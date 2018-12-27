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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.FieldPosition;

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
 *查询收款人界面 
 *
 */
public class QryPyeFrame extends JFrame implements ActionListener {
	final AccountServiceImpl asi = new AccountServiceImpl();
	private DefaultTableModel defaultTableModel;
	JTable table;      //建立表格
	JLabel lbltitle,lblpyename,lblpyecard,lblbelong,lbladd,lbldelete,lbldelpyeid;  //标题 添加 删除标签
	Object data_pye[][];  //收款人数组存放数据
	//表头名
	Object title_name[] = {"收款人ID", "账户ID", "收款人姓名", "收款人卡号","所属银行"};
	JButton browsebtn, addbtn, delebtn, rebackbtn;
	Box box1, box2;
	JTextField name_field,card_field,belong_field,delpyeid_field;
	JPanel jPanel4, jPanel5;
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	public String username;
	public String client_id;
	private com.shao.model.Bankcard bkd;
	private com.shao.model.Payee pye;
	private com.shao.model.Bankuser bu;
	
	public QryPyeFrame(final String username, final String client_id) {
		this.username = username;
		this.client_id = client_id;
		
		init();
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 720, 550);
		setTitle("尊敬的:"+username+"用户界面");
	}
	void init()
	{
		defaultTableModel = new DefaultTableModel(data_pye,title_name); // 用双数组创建DefaultTableModel对象
		
		lbltitle = new JLabel("收款人浏览");
		browsebtn = new JButton("点击浏览 ");
		browsebtn .addActionListener(this);
		
		rebackbtn = new JButton("返        回");
		rebackbtn.addActionListener(this);
		
		
		lblpyename = new JLabel("收款名：");
		name_field = new JTextField();
		
		lblpyecard = new JLabel("收款卡号：");
		card_field = new JTextField();
		
		lblbelong = new JLabel("卡号所属银行：");
		belong_field = new JTextField();
		
		
		addbtn = new JButton("添加");
		addbtn.addActionListener(this);
		
		
		lbldelpyeid = new JLabel("要删除的收款人ID：");
		delpyeid_field = new JTextField();
		
		delebtn = new JButton("删除");
		delebtn.addActionListener(this);
		
		
		data_pye = new Object[50][5];
		table = new JTable(data_pye, title_name);//组件的创建
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(100, 100, 500, 500);
		//设置列宽
//		table.getColumnModel().getColumn(0).setPreferredWidth(80);
//		table.getColumnModel().getColumn(1).setPreferredWidth(80);
//		table.getColumnModel().getColumn(2).setPreferredWidth(45);
//		table.getColumnModel().getColumn(3).setPreferredWidth(45);
//		table.getColumnModel().getColumn(4).setPreferredWidth(40);
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
	        for (int i = 0; i < 5; i++) {
	            table.getColumn(title_name[i]).setCellRenderer(ter);
	        }
	        
		
		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(20));
//		box1.add(Box.createHorizontalStrut(100)); 
		
		box1.add(browsebtn);
		box1.add(Box.createVerticalStrut(10));
//		box1.add(Box.createHorizontalStrut(100)); 
		
		box1.add(rebackbtn);
		box1.add(Box.createVerticalStrut(50));
		
		box1.add(lblpyename);
		box1.add(Box.createVerticalStrut(5));
		
		box1.add(name_field);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(lblpyecard);
		box1.add(Box.createVerticalStrut(5));
		
		box1.add(card_field);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(lblbelong);
		box1.add(Box.createVerticalStrut(5));
		
		box1.add(belong_field);
		box1.add(Box.createVerticalStrut(8));
		
		box1.add(addbtn);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(lbldelpyeid);
		box1.add(Box.createVerticalStrut(5));
		
		box1.add(delpyeid_field);
		box1.add(Box.createVerticalStrut(8));
		
		box1.add(delebtn);
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
		jPanel5.add(lbltitle,BorderLayout.NORTH);
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

//		public void xinXiLiuLan()
//		{
//			int i=0;
//			 while(i<50)
//			 {
//				 
//				 data_pye[i][0]=" ";
//				 data_pye[i][1]=" ";
//				 data_pye[i][2]=" ";
//				 data_pye[i][3]=" ";
//				 data_pye[i][4]=" ";
//				 i++;
//			 }
//			 i=0;
//			 this.connDB();
//			 try {
//				 bu = asi.bu_query(username);
//				 String st = bu.getUid();
//				 System.out.println(st);
////				 pye = asi.pye_query_QPY(bu.getUid());
//				 stmt = con.createStatement();
//				 String sql= "select * from payee where uid = "+st+"";
//				 rs = stmt.executeQuery(sql);
//				 while(rs.next())
//				 {
//					 String payee_id = rs.getString("payee_id");
//					 String uid = rs.getString("uid");
//					 String payee_name = rs.getString("payee_name");
//					 String payee_card = rs.getString("payee_card");
//					 String belong_bank = rs.getString("belong_bank");
//					 data_pye[i][0]=payee_id;
//					 data_pye[i][1]=uid;
//					 data_pye[i][2]=payee_name;
//					 data_pye[i][3]=payee_card;
//					 data_pye[i][4]=belong_bank;
//					 i++;
//					 
//				 }
//				 this.closeDB();
//				 repaint();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			 this.closeDB();
//		}
//		

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	
		if(source == browsebtn)//点击信息浏览按钮
		{
			int i=0;
			 while(i<50)
			 {
				 
				 data_pye[i][0]=" ";
				 data_pye[i][1]=" ";
				 data_pye[i][2]=" ";
				 data_pye[i][3]=" ";
				 data_pye[i][4]=" ";
				 i++;
			 }
			 i=0;
				this.connDB();
			 try {
				 bu = asi.bu_query(username);
				 
//				 pye = asi.pye_query_QPY(bu.getUid());
				 stmt = con.createStatement();
				 bu = asi.bu_query(username);
				 String st = bu.getUid();
				 String sql= "select * from payee where uid = "+st+"";
				 rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
					 String payee_id = rs.getString("payee_id");
					 String uid = rs.getString("uid");
					 String payee_name = rs.getString("payee_name");
					 String payee_card = rs.getString("payee_card");
					 String belong_bank = rs.getString("belong_bank");
					 data_pye[i][0]=payee_id;
					 data_pye[i][1]=uid;
					 data_pye[i][2]=payee_name;
					 data_pye[i][3]=payee_card;
					 data_pye[i][4]=belong_bank;
					 i++;
				 }
				 
//				 this.closeDB();
//				 repaint();
			} catch (SQLException e1){
				e1.printStackTrace();
			}
			 
			 
	}else if(source == delebtn){
			this.connDB();
			int res = JOptionPane.showConfirmDialog(null, "确认删除此收款人？", "删除确认", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
			String delid = delpyeid_field.getText();
			System.out.println(delid);
			try {
				stmt = con.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 String sql1= "delete from payee where payee_id = "+delid+"";
			 try {
			int rds = stmt.executeUpdate(sql1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}else{
				
			}
			
		}else if(source == rebackbtn){
			QueryFrame qf = new QueryFrame(username,client_id);
			setVisible(false);
			qf.setVisible(true);
		}
		
		 this.closeDB();
			 repaint();
	}
		public static void main(String[] args) {
			QryPyeFrame skt = new QryPyeFrame(null,null);
		}
	
}
