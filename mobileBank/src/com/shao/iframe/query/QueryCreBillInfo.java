package com.shao.iframe.query;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
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
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.InitialContext;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.bill.PaymentFrame;
import com.shao.iframe.bill.Supermaket;
import com.shao.model.*;

import org.omg.PortableInterceptor.SUCCESSFUL;
/**
 * @author HGX
 *表示层
 *查询信用卡界面 
 *
 */
public class QueryCreBillInfo extends JFrame implements ActionListener {
	final AccountServiceImpl asi =new AccountServiceImpl();
	private DefaultTableModel defaultTableModel;
	private JComboBox<String> crecard_choose;	
	JTable table;
	JLabel lblchoose_crecard,label1,lblbankcard;
	Object a[][];
	Object name[] = {"信用卡序列号","卡号","总额度","可用额度","本月账单","历史账单"};
	JButton buttonOfKe,btnbrowse,btncompu_money,btnreback,btnrepayment;
	Box box1,box2;
	JTextField chocard_field,re_money_field,bankcard_field;
	JPanel jPanel4,jPanel5;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	static String username =null;
	static String client_id = null ;
	private com.shao.model.Creditcard crd;
	private com.shao.model.Bankcard bkd;
	private com.shao.model.Bankuser bu;
	
	public QueryCreBillInfo(final String username,final String client_id)
	{
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		
		init();
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 720, 550);
		setTitle("尊敬的:"+username+"用户界面");
		this.username = username;
		this.client_id = client_id;
	}
	
	void init()
	{
		defaultTableModel = new DefaultTableModel(a,name); // 用双数组创建DefaultTableModel对象
		label1 = new JLabel("信用卡账单浏览");
		btnbrowse = new JButton("账单浏览 ");
		btnbrowse .addActionListener(this);
		btnreback = new JButton("返        回");
		btnreback.addActionListener(this);
		lblchoose_crecard = new JLabel("选择信用卡号：");
		//信用卡选择列表
		chocard_field = new JTextField();
//		crecard_choose.addItem(crd.getCre_id());
		
		btncompu_money = new JButton("应还额度");
		btncompu_money.addActionListener(this);
		re_money_field = new JTextField();
		
		lblbankcard = new JLabel("选择还款卡号");
		bankcard_field = new JTextField();
		btnrepayment = new JButton("一键还款");
		btnrepayment.addActionListener(this);
		
		
		a = new Object[50][6];
		table = new JTable(a, name);//组件的创建
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(100, 100, 500, 500);
		//设置列宽
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		// 设置表格间隔色
	     DefaultTableCellRenderer ter = new DefaultTableCellRenderer()
	        {
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                    boolean hasFocus, int row, int column) {
	                // table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
	                if (row % 2 == 0)
	                    setBackground(Color.pink);
	                else if (row % 2 == 1)
	                    setBackground(Color.white);
	                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            }
	        };
	        for (int i = 0; i < 6; i++) {
	            table.getColumn(name[i]).setCellRenderer(ter);
	        }
	        
		
		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(20));
//		box1.add(Box.createHorizontalStrut(100)); 
		
		box1.add(btnbrowse);
		box1.add(Box.createVerticalStrut(10));
//		box1.add(Box.createHorizontalStrut(100)); 
		
		box1.add(btnreback);
		box1.add(Box.createVerticalStrut(50));
		
		box1.add(lblchoose_crecard);
		box1.add(Box.createVerticalStrut(5));
		
		box1.add(chocard_field);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(btncompu_money);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(re_money_field);
		box1.add(Box.createVerticalStrut(50));
		
		box1.add(lblbankcard);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(bankcard_field);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(btnrepayment);
		box1.add(Box.createVerticalStrut(10));
		
		
		box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(15));
//		box2.add(Box.createHorizontalStrut(100)); 
		box2.add(box1);   //左边的按钮部分用 box布局
		

		jPanel4 = new JPanel();
		jPanel5 = new JPanel();
		jPanel4.setLayout(new BorderLayout());
		jPanel4.add(box2,BorderLayout.NORTH);//把左边的按钮部分放到jpanel4中。
//		
		jPanel5.setLayout(new BorderLayout());
		jPanel5.add(label1,BorderLayout.NORTH);
		jPanel5.add(scrollPane,BorderLayout.CENTER);//把表格 放jpanel5里
