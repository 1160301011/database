package com.shao.iframe.umanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AdminFrame;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.LoginFrame;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;


/**添加客户信息
 * @author Administrator
 *
 */
public class AddClientinfo extends JFrame implements ActionListener, HyperlinkListener{
	private JPanel contentPane;
	private JTextField addrField;		//客户地址输入框
	private JTextField client_name;		//客户姓名输入框
	private JTextField client_idcard;		//客户身份输入框
	private JTextField client_phone;		//客户手机输入框
	private Clientinfo cfo;
	private Bankuser bu;
	private String name;
	private String client_id1;
	private JEditorPane pane;
	final AccountServiceImpl asi =new AccountServiceImpl();
	
	
	public AddClientinfo(final String name,final String client_id1) {
		
		
		this.name = name;   //传入客户ID 和用户名  
		this.client_id1 = client_id1;
		MenuBar();
		
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// 设置pane的超级链接监听
		pane.addHyperlinkListener(this);
		this.add(new JScrollPane(pane), "Center");
		
		
		
		//查询客户信息
		cfo = asi.cquery(client_id1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 500, 400);
		setTitle(name);
		setLocationRelativeTo(null);  //设置屏幕居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel lblNewLabel = new JLabel("客  户ID ：");
//		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
//		lblNewLabel.setBounds(105, 72, 126, 30);
//		contentPane.add(lblNewLabel);
//		
//		client_id = new JTextField();			//客户ID输入框
//		client_id.setBounds(235, 77, 152, 20);
//		contentPane.add(client_id);
//		client_id.setColumns(10);
		
														//客户姓名按钮
		JLabel lblNewLabel3 = new JLabel("客户姓名 ：");
		lblNewLabel3.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel3.setBounds(105, 72, 126, 30);
		contentPane.add(lblNewLabel3);
		
		client_name = new JTextField();			//客户姓名输入框
		client_name.setBounds(235, 77, 152, 20);
		contentPane.add(client_name);
		client_name.setColumns(8);
		client_name.setText(cfo.getClient_name());  //数据库自动加载客户姓名
		
		//身份证按钮
		JLabel lblNewLabel4 = new JLabel("身 份 证 ：");
		lblNewLabel4.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel4.setBounds(105, 112, 126, 30);
		contentPane.add(lblNewLabel4);
		
		client_idcard = new JTextField();			//身份证输入框
		client_idcard.setBounds(235, 119, 152, 20);
		contentPane.add(client_idcard);
		client_idcard.setColumns(19);
		client_idcard.setText(cfo.getClient_idcard()); //数据库加载信息
		//客户手机按钮
		JLabel lblNewLabel5 = new JLabel("客户手机 ：");
		lblNewLabel5.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel5.setBounds(105, 152, 126, 30);
		contentPane.add(lblNewLabel5);
		
		client_phone = new JTextField();			//手机号输入框
		client_phone.setBounds(235, 159, 152, 20);
		contentPane.add(client_phone);
		client_phone.setColumns(15);
		client_phone.setText(cfo.getClient_phone());  //数据库加载信息
		
		
		JLabel lbladdr = new JLabel("客户地址 ：");
		lbladdr.setFont(new Font("新宋体", Font.BOLD, 20));
		lbladdr.setBounds(105, 192, 126, 30);
		contentPane.add(lbladdr);
		
		addrField = new JTextField();			//客户地址输入框
		addrField.setBounds(235, 199, 152, 20);
		contentPane.add(addrField);
		addrField.setColumns(10);
		addrField.setText(cfo.getAddr()); //数据库加载信息
		
		
		//返回监听时间
	JButton backButton_1 = new JButton("返回");
	backButton_1.setFont(new Font("新宋体", Font.BOLD, 18));
	backButton_1.setBounds(290, 275, 113, 27);
	contentPane.add(backButton_1);
	backButton_1.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			UmanageFrame uf = new UmanageFrame(name,client_id1);
			setVisible(false);
			uf.setVisible(true);			
		}		
	});
	
				//编辑按钮监听
			JButton editButton = new JButton("编辑");
		editButton.setFont(new Font("新宋体", Font.BOLD, 18));
		editButton.setBounds(152, 275, 113, 27);
		contentPane.add(editButton);
		editButton.addActionListener(new editButtonAction());
	}
	
	private void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		//定义菜单目录
		JMenu oper_JM = new JMenu("操作");
		JMenu set_JM = new JMenu("设置");
		
		oper_JM.setMnemonic(KeyEvent.VK_F);
		JMenuItem oper_exit_JMitem = new JMenuItem("退出", KeyEvent.VK_X);
		JMenuItem oper_reback_JMitem = new JMenuItem("返回", KeyEvent.VK_X);
		JMenuItem set_chuser_JMitem = new JMenuItem("更改账号");
		JMenuItem set_pwd_JMitem = new JMenuItem("更改密码");
		JMenuItem set_ad_JMitem = new JMenuItem("后台");
		 
		set_JM.add(set_chuser_JMitem);
		set_JM.add(set_ad_JMitem);
		set_JM.add(set_pwd_JMitem);
		//菜单栏加入菜单名
		menuBar.add(oper_JM);
		menuBar.add(set_JM);
		//加入菜单功能
		setJMenuBar(menuBar);
		oper_JM.add(oper_exit_JMitem);
		oper_JM.add(oper_reback_JMitem);
		// 设置点击退出后可以退出
		oper_exit_JMitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		oper_reback_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(name,client_id1);
				setVisible(false);
				af.setVisible(true);
			}
		});
		set_chuser_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);
			}
		});
		
		set_ad_JMitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strid = JOptionPane.showInputDialog("请输入超管口令");
                if (strid.equals("root"))
                {
                	AdminFrame af = new AdminFrame(name,client_id1);
    				setVisible(false);
    				af.setVisible(true);
                }
