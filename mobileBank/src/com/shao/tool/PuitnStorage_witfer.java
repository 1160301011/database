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
 *���߰�
 *ȡ���¼������ 
 *
 */
public class PuitnStorage_witfer {
	// �õ����ݿ������
		public static Vector getRows(){
			
			
			String sql_url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";	//���ݿ�·����һ�㶼������д����test�����ݿ�����
			String name = "root";		//�û���
			String password = "root";	//����
			
			Connection conn;
			PreparedStatement preparedStatement = null;
	 
			Vector rows = null;
			Vector columnHeads = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");		//��������
				
				conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
				
//				if(!conn.isClosed())
//					System.out.println("�ɹ��������ݿ�");
				
				String sql="select * from WITHDRAWAL ";
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
//				pst.setObject(1,1231232);
//				pst.executeUpdate();
//				preparedStatement = conn.prepareStatement("select * from bankcard");
				ResultSet result1 = pst.executeQuery();
				
				if(result1.wasNull())
					JOptionPane.showMessageDialog(null, "��������޼�¼");
				
				rows = new Vector();
				
				ResultSetMetaData rsmd = result1.getMetaData();
						
				while(result1.next()){
					rows.addElement(getNextRow(result1,rsmd));
				}
				
			} catch (ClassNotFoundException e) {
				System.out.println("δ�ɹ�����������");
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("δ�ɹ������ݿ⡣");
				e.printStackTrace();
			}
			return rows;
		}
		
		// �õ����ݿ��ͷ
		public static Vector getHead(){
			String sql_url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";	//���ݿ�·����һ�㶼������д����test�����ݿ�����
			String name = "root";		//�û���
			String password = "root";	//����
			Connection conn;
			PreparedStatement preparedStatement = null;
	 
			Vector columnHeads = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");		//��������
				conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
//				if(!conn.isClosed())
//					System.out.println("�ɹ��������ݿ�");
				preparedStatement = conn.prepareStatement("select * from WITHDRAWAL");
				ResultSet result1 = preparedStatement.executeQuery();
				System.out.println("��ѯ�ɹ�");
				boolean moreRecords = result1.next();
				if(!moreRecords)
					JOptionPane.showMessageDialog(null, "��������޼�¼");
				
				columnHeads = new Vector();
				ResultSetMetaData rsmd = result1.getMetaData();
				for(int i = 1; i <= rsmd.getColumnCount(); i++)
					columnHeads.addElement(rsmd.getColumnName(i));
				
			} catch (ClassNotFoundException e) {
				System.out.println("δ�ɹ�����������");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("δ�ɹ������ݿ⡣");
				e.printStackTrace();
			}
			return columnHeads;
			
		}
		
		// �õ����ݿ�����һ������
		private static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
			Vector currentRow = new Vector();
			for(int i = 1; i <= rsmd.getColumnCount(); i++){
				currentRow.addElement(rs.getString(i));
			}
			return currentRow;
			
		}
		
		/*//������
		 public static void main(String[] args){ss
			 getRows();
		}*/


}
