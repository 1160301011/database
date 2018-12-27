package com.shao.iframe.operation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.model.Bankcard;
import com.shao.model.User;
import com.shao.model.Bankuser;

/**
 * @author HGX
 *表示层
 *存款界面 
 *
 */

public class DepositFrame extends JFrame {
	
	private JComboBox<String> card_choose_JCo;	
	private JPanel contentPane;
	private JTextField inputField,chocard_Field;
	private User user_query;
	private User user_modMoney;
	private Bankuser bank_user_query;
	private Bankcard bkd;
	/**
	 * Create the frame.
	 */
	/**
	 * @param username  用户名
	 * @param client_id  客户名
	 */
	public DepositFrame(final String username,final String client_id) {
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 380);
		setTitle(username);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("存款金额:");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(80, 92, 121, 27);
		contentPane.add(lblNewLabel);
		
		inputField = new JTextField();			//转账用户名输入框
		inputField.setBounds(225, 95, 133, 24);
		contentPane.add(inputField);
		inputField.setColumns(10);
		
		JLabel lbldepcard = new JLabel("存款卡号:");
		lbldepcard.setFont(new Font("新宋体", Font.BOLD, 20));
		lbldepcard.setBounds(80, 162, 121, 27);
		contentPane.add(lbldepcard);
		
		chocard_Field = new JTextField();
		chocard_Field.setBounds(225, 165, 130, 21);
//		card_choose_JCo.addItem(card_id1);
		contentPane.add(chocard_Field);
//		cre_closepay.addActionListener();
		chocard_Field.setColumns(10);
		
													//活期存款按钮 
		JButton CurrBtn = new JButton("活期存入");
		CurrBtn.setFont(new Font("新宋体", Font.BOLD, 15));
		CurrBtn.setBounds(90, 250, 130, 27);
		contentPane.add(CurrBtn);
		CurrBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				if(Float.parseFloat(inputField.getText())<100000){		//单日交易限额十万
					try {
						bank_user_query = asi.bu_query(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					bkd = asi.card_query(chocard_Field.getText());
					double ubalance = bank_user_query.getUserbalance()-Double.parseDouble(inputField.getText());
					double bcurrent = bkd.getCurrent()+Double.parseDouble(inputField.getText());
					DecimalFormat df = new DecimalFormat( "0.00 "); 
					asi.update(bank_user_query.getUsername(), ubalance);
					asi.upcurrent(chocard_Field.getText(),bcurrent);
//						setVisible(false);
//						AtmFrame frame = new AtmFrame(username,client_id);
//						frame.setVisible(true);
						try {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：存款交易；责任人：业务员13"+"\n";
							bank_user_query = asi.bu_query(username);
						} catch (SQLException e1) {
							string=string+e1.toString()+"\n";
							e1.printStackTrace();
						}
						string=string+"交易成功！"+"\n" + "当前账户余额为：" + bank_user_query.getUserbalance();
						JOptionPane.showMessageDialog(null, "交易成功！"+"\n" + "当前账户余额为：" + bank_user_query.getUserbalance());							
				}else{
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df1.format(new Date());
					string=string+"当前程序：存款交易；责任人：用户"+"\n";
					string=string+"输入金额大于1000000，请重新输入！"+"\n";
					JOptionPane.showMessageDialog(null, "输入金额大于1000000，请重新输入！");
					inputField.setText("");
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
		
		JButton DeathBtn = new JButton("定期存入");
		DeathBtn.setFont(new Font("新宋体", Font.BOLD, 15));
		DeathBtn.setBounds(290, 250, 130, 27);
		contentPane.add(DeathBtn);
		DeathBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Float.parseFloat(inputField.getText())<100000){		//单日交易限额十万
					try {
						bank_user_query = asi.bu_query(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					bkd = asi.card_query(chocard_Field.getText());
					double ubalance = bank_user_query.getUserbalance()-Double.parseDouble(inputField.getText());
					double bdeath = bkd.getDeath()+Double.parseDouble(inputField.getText());
					DecimalFormat df = new DecimalFormat( "0.00 "); 
					asi.update(bank_user_query.getUsername(), ubalance);
					asi.updeath(chocard_Field.getText(),bdeath);
//						setVisible(false);
//						AtmFrame frame = new AtmFrame(username,client_id);
//						frame.setVisible(true);
						try {
							bank_user_query = asi.bu_query(username);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "交易成功！"+"\n" + "当前账户余额为：" + bank_user_query.getUserbalance());							
				}else{
					JOptionPane.showMessageDialog(null, "输入金额大于1000000，请重新输入！");
					inputField.setText("");
				}				
			}
			
		});
		
		//返回按钮
		JButton rebackBtn = new JButton("返回");
		rebackBtn.setFont(new Font("新宋体", Font.BOLD, 15));
		rebackBtn.setBorderPainted(true); // 无边框
		rebackBtn.setOpaque(false); // 设置按钮透明
		rebackBtn.setContentAreaFilled(false); // 关闭底色
		rebackBtn.setBounds(350, 310, 113, 27);
		contentPane.add(rebackBtn);
		rebackBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				OperationFrame of = new OperationFrame(username,client_id);
				setVisible(false);
				of.setVisible(true);			
			}		
		});
		//单日限额标签
		JLabel lblNewLabel_1 = new JLabel("*限额100000");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(369, 98, 105, 18);
		contentPane.add(lblNewLabel_1);
	}
	
	class card_chooseAction implements ActionListener {
		
		
		public void actionPerformed(ActionEvent e) {
			int i=card_choose_JCo.getSelectedIndex()+1;
			String s=(String)card_choose_JCo.getSelectedItem();
			System.out.println("你选中的是第"+i+"项"+",内容是:"+s);
		}
	}
}
