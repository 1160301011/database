package com.shao.iframe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.model.*;
import com.shao.tool.ValidCode;

import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
/**
 * @author HGX
 *表示层
 *注册账户界面 
 *
 */
public class SignFrame extends JFrame implements ActionListener, HyperlinkListener{
	JMenuBar mentuBar;
	JMenuItem skin1,skin2,fileMenu2;
	// 用于选择皮肤时传递参数
	Object ob;
	private JEditorPane pane;
	private JPanel contentPane;				
	private JTextField u_name;
	private JPasswordField u_password;
	private JPasswordField u_password_1;
	private Bankuser bu;
	private User user;
	private JLabel validcode = new JLabel("验证码：");
	private JTextField jtf_code = new JTextField();         /* 验证码输入框*/
	private ValidCode vcode = new ValidCode();
	


	public SignFrame() {
		
		MenuBar();
		/*
		 * 定义显示网页的部分
		 */
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// 设置pane的超级链接监听
		pane.addHyperlinkListener(this);
		this.add(new JScrollPane(pane), "Center");
		
		setTitle("注册界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("用户名 :");
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel.setBounds(74, 78, 102, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密  码 :");
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_1.setBounds(74, 149, 88, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("确认密码 :");
		lblNewLabel_2.setFont(new Font("新宋体", Font.BOLD, 18));
		lblNewLabel_2.setBounds(70, 211, 101, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel validcode = new JLabel("验证码  :");
		validcode.setFont(new Font("新宋体", Font.BOLD, 18));
		validcode.setBounds(74, 275, 101, 28);
		contentPane.add(validcode);
		
		vcode.setBounds(197, 315, 123, 30);
		contentPane.add(vcode);
		
		u_name = new JTextField();
		u_name.setBounds(187, 85, 123, 24);
		contentPane.add(u_name);
		u_name.setColumns(10);
		
		u_password = new JPasswordField();
		u_password.setBounds(187, 153, 123, 24);
		contentPane.add(u_password);
		
		u_password_1 = new JPasswordField();
		u_password_1.setBounds(187, 215, 123, 24);
		contentPane.add(u_password_1);
		
		jtf_code = new JTextField();
		jtf_code.setBounds(187, 275, 123, 24);
		contentPane.add(jtf_code);
		
		JButton OKButton = new JButton("注册");  //注册按钮设置
		OKButton.setFont(new Font("新宋体", Font.BOLD, 20));
		OKButton.setBounds(150, 397, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new OKButtonAction());
	}
	

	private void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("操作");
		JMenu skin = new JMenu("更换皮肤");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileExitMenuItem = new JMenuItem("退出", KeyEvent.VK_X);
		fileMenu2= new JMenuItem("返回");
		skin1 = new JMenuItem("VIP SVIP 了解一下");
		skin.add(skin1);
		menuBar.add(fileMenu);
		menuBar.add(skin);
		setJMenuBar(menuBar);
		fileMenu.add(fileExitMenuItem);
		fileMenu.add(fileMenu2);
		// 设置点击退出后可以退出
		fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);
			}
		});
	}


	//注册按钮监听器
	class OKButtonAction implements ActionListener {
		
		AccountServiceImpl asi =new AccountServiceImpl();
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			  user = asi.query(u_name.getText());
			  Bankinfo bif = new Bankinfo();
			  try {
				bu =asi.bu_query(u_name.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		if(!jtf_code.getText().equals("")){
			if(!vcode.getCode().equals(jtf_code.getText())) {
			  if (!u_name.getText().equals("")) {
					if (!u_password.getText().equals("")) {
						if (!u_password_1.getText().equals("")) {
							if (u_password.getText().equals(u_password_1.getText())) {                              
								if (bu==null) {   //  !u_name.getText().trim().equals(user.getName())                             //  !u_name.getText().equals(user.getName())
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									string=string+df.format(new Date());
									string=string+"当前程序：注册；当前责任人：业务员2"+"\n";
									string=string+u_name.getText()+" 注册成功,可以继续登录！"+"\n";
									asi.addUser(u_name.getText(),u_password.getText());
									asi.addClientinfo2(u_name.getText());
									setVisible(false);
									LoginFrame frame = new LoginFrame();
									frame.setVisible(true);
									JOptionPane.showMessageDialog(null, "注册成功,可以继续登录！");
								} else {
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									string=string+df.format(new Date());
									string=string+"当前程序：注册；当前责任人：用户"+"\n";
									string=string+u_name.getText()+" 用户名已经存在！"+"\n";
									JOptionPane.showMessageDialog(null, "用户名已经存在！");
								}
							} else {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df.format(new Date());
								string=string+"当前程序：注册；当前责任人：用户"+"\n";
								string=string+u_name.getText()+" 密码确认不符！"+"\n";
								JOptionPane.showMessageDialog(null, "密码确认不符！");
							}

						} else {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df.format(new Date());
							string=string+"当前程序：注册；当前责任人：用户"+"\n";
							string=string+u_name.getText()+" 未输入确认密码！"+"\n";
							JOptionPane.showMessageDialog(null, "未输入确认密码！");
						}

					} else {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df.format(new Date());
						string=string+"当前程序：注册；当前责任人：用户"+"\n";
						string=string+u_name.getText()+" 未输入密码！"+"\n";
						JOptionPane.showMessageDialog(null, "未输入密码！");
					}
				} else {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df.format(new Date());
					string=string+"当前程序：注册；当前责任人：用户"+"\n";
					string=string+u_name.getText()+" 未输入用户名！"+"\n";
					JOptionPane.showMessageDialog(null, "未输入用户名！");
				}
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df.format(new Date());
					string=string+"当前程序：注册；当前责任人：用户"+"\n";
					string=string+u_name.getText()+" 您输入的验证码有误！"+"\n";
					JOptionPane.showMessageDialog(null, "您输入的验证码有误！");
				}
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df.format(new Date());
					string=string+"当前程序：注册；当前责任人：用户"+"\n";
					string=string+u_name.getText()+" 未输入验证码！"+"\n";
					JOptionPane.showMessageDialog(null, "未输入验证码！");
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
	
	public boolean isValidCodeRight() {
		if(jtf_code == null) {
			return false;
		}else if(vcode == null) {
			return true;
		}else if(vcode.getCode().equals(jtf_code.getText())) {
			return true;
		}else 
			return false;
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Create the frame.
	 * @return 
	 */
	}

	

