package com.shao.iframe.cardManage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shao.iframe.AtmFrame;
import com.shao.model.Bankcard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.model.Bankuser;
import com.shao.model.User;
import com.shao.model.Bankcard;

/**
 * @author HGX
 *表示层
 *绑定储蓄卡界面 
 *
 */
public class AddbankcardFrame extends JFrame {

	private JPanel contentPane;
	private JTextField card_serial;
	private JTextField uid;
	private JTextField bclient_id;
	private JTextField card_id;
	private JTextField int_serial;
	private JTextField card_ps;
	private JTextField card_balance;
	private JTextField current;
	private JTextField death;
	private Bankuser bu;
	private User user;
	private Bankcard bkd1;
	private Bankcard bkd2;
	private String client_id;
	private String username;
	final AccountServiceImpl asi =new AccountServiceImpl();
	
	public AddbankcardFrame(final String username, final String client_id) {
		this.client_id =client_id;
		this.username = username;
		
		setTitle("尊敬的用户: " + username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lbltitle = new JLabel();
		lbltitle.setFont(new Font("新宋体", Font.BOLD, 22));
		lbltitle.setForeground(Color.RED); // 字体颜色设置
		lbltitle.setText("请填写信息");
		lbltitle.setBounds(160, 40, 162, 34);
		contentPane.add(lbltitle);
		
		JLabel lblNewLabel0 = new JLabel("银行卡序列号:");
		lblNewLabel0.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel0.setBounds(45, 150, 151, 28);
		contentPane.add(lblNewLabel0);
		
		card_serial = new JTextField();
		card_serial.setBounds(190, 155, 130, 21);
		contentPane.add(card_serial);
		card_serial.setColumns(10);
		
//		JLabel lblNewLabel = new JLabel("账户ID:");
//		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 18));
//		lblNewLabel.setBounds(100, 90, 102, 34);
//		contentPane.add(lblNewLabel);
//
//		uid = new JTextField();
//		uid.setBounds(190, 98, 130, 21);
//		contentPane.add(uid);
//		uid.setColumns(10);
//
//		JLabel lblNewLabel_1 = new JLabel("客户ID:");
//		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 18));
//		lblNewLabel_1.setBounds(100, 150, 88, 28);
//		contentPane.add(lblNewLabel_1);
//
//		bclient_id = new JTextField();
//		bclient_id.setBounds(190, 157, 130, 21);
//		contentPane.add(bclient_id);
//		bclient_id.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("银行卡卡号:");
		lblNewLabel_2.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_2.setBounds(65, 210, 150, 28);
		contentPane.add(lblNewLabel_2);

		card_id = new JTextField();
		card_id.setBounds(190, 217, 130, 21);
		contentPane.add(card_id);
		card_id.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("存息编号:");
		lblNewLabel_3.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_3.setBounds(80, 270, 131, 28);
		contentPane.add(lblNewLabel_3);

		int_serial = new JTextField();
		int_serial.setBounds(190, 277, 130, 21);
		contentPane.add(int_serial);
		
		int_serial.setText("101");
		

		JLabel lblNewLabel_4 = new JLabel("设置支付密码:");
		lblNewLabel_4.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_4.setBounds(50, 330, 150, 28);
		contentPane.add(lblNewLabel_4);

		card_ps = new JTextField();
		card_ps.setBounds(190, 337, 130, 21);
		contentPane.add(card_ps);
		card_ps.setColumns(10);

//		JLabel lblNewLabel_5 = new JLabel("总金额:");
//		lblNewLabel_5.setFont(new Font("新宋体", Font.BOLD, 18));
//		lblNewLabel_5.setBounds(100, 390, 101, 28);
//		contentPane.add(lblNewLabel_5);
//
//		card_balance = new JTextField();
//		card_balance.setBounds(190, 397, 130, 21);
//		contentPane.add(card_balance);
//		card_balance.setColumns(10);
		JLabel lblNewLabel_6 = new JLabel("活期金额:");
		lblNewLabel_6.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_6.setBounds(80, 390, 101, 28);
		contentPane.add(lblNewLabel_6);

		current = new JTextField();
		current.setBounds(190, 397, 130, 21);
		contentPane.add(current);
		current.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("定期金额:");
		lblNewLabel_7.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_7.setBounds(80, 460, 101, 28);
		contentPane.add(lblNewLabel_7);

		death = new JTextField();
		death.setBounds(190, 467, 130, 21);
		contentPane.add(death);
		death.setColumns(10);

		JButton rollback = new JButton("返回");
		rollback.setFont(new Font("新宋体", Font.BOLD, 20));
		rollback.setBounds(250, 550, 113, 27);
		contentPane.add(rollback);
		rollback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(username,client_id);
				setVisible(false);
				af.setVisible(true);			
			}		
		});
		/*
		 * 信息输入框
		 */
		JButton BoundBtn = new JButton("注册"); // 注册按钮设置
		BoundBtn.setFont(new Font("新宋体", Font.BOLD, 20));
		BoundBtn.setBounds(100, 550, 113, 27);
		contentPane.add(BoundBtn);
		BoundBtn.addActionListener(new BoundBtnAction());
	}
	class BoundBtnAction implements ActionListener {
		AccountServiceImpl asi = new AccountServiceImpl();

		public void actionPerformed(ActionEvent e) {
		String string=new String();
				try {
					bkd1 = asi.bcd_query(card_serial.getText());
				} catch (SQLException e2) {
				}
				try {
					bu = asi.bu_query(username);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				bkd2 = asi.card_query(card_id.getText());
		
		if(!card_serial.getText().equals("")){
//			if(!uid.getText().equals("")){
//				if(!bclient_id.getText().equals("")){
					if(!card_id.getText().equals("")){
						if(!int_serial.getText().equals("")){
							if(!card_ps.getText().equals("")){
								String regex = "[0-9]{6}";
								if(card_ps.getText().matches(regex)){
//								if(!card_balance.getText().equals("")){
									if(!current.getText().equals("")){
										if(!death.getText().equals("")){
											if(bkd1 == null && bkd2==null ){
												try {
													SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
													string=string+df1.format(new Date());
													string=string+"当前程序：绑定储蓄卡；责任人：业务员10"+"\n";
													asi.addBankCard(card_serial.getText(),bu.getUid(),client_id,card_id.getText(),int_serial.getText(),card_ps.getText(),current.getText(),death.getText());
													JOptionPane.showMessageDialog(null, "绑定成功！");
													setVisible(true);
												} catch (SQLException e1) {
													string=string+e1.toString()+"\n";
													e1.printStackTrace();
												}
											}else{
												SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
												string=string+df1.format(new Date());
												string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
												string=string+"您输入的卡号或序列号已存在！"+"\n";
												JOptionPane.showMessageDialog(null, "您输入的卡号或序列号已存在！");
											}
										}else{
											SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
											string=string+df1.format(new Date());
											string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
											string=string+"请输入定期金额！"+"\n";
											JOptionPane.showMessageDialog(null, "请输入定期金额！");
										}
									}else{
										SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
										string=string+df1.format(new Date());
										string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
										string=string+"请输入活期金额！"+"\n";
										JOptionPane.showMessageDialog(null, "请输入活期金额！");
									}
									}else{
										SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
										string=string+df1.format(new Date());
										string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
										string=string+"密码不符合规定！"+"\n";
										JOptionPane.showMessageDialog(null, "密码不符合规定！");
										card_ps.setText("");
									}
//								}else{
//									JOptionPane.showMessageDialog(null, "请输入银行卡余额！");
//								}
							}else{
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
								string=string+"请输入支付密码！"+"\n";
								JOptionPane.showMessageDialog(null, "请输入支付密码！");
							}
							
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
							string=string+"未输入存息编号！"+"\n";
							JOptionPane.showMessageDialog(null, "未输入存息编号！");
						}
					}else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
						string=string+"未输入银行卡卡号！"+"\n";
						JOptionPane.showMessageDialog(null, "未输入银行卡卡号！");
					}
					
//				}else{
//					JOptionPane.showMessageDialog(null, "未输入客户ID！");
//				}
//			}else{
//				JOptionPane.showMessageDialog(null, "未输入账户ID！");
//			}
		}else{
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			string=string+df1.format(new Date());
			string=string+"当前程序：绑定储蓄卡；责任人：用户"+"\n";
			string=string+"未输入银行卡序列号！"+"\n";
			JOptionPane.showMessageDialog(null, "未输入银行卡序列号！");
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
}
}
