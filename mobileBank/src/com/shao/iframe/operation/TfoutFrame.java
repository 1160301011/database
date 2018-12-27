package com.shao.iframe.operation;

import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.model.*;
import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
/**
 * @author HGX
 *表示层
 *跨行转账界面 
 *
 */
public class TfoutFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField toname_Field;
	private JTextField out_moneyField;
	private JTextField tocard_Field;
	private JTextField from_cardField;
	//private com.shao.model.user user;
	private com.shao.model.Clientinfo cif;
	private com.shao.model.Bankcard bkd_cif;
	private com.shao.model.Bankcard bankcard_query_from;
	private com.shao.model.Payee pye;
	private com.shao.model.Bankcard bankcard_query_cardid;
	private com.shao.model.Bankcard bkd_query_card_serial;
	private com.shao.model.Bankuser bu;
	public String bankname ="未知";
	public TfoutFrame(final String username,final String client_id) {
	    
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 400);
		setTitle(username);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("对方姓名：");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(115, 72, 126, 30);
		contentPane.add(lblNewLabel);
		
		toname_Field = new JTextField();
		toname_Field.setBounds(245, 77, 150, 24);
		contentPane.add(toname_Field);
		toname_Field.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("对方卡号：");
		lblNewLabel2.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel2.setBounds(115, 141, 126, 30);
		contentPane.add(lblNewLabel2);
		
		tocard_Field = new JTextField();
		tocard_Field.setBounds(245, 141, 150, 24);
		contentPane.add(tocard_Field);
		tocard_Field.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("付款卡号：");
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(115, 210, 112, 30);
		contentPane.add(lblNewLabel_1);
		
		from_cardField = new JTextField();
		from_cardField.setBounds(243, 215, 152, 24);
		contentPane.add(from_cardField);
		from_cardField.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("转账金额：");
		lblNewLabel_2.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel_2.setBounds(115, 280, 112, 30);
		contentPane.add(lblNewLabel_2);
		
		out_moneyField = new JTextField();
		out_moneyField.setBounds(243, 285, 152, 24);
		contentPane.add(out_moneyField);
		out_moneyField.setColumns(10);
		
		
		JButton OKButton = new JButton("确认");
		OKButton.setFont(new Font("新宋体", Font.BOLD, 17));
		OKButton.setBounds(104, 320, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				int res = JOptionPane.showConfirmDialog(null, "确认此次转账？", "转账确认", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					bkd_cif = asi.bkd_cif_query(tocard_Field.getText());
					//查询该客户信息 
					cif = asi.cquery(bkd_cif.getClient_id());
					//查询收款名册有无此卡号的信息
					pye = asi.pye_query(tocard_Field.getText());
					//查询账户信息
					try {
						bu = asi.bu_query(username);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
				if(!tocard_Field.getText().equals("")){
					if (tocard_Field.getText().equals(bkd_cif.getCard_id())) {
						if(!toname_Field.getText().equals("")){
							if(toname_Field.getText().equals(cif.getClient_name())){
								if(!from_cardField.getText().equals("")){
						if (Float.parseFloat(out_moneyField.getText()) < 100000) {
							String str = JOptionPane.showInputDialog("请输入支付密码：");
							//查询转账卡号是否存在
								bankcard_query_from = asi.tra_bkd_query(from_cardField.getText());
								//查询转账卡号的序列号
								bkd_query_card_serial = asi.query_card_serial(from_cardField.getText());
								if (str.equals(bankcard_query_from.getCard_ps())){
							if (bankcard_query_from.getCurrent() > Double.parseDouble(out_moneyField.getText())) {
								DecimalFormat df = new DecimalFormat("0.00 ");
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：跨行转账交易；责任人：业务员14"+"\n";
								//跨行利息计算
								double fee = Double.parseDouble(out_moneyField.getText())*0.001;
								double temp = Double.parseDouble(out_moneyField.getText())+Double.parseDouble(out_moneyField.getText())*0.001;
								asi.bkdtransfer_out(from_cardField.getText(), tocard_Field.getText(), Double.parseDouble(out_moneyField.getText()),temp);
								try {
									asi.addtra_out_record(bkd_query_card_serial.getCard_serial(),client_id,tocard_Field.getText(),df.format(temp),fee);
								} catch (ClassNotFoundException e1) {
									string=string+e1.toString()+"\n";
									e1.printStackTrace();
								}
								if(pye == null){
									asi.addpayee(bu.getUid(), toname_Field.getText(), tocard_Field.getText(),bankname);
								}else{
									System.out.println("收款人已存在");
								}
								OperationFrame opf = new OperationFrame(username,client_id);
								setVisible(false);
								opf.setVisible(true);	
//									try {
										bankcard_query_cardid = asi.tra_bkd_query(from_cardField.getText());
//									} catch (SQLException e1) {
//										e1.printStackTrace();
//									}
									string=string+"转账交易成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent()+"\n"+"手续费为:"+df.format(fee)+"\n";
									JOptionPane.showMessageDialog(null, "转账交易成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent()+"\n"+"手续费为:"+df.format(fee));
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
								string=string+"支付密码不正确！"+"\n";
								JOptionPane.showMessageDialog(null, "支付密码不正确！");
						}
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
							string=string+"转账金额必须小于10000，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "转账金额必须小于10000，请重新输入！");
							out_moneyField.setText("");
						}
						
								}else{
									SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									string=string+df1.format(new Date());
									string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
									string=string+"请输入付款卡号！"+"\n";
									JOptionPane.showMessageDialog(null, "请输入付款卡号！");
								}
							}else{
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
								string=string+"姓名与卡号不符！"+"\n";
								JOptionPane.showMessageDialog(null, "姓名与卡号不符！");
								toname_Field.setText("");
							}
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
							string=string+"请输入对方姓名！"+"\n";
							JOptionPane.showMessageDialog(null, "请输入对方姓名！");
						}
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
						string=string+"您输入的银行卡不存在！"+"\n";
						JOptionPane.showMessageDialog(null, "您输入的银行卡不存在！");
						tocard_Field.setText("");
					}
					} else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：跨行转账交易；责任人：用户"+"\n";
						string=string+"请输入银行卡号！"+"\n";
						JOptionPane.showMessageDialog(null, "请输入银行卡号！");
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
		backButton_1.setBounds(282, 320, 113, 27);
		contentPane.add(backButton_1);
		backButton_1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				OperationFrame opf = new OperationFrame(username,client_id);
				setVisible(false);
				opf.setVisible(true);			
			}		
		});
	
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