//		jPanel5.add(box1.createHorizontalStrut(50));
		
		this.setLayout(new BorderLayout());
		add(jPanel5,BorderLayout.CENTER);
		add(jPanel4,BorderLayout.WEST);//把两个大的panel放到窗口里面
	}

	public void connDB() { // 连接数据库
		try {
			Class.forName("com.mysql.jdbc.Driver");//注册驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {//创建连接
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobilebank3?characterEncoding=utf-8", "root", "root");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void closeDB() // 关闭连接
	{
		try {
			stmt.close();
			con.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

		public void xinXiLiuLan()
		{
			int i=0;
			 while(i<50)
			 {
				 
				 a[i][0]=" ";
				 a[i][1]=" ";
				 a[i][2]=" ";
				 a[i][3]=" ";
				 a[i][4]=" ";
				 a[i][5]=" ";
				 i++;
			 }
			 i=0;
			 this.connDB();
			 try {
				stmt = con.createStatement();
				bu = asi.bu_query(username);
				String buid = bu.getUid();
				System.out.println(buid);
				 String sql= "select * from credit_card where uid ="+buid+"";
				 rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
					 String crecon_serial = rs.getString("cre_serial");
					 String cre_id = rs.getString("cre_id");
					 String cre_balance = rs.getString("cre_balance");
					 String cre_available = rs.getString("cre_available");
					 String this_month_money = rs.getString("this_month_money");
					 String  his_month_money= rs.getString("his_month_money");
					 a[i][0]=crecon_serial;
					 a[i][1]=cre_id;
					 a[i][2]=cre_balance;
					 a[i][3]=cre_available;
					 a[i][4]=this_month_money;
					 a[i][5]=his_month_money;
					 i++;
					 
				 }
				 this.closeDB();
				 repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 this.closeDB();
		}
		

	
	public void actionPerformed(ActionEvent e) {
		String string=new String();
		Object source = e.getSource();
		if(source == btnbrowse)//点击信息浏览按钮
		{
			 int i=0;
			 while(i<50)
			 {
				 a[i][0]=" ";
				 a[i][1]=" ";
				 a[i][2]=" ";
				 a[i][3]=" ";
				 a[i][4]=" ";
				 a[i][5]=" ";
				 i++;
			 }
			 i=0;
			 this.connDB();
			 try {
				stmt = con.createStatement();
				bu = asi.bu_query(username);
				String buid = bu.getUid();
				System.out.println(buid);
				 String sql= "select * from credit_card where uid ="+buid+"";
				 rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
					 String crecon_serial = rs.getString("cre_serial");
					 String cre_id = rs.getString("cre_id");
					 String cre_balance = rs.getString("cre_balance");
					 String cre_available = rs.getString("cre_available");
					 String this_month_money = rs.getString("this_month_money");
					 String  his_month_money= rs.getString("his_month_money");
					 a[i][0]=crecon_serial;
					 a[i][1]=cre_id;
					 a[i][2]=cre_balance;
					 a[i][3]=cre_available;
					 a[i][4]=this_month_money;
					 a[i][5]=his_month_money;
					 
					 i++;
				 }
				 this.closeDB();
				 repaint();
			} catch (SQLException e1){
				e1.printStackTrace();
			}
		}
		if(source == btnreback)//点击返回按钮
		{
				QueryFrame qf = new QueryFrame(username,client_id);
				setVisible(false);
				qf.setVisible(true);			
			}		
		if(source == btncompu_money)//点击应还金额按钮
		{
//			final AccountServiceImpl asi =new AccountServiceImpl();
			crd = asi.query_cre_card(chocard_field.getText());
			double temp =crd.getCre_balance()-crd.getCre_available();
			 DecimalFormat df = new DecimalFormat("0.00 ");
             
			 re_money_field.setText(temp+"");
		}
		
		if(source == btnrepayment)//点击一键还款按钮
		{
			crd = asi.query_cre_card(chocard_field.getText());
			bkd = asi.query_bankcard(bankcard_field.getText());
			//判断如果还款卡号余额大于应还款的额度继续执行
			if (bkd.getCurrent() > Double.parseDouble(re_money_field.getText())) {
				//储蓄卡扣款
			double temp =bkd.getCurrent()-Double.parseDouble(re_money_field.getText());
			asi.upbankcard_balance(bankcard_field.getText(),temp);
				//信用卡额度恢复初始申请额度
			asi.upcredit_card_available(crd.getCre_balance(),chocard_field.getText());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			string=string+df1.format(new Date());
			string=string+"当前程序：还款；责任人：业务员17"+"\n";
			string=string+"还款成功！"+"\n";
			JOptionPane.showMessageDialog(null, "还款成功！");
//			crd = asi.query_cre_card(chocard_field.getText());
//			
//			 DecimalFormat df = new DecimalFormat("0.00 ");
             
//			 re_money_field.setText(df.format(temp));
			}else{
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df1.format(new Date());
				string=string+"当前程序：取款；责任人：用户"+"\n";
				string=string+"余额不足，请更换卡号！" + "\n" + "当前余额为：" + bkd.getCurrent()+"\n";
				JOptionPane.showMessageDialog(null, "余额不足，请更换卡号！" + "\n" + "当前余额为：" + bkd.getCurrent());
				bankcard_field.setText("");
			}
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
	
//	public static void main(String[] args) {
//		QueryCreBillInfo skt = new QueryCreBillInfo(username,client_id);
//	}
	
}
