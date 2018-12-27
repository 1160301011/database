package com.shao.iframe.bill;

import java.awt.Font;
import java.awt.Insets;
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
 *生活缴费界面 
 *
 */
public class LifeFrame extends JFrame{
	private JPanel contentPane;  //面板
	private JTextField pay_cardField,pay_moneyField,pay_remarkField;  //输入框
	private JPasswordField pay_pwdField;  //密码输入框
	private com.shao.model.Bankcard query_paycard;  //银行卡实体调用
	private com.shao.model.Creditcard query_paycard2;
	private com.shao.model.Bankcard query_paycard_serial1;	//储蓄卡序列号声明
	private com.shao.model.Creditcard query_paycard_serial2;	//信用卡序列号声明
	private com.shao.model.Bankcard bankcard_query_cardid;
	
//	private com.shao.model.bankcard 
	
	public LifeFrame(final String username,final String client_id) {
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 400);					//设置窗体大小
		setTitle("尊敬的:"+username+ "欢迎进入生活缴费");
		setLocationRelativeTo(null);				//在屏幕中间显示(居中显示)  
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  //设置布局边框
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//付款银行卡标签
		JLabel lblNewLabel = new JLabel("付款银行卡：");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 72, 126, 30);
		contentPane.add(lblNewLabel);
		//银行卡号标签
		pay_cardField = new JTextField();
		pay_cardField.setBounds(245, 77, 150, 24);
		contentPane.add(pay_cardField);
		pay_cardField.setColumns(10);
		
		//付款金额标签
		JLabel lblNewLabel2 = new JLabel("付款金额：");
		lblNewLabel2.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel2.setBounds(105, 141, 126, 30);
		contentPane.add(lblNewLabel2);
		//文本输入框
		pay_moneyField = new JTextField();
		pay_moneyField.setBounds(245, 141, 150, 24);
		contentPane.add(pay_moneyField);
		pay_moneyField.setColumns(10);
		
		//支付密码标签
		JLabel lblNewLabel_1 = new JLabel("支付密码：");
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(105, 210, 112, 30);
		contentPane.add(lblNewLabel_1);
		//密码文本框
		pay_pwdField = new JPasswordField();
		pay_pwdField.setBounds(243, 210, 152, 24);
		contentPane.add(pay_pwdField);
		pay_pwdField.setColumns(10);
		

		//储蓄卡按钮
		JButton OKButton = new JButton("储蓄卡缴费");
		OKButton.setFont(new Font("新宋体", Font.BOLD, 17));
		OKButton.setBounds(104, 270, 113, 27);
		OKButton.setMargin(new Insets(0,0,0,0));
		contentPane.add(OKButton);    
		//设置按键监听
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				//弹出确认框
				int res = JOptionPane.showConfirmDialog(null, "确认此次缴费？", "缴费确认", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					//查询卡号的信息
					query_paycard = asi.tra_bkd_query(pay_cardField.getText());
					//判断卡号不为空
				if(!pay_cardField.getText().equals("")){  
					//判断金额不为空
					if (!pay_moneyField.getText().equals("")){
						//判断密码不为空
						if (pay_pwdField.getText().equals(query_paycard.getCard_ps())) {
							//判断输入金额小于限制额度
						if (Float.parseFloat(pay_moneyField.getText()) < 100000) {
							if (query_paycard.getCurrent() > Double.parseDouble(pay_moneyField.getText())) {
							//查询缴费卡号是否存在
							query_paycard = asi.tra_bkd_query(pay_cardField.getText());
							query_paycard_serial1 = asi.query_paycard_serial(pay_cardField.getText());
							//如果卡中的余额大于所要缴费的余额 进行下一步判断
							if (query_paycard.getCurrent() > Double.parseDouble(pay_moneyField.getText())) {
								DecimalFormat df = new DecimalFormat("0.00 ");
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：缴费；责任人：业务员5"+"\n";
								//执行缴费业务扣款
								asi.paybill_trade(query_paycard_serial1.getCard_id(),Double.parseDouble(pay_moneyField.getText()));
								try {
									//加入消费表
									asi.addConsumtion_life(query_paycard_serial1.getCard_serial(),Double.parseDouble(pay_moneyField.getText()));
								} catch (NumberFormatException e1) {
									string=string+e1.toString()+"\n";
									e1.printStackTrace();
								} catch (Exception e1) {
									string=string+e1.toString()+"\n";
									e1.printStackTrace();
								}
								SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df11.format(new Date());
								string=string+"缴费成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent()+"\n";
									setVisible(false);
									PaymentFrame frame = new PaymentFrame(username,client_id);
									frame.setVisible(true);
										bankcard_query_cardid = asi.tra_bkd_query(pay_cardField.getText());
									JOptionPane.showMessageDialog(null, "缴费成功！" + "\n" + "剩余余额为：" + bankcard_query_cardid.getCurrent());
														
							} else {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df.format(new Date());
								string=string+"当前程序：缴费；责任人：用户"+"\n";
								string=string+"余额不足，请重新输入！" + "\n" + "当前余额为：" + bankcard_query_cardid.getCurrent()+"\n";
								JOptionPane.showMessageDialog(null,
										"余额不足，请重新输入！" + "\n" + "当前余额为：" + bankcard_query_cardid.getCurrent());
							}
						}else{
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df.format(new Date());
							string=string+"当前程序：缴费；责任人：用户"+"\n";
							string=string+"您的卡号余额不足，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "您的卡号余额不足，请重新输入！");
							pay_moneyField.setText("");
						}
						
						} else {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df.format(new Date());
							string=string+"当前程序：缴费；责任人：用户"+"\n";
							string=string+"缴费金额必须小于10000，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "缴费金额必须小于10000，请重新输入！");
							pay_moneyField.setText("");
						} 
						}else{
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df.format(new Date());
							string=string+"当前程序：缴费；责任人：用户"+"\n";
							string=string+"支付密码不正确，请重新输入！"+"\n";
							JOptionPane.showMessageDialog(null, "支付密码不正确，请重新输入！");
							pay_moneyField.setText("");
						}
							
					} else {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df.format(new Date());
						string=string+"当前程序：缴费；责任人：用户"+"\n";
						string=string+"请输入缴费金额"+"\n";
						JOptionPane.showMessageDialog(null, "请输入缴费金额");
//						pay_moneyField.setText("");
					}
					} else{
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df.format(new Date());
						string=string+"当前程序：缴费；责任人：用户"+"\n";
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
		
//		JButton CreOKButton = new JButton("信用卡缴费");
//		CreOKButton.setFont(new Font("新宋体", Font.BOLD, 17));
//		CreOKButton.setBounds(104, 310, 113, 27);
//		CreOKButton.setMargin(new Insets(0,0,0,0));
//		contentPane.add(CreOKButton);
//		CreOKButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int res = JOptionPane.showConfirmDialog(null, "确认此次缴费？", "缴费确认", JOptionPane.YES_NO_OPTION);
//				if (res == JOptionPane.YES_OPTION) {
//					//信用卡卡号查询
//					query_paycard2 = asi.cre_card_query(pay_cardField.getText());
//				if(!pay_cardField.getText().equals("")){
//					if (!pay_moneyField.getText().equals("")){
//						if (pay_pwdField.getText().equals(query_paycard2.getCre_pwd())) {
//						if (Float.parseFloat(pay_moneyField.getText()) < 100000) {
//							if (query_paycard2.getCre_available() > Double.parseDouble(pay_moneyField.getText())) {
//							//查询缴费信用卡号是否存在
//							query_paycard2 = asi.cre_card_query(pay_cardField.getText());
//							System.out.println("1");
//							query_paycard_serial2 = asi.query_crd_serial(pay_cardField.getText());
//							System.out.println("2");
//							//如果卡中的可用额度大于所要缴费的余额 进行下一步判断
//							if (query_paycard2.getCre_available() > Double.parseDouble(pay_moneyField.getText())) {
//								DecimalFormat df = new DecimalFormat("0.00 ");
//								//执行缴费业务扣款
//								asi.paybill_cretrade(query_paycard_serial2.getCre_serial(),Double.parseDouble(pay_moneyField.getText()));
////								try {
////									asi.addConsumtion_life(query_paycard_serial1.getCard_serial(),Double.parseDouble(pay_moneyField.getText()));
////								} catch (NumberFormatException e1) {
////									e1.printStackTrace();
////								} catch (Exception e1) {
////									e1.printStackTrace();
////								}
//									setVisible(false);
//									paymentFrame frame = new paymentFrame(username,client_id);
//									frame.setVisible(true);
//										crecard_query_cardid = asi.cre_card_query(pay_cardField.getText());
//									JOptionPane.showMessageDialog(null, "缴费成功！" + "\n" + "剩余额度为：" + crecard_query_cardid.getCre_available());
//														
//							} else {
//								JOptionPane.showMessageDialog(null,
//										"额度不足，请重新输入！" + "\n" + "当前额度为：" + crecard_query_cardid.getCre_available());
//							}
//						}else{
//							JOptionPane.showMessageDialog(null, "您的卡号余额不足，请重新输入！");
//							pay_moneyField.setText("");
//						}
//						
//						} else {
//							JOptionPane.showMessageDialog(null, "缴费金额必须小于10000，请重新输入！");
//							pay_moneyField.setText("");
//						} 
//						}else{
//							JOptionPane.showMessageDialog(null, "支付密码不正确，请重新输入！");
//							pay_moneyField.setText("");
//						}
//							
//					} else {
//						JOptionPane.showMessageDialog(null, "请输入缴费金额");
////						pay_moneyField.setText("");
//					}
//					} else{
//						JOptionPane.showMessageDialog(null, "请输入您的银行卡！");
//						pay_cardField.setText("");
//					}
//				} else {
//					return;
//				}
//			}
//		});
		
		
		//返回按钮
		
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