//				Object[] possibleValues = { "客户经理", "维护", "数据" }; 
//				Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "后台", 
//				JOptionPane.INFORMATION_MESSAGE, null, 
//				possibleValues, possibleValues[0]);
			} 
	});
		set_pwd_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ModifpwdFrame mpf = new ModifpwdFrame(name,client_id1);
				setVisible(false);
				mpf.setVisible(true);
			}
		});
		
		
		
	}
	
	
	//个人信息编辑按钮监听器	
		class editButtonAction implements ActionListener {
			AccountServiceImpl asi =new AccountServiceImpl();
//			public void editButtonAction(final String name){
//			}
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				cfo = asi.cquery(client_id1);
				System.out.println(client_id1);
				try {
					bu = asi.bu_query(name);  //传入UID
					System.out.println(name);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (!client_name.getText().equals("")) {
					if (!client_idcard.getText().equals("")) {
						if (!client_phone.getText().equals("")) {
							if (!addrField.getText().equals("")) {  
//								if (cfo.getClient_idcard()==null) {   //  !u_name.getText().trim().equals(user.getName())                             //  !u_name.getText().equals(user.getName())
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：个人信息编辑；责任人：业务员19"+"\n";	
								asi.updateClientinfo(client_id1,bu.getUid(),client_name.getText(),client_idcard.getText(),client_phone.getText(),addrField.getText());
									setVisible(true);
									string=string+"编辑成功,可以继续使用！";
									JOptionPane.showMessageDialog(null, "编辑成功,可以继续使用！");
//								} else {
//									JOptionPane.showMessageDialog(null, "身份证已存在！");
//								}
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：个人信息编辑；责任人：用户"+"\n";
								string=string+"未输入地址！";
								JOptionPane.showMessageDialog(null, "未输入地址！");
							}

						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：个人信息编辑；责任人：用户"+"\n";
							string=string+"未输入手机号！";
							JOptionPane.showMessageDialog(null, "未输入手机号！");
						}
				

					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：个人信息编辑；责任人：用户"+"\n";
						string=string+"未输入身份证！";
						JOptionPane.showMessageDialog(null, "未输入身份证！");
					}
				} else {
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df1.format(new Date());
					string=string+"当前程序：个人信息编辑；责任人：用户"+"\n";
					string=string+"您未输入姓名";
					JOptionPane.showMessageDialog(null, "您未输入姓名");
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


		public void hyperlinkUpdate(HyperlinkEvent e) {
			
		}
		public void actionPerformed(ActionEvent e) {
			
		}
				
	}
		

