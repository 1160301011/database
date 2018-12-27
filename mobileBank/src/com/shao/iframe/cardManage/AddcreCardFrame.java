package com.shao.iframe.cardManage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
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
//import com.shao.iframe.cardManage.cardManageFrame.BoundBtnAction;
//import com.shao.iframe.cardManage.addcreCardFrame.BoundBtnAction;
import com.shao.model.*;


/**
 * @author HGX
 *表示层
 *开通信用卡界面 
 *
 */
public class AddcreCardFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPasswordField cre_pwd; // 信用卡密码
	private JTextField cre_balance; // 信用卡额度
	private JTextField cre_available; // 可用额度
	private JTextField return_card; // 信用卡还款账号
	private JTextField uid;
	private JTextField client_id1;
	private JComboBox<String> cre_auto; // 自动还款设置
	private JComboBox<String> cre_closepay; // 免密设置
	private Creditcard crd_serial;
	private Bankuser bu;
	private Bankuser bu2;
	private Bankcard bkd;
	private Clientinfo cif;

	public AddcreCardFrame(final String username, final String client_id) {
		final AccountServiceImpl asi = new AccountServiceImpl();
		setTitle("尊敬的用户: " + username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lbltitle = new JLabel();
		lbltitle.setFont(new Font("新宋体", Font.BOLD, 22));
		lbltitle.setForeground(Color.RED); // 字体颜色设置
		lbltitle.setText("请填写信息");
		lbltitle.setBounds(160, 30, 162, 34);
		contentPane.add(lbltitle);

		JLabel lblNewLabel0 = new JLabel("申请额度:");
		lblNewLabel0.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel0.setBounds(80, 450, 140, 28);
		contentPane.add(lblNewLabel0);

		cre_balance = new JTextField();
		cre_balance.setBounds(190, 457, 130, 21);
		contentPane.add(cre_balance);
		cre_balance.setColumns(10);
		// 申请额度 上限提醒
		JLabel lblNewLabel0_1 = new JLabel("*限额20000");
		lblNewLabel0_1.setForeground(Color.RED);
		lblNewLabel0_1.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblNewLabel0_1.setBounds(230, 480, 105, 18);
		contentPane.add(lblNewLabel0_1);

		try {
			bu2 = asi.bu_query(username);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JLabel lbluid = new JLabel("您的账户ID:");
		lbluid.setFont(new Font("新宋体", Font.BOLD, 18));
		lbluid.setBounds(45, 90, 152, 34);
		contentPane.add(lbluid);

		uid = new JTextField();
		uid.setBounds(190, 95, 130, 21);
		contentPane.add(uid);
		uid.setColumns(10);
		uid.setText(bu2.getUid());

		cif = asi.cquery(client_id);

		JLabel lblclient_id = new JLabel("您的客户ID:");
		lblclient_id.setFont(new Font("新宋体", Font.BOLD, 18));
		lblclient_id.setBounds(45, 150, 152, 34);
		contentPane.add(lblclient_id);

		client_id1 = new JTextField();
		client_id1.setBounds(190, 157, 130, 21);
		contentPane.add(client_id1);
		client_id1.setColumns(10);
		client_id1.setText(cif.getClient_id());

		JLabel lblNewLabel = new JLabel("设置支付密码:");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel.setBounds(45, 210, 152, 34);
		contentPane.add(lblNewLabel);

		cre_pwd = new JPasswordField();
		cre_pwd.setBounds(190, 218, 130, 21);
		contentPane.add(cre_pwd);
		cre_pwd.setColumns(10);

		JLabel lblrestpwd = new JLabel("*请设置6位数密码");
		lblrestpwd.setForeground(Color.RED);
		lblrestpwd.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblrestpwd.setBounds(220, 248, 135, 18);
		contentPane.add(lblrestpwd);

		JLabel lblNewLabel_1 = new JLabel("还款账号:");
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_1.setBounds(80, 270, 88, 28);
		contentPane.add(lblNewLabel_1);

		return_card = new JTextField();
		return_card.setBounds(190, 277, 130, 21);
		contentPane.add(return_card);
		return_card.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("免密设置:");
		lblNewLabel_2.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_2.setBounds(80, 330, 150, 28);
		contentPane.add(lblNewLabel_2);

		cre_closepay = new JComboBox();
		cre_closepay.setBounds(190, 337, 130, 21);
		cre_closepay.addItem("1");
		cre_closepay.addItem("2");
		contentPane.add(cre_closepay);
		// cre_closepay.addActionListener();
		cre_closepay.addActionListener(new cre_closepayAction());

		// 自动还款设置提示
		JLabel lblNewLabel_20 = new JLabel("*1关闭,2开启");
		lblNewLabel_20.setForeground(Color.RED);
		lblNewLabel_20.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblNewLabel_20.setBounds(230, 360, 105, 18);
		contentPane.add(lblNewLabel_20);

		JLabel lblNewLabel_3 = new JLabel("自动还款:");
		lblNewLabel_3.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_3.setBounds(80, 390, 140, 28);
		contentPane.add(lblNewLabel_3);

		cre_auto = new JComboBox();
		cre_auto.setBounds(190, 397, 130, 21);
		cre_auto.addItem("1");
		cre_auto.addItem("2");
		contentPane.add(cre_auto);
		cre_auto.addActionListener(this);
		this.setVisible(true);
		// String[] choice ={"1","2"};

		// System.out.println(cre_closepay.getSelectedItem().toString());
		// int text = cre_closepay.getSelectedIndex();

		// String s=(String)cre_closepay.getSelectedItem();
		// System.out.println(s);
		this.setVisible(true);
		// 免密设置提示
		JLabel lblNewLabel_30 = new JLabel("*1关闭,2开启");
		lblNewLabel_30.setForeground(Color.RED);
		lblNewLabel_30.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblNewLabel_30.setBounds(230, 425, 105, 18);
		contentPane.add(lblNewLabel_30);

		//
		// JLabel lblNewLabel_4 = new JLabel("设置支付密码:");
		// lblNewLabel_4.setFont(new Font("新宋体", Font.BOLD, 18));
		// lblNewLabel_4.setBounds(50, 330, 150, 28);
		// contentPane.add(lblNewLabel_4);
		//
		// card_ps = new JTextField();
		// card_ps.setBounds(190, 337, 130, 21);
		// contentPane.add(card_ps);
		// card_ps.setColumns(10);

		JButton rollback = new JButton("返回");
		rollback.setFont(new Font("新宋体", Font.BOLD, 20));
		rollback.setBounds(250, 530, 113, 27);
		contentPane.add(rollback);
		rollback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(username, client_id);
				setVisible(false);
				af.setVisible(true);
			}
		});

		JButton createBtn = new JButton("开通"); // 注册按钮设置
		createBtn.setFont(new Font("新宋体", Font.BOLD, 20));
		createBtn.setBounds(100, 530, 113, 27);
		contentPane.add(createBtn);
		createBtn.addActionListener(new createBtnAction());
	}

	class cre_closepayAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int i = cre_closepay.getSelectedIndex() + 1;
			String s = (String) cre_closepay.getSelectedItem();
			System.out.println("你选中的是第" + i + "项" + ",内容是:" + s);
		}
	}
	/*
	 * 信息输入框
	 */
	class createBtnAction implements ActionListener {

		AccountServiceImpl asi = new AccountServiceImpl();

		public void actionPerformed(ActionEvent e) {
			// 查找信用卡序列号 避免重复开通
			try {
				bu = asi.bu_queryuid(uid.getText());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			String string=new String();
			// if(!uid.getText().equals("")){
			// if(!client_id1.getText().equals("")){
			if (!cre_pwd.getText().equals("")) {
				// 支付密码限制不超过6位
				String regex = "[0-9]{6}";
				if (cre_pwd.getText().matches(regex)) {
					if (!return_card.getText().equals("")) {
						if (!cre_closepay.getSelectedItem().equals("")) {
							if (!cre_auto.getSelectedItem().equals("")) {
								if (!cre_balance.getText().equals("")) {
									if (bu != null) {
										try {
											SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
											string=string+df1.format(new Date());
											string=string+"当前程序：避免重复开通；责任人：业务员11"+"\n";
											asi.addcredit_card(uid.getText(),client_id1.getText(),cre_pwd.getText(),return_card.getText(),(String) cre_closepay.getSelectedItem(),(String) cre_auto.getSelectedItem(),cre_balance.getText());
											string=string+"执行成功"+"\n";
											System.out.println("执行成功");
										} catch (ClassNotFoundException e1) {
											string=string+e1.toString()+"\n";
											e1.printStackTrace();
										}
										SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
										string=string+df1.format(new Date());
										string=string+"开通" + "成功！"+"\n";
										JOptionPane.showMessageDialog(null,
												"开通" + "成功！");
										setVisible(true);
									} else {
										SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
										string=string+df1.format(new Date());
										string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
										string=string+"你输入的ID无效！"+"\n";
										JOptionPane.showMessageDialog(null,
												"你输入的ID无效！");
									}

								} else {
									SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									string=string+df1.format(new Date());
									string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
									string=string+"未输入申请额度！"+"\n";
									JOptionPane.showMessageDialog(null,
											"未输入申请额度！");
								}
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
								string=string+"未设置免密方式！"+"\n";
								JOptionPane.showMessageDialog(null, "未设置免密方式！");
							}
							//
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
							string=string+"未选择还款方式！"+"\n";
							JOptionPane.showMessageDialog(null, "未选择还款方式！");
						}
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
						string=string+"未输入还款账号！"+"\n";
						JOptionPane.showMessageDialog(null, "未输入还款账号！");
					}
				} else {
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df1.format(new Date());
					string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
					string=string+"密码不符合要求！"+"\n";
					JOptionPane.showMessageDialog(null, "密码不符合要求！");
				}
			} else {
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df1.format(new Date());
				string=string+"当前程序：避免重复开通；责任人：用户"+"\n";
				string=string+"未输入支付密码！"+"\n";
				JOptionPane.showMessageDialog(null, "未输入支付密码！");
			}
			try {
				FileWriter writer = new FileWriter("log.txt", true);
	            writer.write(string);
	            writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// }else{
			// JOptionPane.showMessageDialog(null, "未输入客户ID！");
			// }
			// }else{
			// JOptionPane.showMessageDialog(null, "未输入账户ID！");
			// }
		}
	}

	public void actionPerformed(ActionEvent e) {
		int i = cre_auto.getSelectedIndex() + 1;
		String s = (String) cre_auto.getSelectedItem();
		System.out.println("你选中的是第" + i + "项" + ",内容是:" + s);
		// 把i插入你数据库中对应的属性字段

	}
}
