package com.shao.iframe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


















import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.bill.PaymentFrame;
import com.shao.iframe.cardManage.CardManageFrame;
import com.shao.iframe.operation.OperationFrame;
import com.shao.iframe.query.QueryFrame;
import com.shao.iframe.umanage.UmanageFrame;
import com.shao.model.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * @author HGX
 *表示层
 *超管后台管理主界面 
 *
 */
public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> choosebill;	
	private static JTextArea jt_showdata;
	static Statement stmt = null;
	static Connection con = null;
	static ResultSet rs = null;
	public static Timestamp daTs;
	private JTextField 	username_Field;

	public AdminFrame(final String name,final String client_id) {
		
		final AccountServiceImpl asi = new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 520);
		contentPane = new JPanel();
		setTitle("数据查询");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblchoose = new JLabel("查询选择:");
		lblchoose.setFont(new Font("新宋体", Font.BOLD, 20));
		lblchoose.setBounds(30, 100, 113, 35);
		contentPane.add(lblchoose);

		choosebill = new JComboBox();
		choosebill.setBounds(140, 107, 130, 21);
		choosebill.addItem("注册数");
		choosebill.addItem("挂失数");
		choosebill.addItem("绑卡数");
		choosebill.addItem("转账总数");
		choosebill.addItem("跨行转账总数");
		choosebill.addItem("取款总数");
		choosebill.addItem("生活缴费总额");
		choosebill.addItem("信用卡消费");
		
		contentPane.add(choosebill);
		// cre_closepay.addActionListener();
		choosebill.addActionListener(new choosebillAction());
		
		JLabel lblrestrictive = new JLabel("限制账户:");
		lblrestrictive.setFont(new Font("新宋体", Font.BOLD, 20));
		lblrestrictive.setBounds(30, 140, 113, 35);
		contentPane.add(lblrestrictive);
		
		username_Field = new JTextField();  //账户名输入框
		username_Field.setBounds(140, 144, 117, 24);
		contentPane.add(username_Field);
		username_Field.setColumns(10);
		
		
		
		jt_showdata = new JTextArea(10,20);
		jt_showdata.setBounds(20,200,250,100);
		jt_showdata.setLineWrap(true);  //自动换行
		jt_showdata.setFont(new Font("谐体",Font.BOLD|Font.ITALIC,16));
        // 添加到内容面板
		contentPane.add(jt_showdata);
		
		
		JButton relieveBtn = new JButton("解除限制");
		relieveBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		relieveBtn.setBorderPainted(true); // 无边框
		relieveBtn.setOpaque(false); // 设置按钮透明
		relieveBtn.setContentAreaFilled(false); // 关闭底色
		relieveBtn.setMargin(new Insets(0,0,0,0));
		relieveBtn.setBounds(80, 440, 140, 20);
		contentPane.add(relieveBtn);
		relieveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				asi.upuser_status3(username_Field.getText());
				JOptionPane.showMessageDialog(null, "解除成功！");
			}
		});
		
		JButton restrictiveBtn = new JButton("限制交易");
		restrictiveBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		restrictiveBtn.setBorderPainted(true); // 无边框
		restrictiveBtn.setOpaque(false); // 设置按钮透明
		restrictiveBtn.setContentAreaFilled(false); // 关闭底色
		restrictiveBtn.setMargin(new Insets(0,0,0,0));
		restrictiveBtn.setBounds(80, 390, 140, 20);
		contentPane.add(restrictiveBtn);
		restrictiveBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				asi.upuser_status2(username_Field.getText());
				JOptionPane.showMessageDialog(null, "限制成功！");
			}
		});
		
		
		JButton resetBtn = new JButton("重置");
		resetBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		resetBtn.setBorderPainted(true); // 无边框
		resetBtn.setOpaque(false); // 设置按钮透明
		resetBtn.setContentAreaFilled(false); // 关闭底色
		resetBtn.setMargin(new Insets(0,0,0,0));
		resetBtn.setBounds(160, 340, 110, 20);
		contentPane.add(resetBtn);
		resetBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 clear();
			}
		});
		
		
		JButton reback = new JButton("返回");
		reback.setBounds(20, 340, 113, 20);
		reback.setBorderPainted(true); // 无边框
		reback.setOpaque(false); // 设置按钮透明
		reback.setForeground(Color.BLACK); // 设置组件字体
		reback.setContentAreaFilled(false); // 关闭底色
		// forgetBtn.setFocusPainted(false); //聚焦线
		reback.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(reback);
		reback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(name,client_id);
				setVisible(false);
				af.setVisible(true);			
			}		
		});

	}
	// 连接数据库
	public static void connDB() { 
		InputStream in = AdminFrame.class.getClassLoader().getResourceAsStream("database.properties");
		System.out.println(in);
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String driverClass=pro.getProperty("driverClass");
		String url = pro.getProperty("url");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = (Connection) DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	}

	public static void closeDB() // 关闭连接
	{
		try {
			rs.close();
			stmt.close();
		    con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		AdminFrame af = new AdminFrame(null,null);
		af.setVisible(true);
	}
	
	

	class choosebillAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			connDB();
			Object source = e.getSource();
			String Strchoose=choosebill.getSelectedItem().toString();
			System.out.println(con);
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			Date dadate = new Date();//获得系统时间
			String nowDaDate = sdf.format(dadate);
			Timestamp daTs = Timestamp.valueOf(nowDaDate);
			
			if(Strchoose.equals("注册数")){
			String sql = "select count(1) as countnumber from bank_user group by userstatus";
			System.out.println(sql);
			Statement stat;
			try {
				stat = (Statement) con.createStatement();
				ResultSet rs;
				rs = stat.executeQuery(sql);
				while (rs.next()) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df.format(new Date());
					string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
					string=string+"截止"+daTs+"\n"+"注册数共计："+rs.getString(1)+"\n";
					System.out.println(rs.getString("countnumber"));
					//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
					jt_showdata.setText("截止"+daTs+"\n"+"注册数共计："+rs.getString(1));
				}
//				closeDB();
//				repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			}else if (Strchoose.equals("挂失数")){
				String sql = "select count(1) as losenumber from bank_user where userstatus=2";
				System.out.println(sql);
				Statement stat;
				try {
					stat = (Statement) con.createStatement();
					ResultSet rs;
					rs = stat.executeQuery(sql);
					while (rs.next()) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df.format(new Date());
						string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
						string=string+"截止"+daTs+"\n"+"挂失数共计："+rs.getString(1)+"\n";
						System.out.println(rs.getString("losenumber"));
						//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
						jt_showdata.setText("截止"+daTs+"\n"+"挂失数共计："+rs.getString(1));
					}
//					closeDB();
//					repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
			}
		}else if (Strchoose.equals("绑卡数")){
			String sql = "select count(*) as bkdnumber from bankcard";
			System.out.println(sql);
			Statement stat;
			try {
				stat = (Statement) con.createStatement();
				ResultSet rs;
				rs = stat.executeQuery(sql);
				while (rs.next()) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df.format(new Date());
					string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
					string=string+"截止"+daTs+"\n"+"绑卡数共计："+rs.getString(1)+"\n";
					System.out.println(rs.getString("bkdnumber"));
					//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
					jt_showdata.setText("截止"+daTs+"\n"+"绑卡数共计："+rs.getString(1));
				}
//				closeDB();
//				repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			
		}
	}else if(Strchoose.equals("转账总数")){
		String sql = " select t.client_id,sum(t.trade_money) from TRANSFER t join client_info c on t.client_id = c.client_id";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df.format(new Date());
				string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
				string=string+"截止"+daTs+"\n"+"转账总金额:"+rs.getString("sum(t.trade_money)")+"\n";
				System.out.println(rs.getString("t.client_id")+"\n " +rs.getString("sum(t.trade_money)"));
				//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
				jt_showdata.setText("截止"+daTs+"\n"+"转账总金额:"+rs.getString("sum(t.trade_money)"));
			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
	}else if(Strchoose.equals("跨行转账总数")){
		String sql = " select sum(t.trade_money) from TRANSFER t join client_info c on t.client_id = c.client_id where int_out=2";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df.format(new Date());
				string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
				string=string+"截止"+daTs+"\n"+"跨行转账总金额:"+rs.getString("sum(t.trade_money)")+"\n";
				//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
				jt_showdata.setText("截止"+daTs+"\n"+"跨行转账总金额:"+rs.getString("sum(t.trade_money)"));
			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		
	}else if(Strchoose.equals("取款总数")){
		String sql = "select sum(wid_pre_money) as 交易金额,sum(wid_interest) as 交易利息总额 from WITHDRAWAL";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df.format(new Date());
				string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
				string=string+"截止"+daTs+"\n"+"取款总数 : "+rs.getString("交易金额")+"\n";
				//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
				jt_showdata.setText("截止"+daTs+"\n"+"取款总数 : "+rs.getString("交易金额"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		
	}else if(Strchoose.equals("生活缴费总额")){
		String sql = "select sum(con_money) as 生活缴费总额 from CONSUMPTION";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df.format(new Date());
				string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
				string=string+"截止"+daTs+"\n"+"生活缴费总额 : "+rs.getString("生活缴费总额")+"\n";
				//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
				jt_showdata.setText("截止"+daTs+"\n"+"生活缴费总额 : "+rs.getString("生活缴费总额"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		
	}else if(Strchoose.equals("信用卡消费")){
		String sql = " select sum(crecon_money) as 信用卡消费总额 from CREDIT_CONSUMPTION";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df.format(new Date());
				string=string+"当前程序：超管后台管理；责任人：后台进程"+"\n";
				string=string+"截止"+daTs+"\n"+"信用卡消费总数 : "+rs.getString("信用卡消费总额")+"\n";
				//					JOptionPane.showMessageDialog(null, "注册数共计：" +rs.getString(1));
				jt_showdata.setText("截止"+daTs+"\n"+"信用卡消费总数 : "+rs.getString("信用卡消费总额"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		closeDB();
		repaint();	
	
	}
			try {
				FileWriter writer = new FileWriter("log.txt", true);
	            writer.write(string);
	            writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i=choosebill.getSelectedIndex()+1;
//			String s1=(String)choosebill.getSelectedItem();
			System.out.println("你选中的是第"+i+"项"+",内容是:"+Strchoose);
	
		}
		
	}
	private void clear() {
		jt_showdata.setText("");    //设置为空
	}
	
	
}
