package com.shao.iframe.query;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.shao.tool.PuitnStorage_witfer;;
/**
 * @author HGX
 *表示层
 *查询取款记录界面 
 *
 */
public class QuerywitFrame extends JFrame {

	DefaultTableModel tableModel;		// 默认显示的表格
	JButton add,del,exit,save,reback;		// 各处理按钮
	JTable table;		// 表格
	JPanel panelUP;	//增加信息的面板
	Object a[][];
	// 构造函数
	public QuerywitFrame(final String name,final String client_id){
		this.setBounds(300, 200, 1200, 450);		// 设置窗体大小
		this.setTitle(name);		// 设置窗体名称
		this.setLayout(new BorderLayout());	// 设置窗体的布局方式
		// 新建各按钮组件
		add = new JButton("增加");
		del = new JButton("删除");
		save = new JButton("保存");
		reback = new JButton("返回");
		exit = new JButton("退出");
		
		
		panelUP = new JPanel();		// 新建按钮组件面板
		panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));	// 设置面板的布局方式
		
		// 将各按钮组件依次添加到面板中
		panelUP.add(add);
		panelUP.add(del);
		panelUP.add(save);
		panelUP.add(reback);
		panelUP.add(exit);
		
		reback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				QueryFrame qf = new QueryFrame(name,client_id);
				setVisible(false);
				qf.setVisible(true);			
			}		
		});
		
		
		// 取得haha数据库的aa表的各行数据
		Vector rowData = PuitnStorage_witfer.getRows();
		// 取得haha数据库的aa表的表头数据
		Vector columnNames = PuitnStorage_witfer.getHead();
		
		
		// 新建表格
		a = new Object[50][9];
		tableModel = new DefaultTableModel(rowData,columnNames);	
		table = new JTable(tableModel);
		
		JScrollPane s = new JScrollPane(table);
		
		// 将面板和表格分别添加到窗体中
		this.add(panelUP,BorderLayout.NORTH);
		this.add(s);
		
		// 事件处理
		MyEvent();
		
		this.setVisible(true);		// 显示窗体
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 // 设置窗体可关闭
	}
	
	// 事件处理
	public void MyEvent(){
		
		// 增加
		add.addActionListener(new ActionListener(){
 
			public void actionPerformed(ActionEvent arg0) {
				// 增加一行空白区域
				tableModel.addRow(new Vector());
			}
			
		});
		
		// 删除
		del.addActionListener(new ActionListener(){
 
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// 删除指定行
				int rowcount = table.getSelectedRow();
				if(rowcount >= 0){
					tableModel.removeRow(rowcount);
				}
			}
			
		});
		
		/**
		 * 保存
		 * 我的解决办法是直接将aa表中的全部数据删除，
		 * 将表格中的所有内容获取到,
		 * 然后将表格数据重新写入aa表
		 */
		save.addActionListener(new ActionListener(){
 
			public void actionPerformed(ActionEvent e) {	
				int column = table.getColumnCount();		// 表格列数
				int row = table.getRowCount();		// 表格行数
				
				System.out.println("有"+row+"行");
				System.out.println("有"+column+"列");
				// value数组存放表格中的所有数据
				String[][] value = new String[row][column];
				
				for(int i = 0; i < row; i++){
					for(int j = 0; j < column; j++){
						
						System.out.println(i);
						System.out.println(j);
						value[i][j] = table.getValueAt(i, j).toString();
						System.out.println("保存成功");
					}
				}
				
				
				// 以下均为对数据库的操作
				String sql_url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";	//数据库路径（一般都是这样写），haha是数据库名称
				String name = "root";		//用户名
				String password = "root";	//密码
				Connection conn;
				PreparedStatement preparedStatement = null;
					System.out.println("最后成功连接");
				try {
					Class.forName("com.mysql.jdbc.Driver");		//连接驱动
					conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
					if(!conn.isClosed())
						System.out.println("成功连接数据库");
					
					// 删除aa表中所有数据
					preparedStatement = conn.prepareStatement("delete from WITHDRAWAL where true");
					preparedStatement.executeUpdate();
					System.out.println("删除成功");
					// 将value数组中的数据依次存放到aa表中
					for(int i = 0; i < row; i++){
						
						preparedStatement = conn.prepareStatement("insert into WITHDRAWAL values("+ Integer.parseInt(value[i][0]) + ",'" + value[i][1] + "')");
						preparedStatement.executeUpdate();
						System.out.println("添加成功");
					}
				
				} catch (ClassNotFoundException e1) {
					System.out.println("未成功加载驱动。");
					e1.printStackTrace();
				} catch (SQLException e1) {
					System.out.println("未成功打开数据库。");
					e1.printStackTrace();
				}
				
				// 保存后退出
				System.exit(0);
			}
		});
		
		// 退出
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
