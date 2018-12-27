package com.shao.iframe.umanage;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.Service.*;
import com.shao.iframe.LoginFrame;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.bill.PaymentFrame;
import com.shao.iframe.cardManage.CardManageFrame;
import com.shao.iframe.operation.OperationFrame;
import com.shao.iframe.query.QueryFrame;
import com.shao.model.*;
/**
 * @author HGX
 *表示层
 *账户管理主界面 
 *
 */
public class UmanageFrame extends JFrame implements ActionListener, HyperlinkListener {
	public String name;
	private JPanel contentPane;
	private User user;
	private Clientinfo clientinfo;
	private Bankinfo bif;
	private JButton modifyBtn;
	JMenuBar mentuBar;
	JMenuItem skin1;
	JMenuItem skin2;
	private JEditorPane pane;
	// 用于选择皮肤时传递参数
	Object ob;
	public String client_id;
	
	public UmanageFrame(final String name, final String client_id) {
		
		this.name = name;
		this.client_id = client_id;
		
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
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		
		JFrame jFrame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		setTitle(name+"  ：账户管理");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		modifyBtn = new JButton("修改密码");
		modifyBtn.setFont(new Font("新宋体", Font.BOLD, 20));
		modifyBtn.setMargin(new Insets(0,0,0,0));
		modifyBtn.setBounds(80, 100, 135, 30);
		contentPane.add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ModifpwdFrame of = new ModifpwdFrame(name, client_id);
				setVisible(false);
				of.setVisible(true);
			}
		});
		
		JButton CreCfoButton = new JButton("编辑用户信息");
		CreCfoButton.setFont(new Font("新宋体", Font.BOLD, 20));
		CreCfoButton.setMargin(new Insets(0,0,0,0));
		CreCfoButton.setBounds(68, 200, 160, 27);
		contentPane.add(CreCfoButton);
		CreCfoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddClientinfo cf = new AddClientinfo(name,client_id);
				setVisible(false);
				cf.setVisible(true);
			}
		});
		
		JButton addpayeeBtn = new JButton("添加收款人");
		addpayeeBtn.setFont(new Font("新宋体", Font.BOLD, 20));
		addpayeeBtn.setMargin(new Insets(0,0,0,0));
		addpayeeBtn.setBounds(80, 300, 145, 27);
		contentPane.add(addpayeeBtn);
		addpayeeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddPayee py = new AddPayee(name,client_id);
				setVisible(false);
				py.setVisible(true);
			}
		});
		
		
	}
	
	private void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		//定义菜单目录
		JMenu oper_JM = new JMenu("操作");
		JMenu chuser_JM = new JMenu("设置");
		
		oper_JM.setMnemonic(KeyEvent.VK_F);
		JMenuItem oper_exit_JMitem = new JMenuItem("退出", KeyEvent.VK_X);
		JMenuItem oper_reback_JMitem = new JMenuItem("返回", KeyEvent.VK_X);
		JMenuItem chuser_JMitem = new JMenuItem("更改账号");
		 
		chuser_JM.add(chuser_JMitem);
		//菜单栏加入菜单名
		menuBar.add(oper_JM);
		menuBar.add(chuser_JM);
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
				AtmFrame af = new AtmFrame(name,client_id);
				setVisible(false);
				af.setVisible(true);
			}
		});
		chuser_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);
			}
		});
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
		
	}
	

}
