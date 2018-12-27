package com.shao.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.text.SimpleDateFormat;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
/**
 * @author HGX
 *工具包
 *取款记录表生成 
 *
 */
public class PuitnStorage_witfer {
	// 得到数据库表数据
		public static Vector getRows(){
			
			
			String sql_url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";	//数据库路径（一般都是这样写），test是数据库名称
			String name = "root";		//用户名
			String password = "root";	//密码
			
			Connection conn;
			PreparedStatement preparedStatement = null;
	 
			Vector rows = null;
			Vector columnHeads = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");		//连接驱动
				
				conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
				
//				if(!conn.isClosed())
//					System.out.println("成功连接数据库");
				
				String sql="select * from WITHDRAWAL ";
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
//				pst.setObject(1,1231232);
//				pst.executeUpdate();
//				preparedStatement = conn.prepareStatement("select * from bankcard");
				ResultSet result1 = pst.executeQuery();
				
				if(result1.wasNull())
					JOptionPane.showMessageDialog(null, "结果集中无记录");
				
				rows = new Vector();
				
				ResultSetMetaData rsmd = result1.getMetaData();
						
				while(result1.next()){
					rows.addElement(getNextRow(result1,rsmd));
				}
				
			} catch (ClassNotFoundException e) {
				System.out.println("未成功加载驱动。");
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("未成功打开数据库。");
				e.printStackTrace();
			}
			return rows;
		}
		
		// 得到数据库表头
		public static Vector getHead(){
			String sql_url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";	//数据库路径（一般都是这样写），test是数据库名称
			String name = "root";		//用户名
			String password = "root";	//密码
			Connection conn;
			PreparedStatement preparedStatement = null;
	 
			Vector columnHeads = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");		//连接驱动
				conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
//				if(!conn.isClosed())
//					System.out.println("成功连接数据库");
				preparedStatement = conn.prepareStatement("select * from WITHDRAWAL");
				ResultSet result1 = preparedStatement.executeQuery();
				System.out.println("查询成功");
				boolean moreRecords = result1.next();
				if(!moreRecords)
					JOptionPane.showMessageDialog(null, "结果集中无记录");
				
				columnHeads = new Vector();
				ResultSetMetaData rsmd = result1.getMetaData();
				for(int i = 1; i <= rsmd.getColumnCount(); i++)
					columnHeads.addElement(rsmd.getColumnName(i));
				
			} catch (ClassNotFoundException e) {
				System.out.println("未成功加载驱动。");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("未成功打开数据库。");
				e.printStackTrace();
			}
			return columnHeads;
			
		}
		
		// 得到数据库中下一行数据
		private static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
			Vector currentRow = new Vector();
			for(int i = 1; i <= rsmd.getColumnCount(); i++){
				currentRow.addElement(rs.getString(i));
			}
			return currentRow;
			
		}
		
		/*//主函数
		 public static void main(String[] args){ss
			 getRows();
		}*/


}
