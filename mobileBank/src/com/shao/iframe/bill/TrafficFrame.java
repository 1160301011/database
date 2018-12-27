package com.shao.iframe.bill;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
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

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;

/**
 * @author HGX
 *表示层
 *交通缴费界面 
 *
 */

public class TrafficFrame extends JFrame{

	private JPanel contentPane;
	private JTextField pay_cardField;  
	private JTextField pay_moneyField;
	private JTextField pay_remarkField;
	private JPasswordField pay_pwdField;
	private com.shao.model.Bankcard query_paycard;
	private com.shao.model.Bankcard query_paycard_serial1;
	private com.shao.model.Bankcard bankcard_query_cardid;
	
//	private com.shao.model.bankcard 
	
	public TrafficFrame(final String username,final String client_id) {
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 400);
		setTitle("尊敬的:"+username+ "欢迎进入交通缴费");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("付款银行卡：");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 72, 126, 30);
		contentPane.add(lblNewLabel);
		
		pay_cardField = new JTextField();
		pay_cardField.setBounds(245, 77, 150, 24);
		contentPane.add(pay_cardField);
		pay_cardField.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("付款金额：");
		lblNewLabel2.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel2.setBounds(105, 141, 126, 30);
		contentPane.add(lblNewLabel2);
		
		pay_moneyField = new JTextField();
		pay_moneyField.setBounds(245, 141, 150, 24);
		contentPane.add(pay_moneyField);
		pay_moneyField.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("支付密码：");
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(105, 210, 112, 30);
		contentPane.add(lblNewLabel_1);
		
		pay_pwdField = new JPasswordField();
		pay_pwdField.setBounds(243, 210, 152, 24);
		contentPane.add(pay_pwdField);
		pay_pwdField.setColumns(10);
		

		
		JButton OKButton = new JButton("确认");
		OKButton.setFont(new Font("新宋体", Font.BOLD, 17));
		OKButton.setBounds(104, 270, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				int res = JOptionPane.showConfirmDialog(null, "确认此次缴费？", "缴费确认", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
//					try {
//						bank_user_query_to = asi.bu_query(out_nameField.getText());
					query_paycard = asi.tra_bkd_query(pay_cardField.getText());
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
				if(!pay_cardField.getText().equals("")){
					if (!pay_moneyField.getText().equals("")){
						if (pay_pwdField.getText().equals(query_paycard.getCard_ps())) {
						if (Float.parseFloat(pay_moneyField.getText()) < 100000) {
							if (query_paycard.getCurrent() > Double.parseDouble(pay_moneyField.getText())) {
							//查询缴费卡号是否存在
							query_paycard = asi.tra_bkd_query(pay_cardField.getText());
							query_paycard_serial1 = asi.query_paycard_serial(pay_cardField.getText());
							//如果卡中的余额大于所要缴费的余额 进行下一步判断
							if (query_paycard.getCurrent() > Double.parseDouble(pay_moneyField.getText())) {
								DecimalFormat df = new DecimalFormat("0.00 ");
								//执行缴费业务扣款
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：交通缴费；责任人：业务员9"+"\n";
								asi.paybill_trade(query_paycard_serial1.getCard_id(),Double.parseDouble(pay_moneyField.getText()));
								try {
									asi.addConsumtion_traffic(query_paycard_serial1.getCard_serial(),Double.parseDouble(pay_moneyField.getText()));
								} catch (NumberFormatException e1) {
									e1.printStackTrace();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								string=string+"缴费成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent()+"\n";
									setVisible(false);
									PaymentFrame frame = new PaymentFrame(username,client_id);
									frame.setVisible(true);
										bankcard_query_cardid = asi.tra_bkd_query(pay_cardField.getText());
									JOptionPane.showMessageDialog(null, "缴费成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent());
														
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：商城购物；责任人：用户"+"\n";
								string=string+"余额不足，请重新输入！" + "\n" + "当前余额为：" + bankcard_query_cardid.getCurrent()+"\n";
								JOptionPane.showMessageDialog(null,
										"余额不足，请重新输入！" + "\n" + "当前余额为：" + bankcard_query_cardid.getCurrent());
							}
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：商城购物；责任人：用户"+"\n";
							string=string+"您的卡号余额不足，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "您的卡号余额不足，请重新输入！");
							pay_moneyField.setText("");
						}
						
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：商城购物；责任人：用户"+"\n";
							string=string+"缴费金额必须小于10000，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "缴费金额必须小于10000，请重新输入！");
							pay_moneyField.setText("");
						} 
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：商城购物；责任人：用户"+"\n";
							string=string+"支付密码不正确，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "支付密码不正确，请重新输入！");
							pay_moneyField.setText("");
						}
							
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：商城购物；责任人：用户"+"\n";
						string=string+"支付密码不正确，请重新输入！"+"\n";
						JOptionPane.showMessageDialog(null, "请输入缴费金额");
//						pay_moneyField.setText("");
					}
					} else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：商城购物；责任人：用户"+"\n";
						string=string+"请输入您的银行卡！"+"\n";
						JOptionPane.showMessageDialog(null, "请输入您的银行卡！");
						pay_cardField.setText("");
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
				setVisible(false);
				PaymentFrame frame = new PaymentFrame(username,client_id);
				frame.setVisible(true);			
			}		
		});
		
	
}
	
}
	

