package com.shao.iframe.operation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;

/**
 * @author HGX
 *表示层
 *同行转账界面 
 *
 */

public class TransferFrame extends JFrame {

	private JPanel contentPane;
	private JTextField out_cardField;
	private JTextField out_moneyField;
	private JTextField descard_Field;
	//private com.shao.model.user user;
	private com.shao.model.Bankcard bankcard_query_from;
	private com.shao.model.Bankcard bankcard_query_to;
	private com.shao.model.Bankcard bankcard_query_cardid;
	private com.shao.model.Bankcard bkd_query_card_serial;
	private com.shao.model.Bankcard bkd_query_card_serial2;
	private com.shao.model.Bankcard bkd;
	private com.shao.model.Payee pye;
	private com.shao.model.Bankuser bu;
	private com.shao.model.Clientinfo cif;
	public String bname ="未知";

	/**
	 * Create the frame.
	 */
	public TransferFrame(final String username,final String client_id) {
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 400);
		setTitle(username);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("付款卡号：");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 72, 126, 30);
		contentPane.add(lblNewLabel);
		
		out_cardField = new JTextField();
		out_cardField.setBounds(245, 77, 150, 24);
		contentPane.add(out_cardField);
		out_cardField.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("收款卡号：");
		lblNewLabel2.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel2.setBounds(115, 141, 126, 30);
		contentPane.add(lblNewLabel2);
		
		descard_Field = new JTextField();
		descard_Field.setBounds(245, 141, 150, 24);
		contentPane.add(descard_Field);
		descard_Field.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("转账金额：");
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(115, 210, 112, 30);
		contentPane.add(lblNewLabel_1);
		
		out_moneyField = new JTextField();
		out_moneyField.setBounds(243, 215, 152, 24);
		contentPane.add(out_moneyField);
		out_moneyField.setColumns(10);
		
		
		
		
		JButton OKButton = new JButton("确认");
		OKButton.setFont(new Font("新宋体", Font.BOLD, 17));
		OKButton.setBounds(104, 270, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				int res = JOptionPane.showConfirmDialog(null, "确认此次转账？", "转账确认", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
//					try {
//						bank_user_query_to = asi.bu_query(out_nameField.getText());
						bankcard_query_to = asi.tra_bkd_query(descard_Field.getText());
						
						try {
							bu =asi.bu_query(username);
						} catch (SQLException e3) {
							e3.printStackTrace();
						}
						bkd = asi.card_query(out_cardField.getText());
						
				if(!out_cardField.getText().equals("")){
					if(bkd.getUid().equals(bu.getUid())){
						if(!descard_Field.getText().equals("")){
					if (descard_Field.getText().equals(bankcard_query_to.getCard_id())) {
						if (Float.parseFloat(out_moneyField.getText()) < 100000) {
							//弹出支付密码输入框
							String str = JOptionPane.showInputDialog("请输入支付密码：");
							//查询转账卡号是否存在
								bankcard_query_from = asi.tra_bkd_query(out_cardField.getText());
								//
								if (str.equals(bankcard_query_from.getCard_ps())){
								//查询转账卡号的序列号
								bkd_query_card_serial = asi.query_card_serial(out_cardField.getText());
								bkd_query_card_serial2 = asi.query_card_serial(descard_Field.getText());
								cif = asi.cinfo_query(bkd_query_card_serial2.getUid());
								//查询收款人信息
								pye = asi.pye_query(descard_Field.getText());
								//查询账户信息
								try {
									bu = asi.bu_query(username);
								} catch (SQLException e2) {
									e2.printStackTrace();
								}
							if (bankcard_query_from.getCurrent() > Double.parseDouble(out_moneyField.getText())) {
								DecimalFormat df = new DecimalFormat("0.00 ");
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：同行转账交易；责任人：业务员15"+"\n";
//								asi.transfer(username, descard_Field.getText(), Double.parseDouble(out_moneyField.getText()));
								asi.bkdtransfer(out_cardField.getText(), descard_Field.getText(), Double.parseDouble(out_moneyField.getText()));
								try {
									System.out.println("转账进入1");
									asi.addtra_record(bkd_query_card_serial.getCard_serial(),client_id,descard_Field.getText(),out_moneyField.getText());
									System.out.println("转账进入2");
								} catch (ClassNotFoundException e1) {
									string=string+e1.toString()+"\n";
									e1.printStackTrace();
								} catch (SQLException e1) {
									string=string+e1.toString()+"\n";
									e1.printStackTrace();
								}
								//检查收款人是否存在  不存在则添加
								if(pye == null){
									asi.addpayee(bu.getUid(), cif.getClient_name(),descard_Field.getText(),bname);
								}else{
									System.out.println("收款人已存在");
								}
									setVisible(false);
									OperationFrame frame = new OperationFrame(username,client_id);
									frame.setVisible(true);
										bankcard_query_cardid = asi.tra_bkd_query(out_cardField.getText());
									string=string+"转账交易成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent()+"\n";
									JOptionPane.showMessageDialog(null, "转账交易成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent());
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
								string=string+"余额不足，请重新输入！" + "\n" + "当前余额为：" + bankcard_query_from.getCurrent()+"\n";
								JOptionPane.showMessageDialog(null,
										"余额不足，请重新输入！" + "\n" + "当前余额为：" + bankcard_query_from.getCurrent());
								out_moneyField.setText("");
							}
								}else{
									SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									string=string+df1.format(new Date());
									string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
									string=string+"您输入的密码不正确！"+"\n";
									JOptionPane.showMessageDialog(null,"您输入的密码不正确！");
								}
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
							string=string+"转账金额必须小于10000，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "转账金额必须小于10000，请重新输入！");
							out_moneyField.setText("");
						}
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
						string=string+"找不到该转账用户！"+"\n";
						JOptionPane.showMessageDialog(null, "找不到该转账用户！");
						descard_Field.setText("");
					}
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
							string=string+"请输入对方银行卡！"+"\n";
							JOptionPane.showMessageDialog(null, "请输入对方银行卡！");
						}
					}else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
						string=string+"请输入属于自己的银行卡！"+"\n";
						JOptionPane.showMessageDialog(null, "请输入属于自己的银行卡！");
						out_cardField.setText("");
					}
					} else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
						string=string+"请输入银行卡！"+"\n";
						JOptionPane.showMessageDialog(null, "请输入银行卡！");
						out_cardField.setText("");
					}
				} else {
					return;
				}
				try {
					FileWriter writer = new FileWriter("log.txt", true);
		            writer.write(string);
		            writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton backButton_1 = new JButton("返回");
		backButton_1.setFont(new Font("新宋体", Font.BOLD, 18));
		backButton_1.setBounds(282, 270, 113, 27);
		contentPane.add(backButton_1);
		backButton_1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				OperationFrame opf = new OperationFrame(username,client_id);
				setVisible(false);
				opf.setVisible(true);			
			}		
		});
	}

}
