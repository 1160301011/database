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
 *��ʾ��
 *��ѯȡ���¼���� 
 *
 */
public class QuerywitFrame extends JFrame {

	DefaultTableModel tableModel;		// Ĭ����ʾ�ı��
	JButton add,del,exit,save,reback;		// ������ť
	JTable table;		// ���
	JPanel panelUP;	//������Ϣ�����
	Object a[][];
	// ���캯��
	public QuerywitFrame(final String name,final String client_id){
		this.setBounds(300, 200, 1200, 450);		// ���ô����С
		this.setTitle(name);		// ���ô�������
		this.setLayout(new BorderLayout());	// ���ô���Ĳ��ַ�ʽ
		// �½�����ť���
		add = new JButton("����");
		del = new JButton("ɾ��");
		save = new JButton("����");
		reback = new JButton("����");
		exit = new JButton("�˳�");
		
		
		panelUP = new JPanel();		// �½���ť������
		panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));	// �������Ĳ��ַ�ʽ
		
		// ������ť���������ӵ������
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
		
		
		// ȡ��haha���ݿ��aa��ĸ�������
		Vector rowData = PuitnStorage_witfer.getRows();
		// ȡ��haha���ݿ��aa��ı�ͷ����
		Vector columnNames = PuitnStorage_witfer.getHead();
		
		
		// �½����
		a = new Object[50][9];
		tableModel = new DefaultTableModel(rowData,columnNames);	
		table = new JTable(tableModel);
		
		JScrollPane s = new JScrollPane(table);
		
		// �����ͱ��ֱ���ӵ�������
		this.add(panelUP,BorderLayout.NORTH);
		this.add(s);
		
		// �¼�����
		MyEvent();
		
		this.setVisible(true);		// ��ʾ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 // ���ô���ɹر�
	}
	
	// �¼�����
	public void MyEvent(){
		
		// ����
		add.addActionListener(new ActionListener(){
 
			public void actionPerformed(ActionEvent arg0) {
				// ����һ�пհ�����
				tableModel.addRow(new Vector());
			}
			
		});
		
		// ɾ��
		del.addActionListener(new ActionListener(){
 
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// ɾ��ָ����
				int rowcount = table.getSelectedRow();
				if(rowcount >= 0){
					tableModel.removeRow(rowcount);
				}
			}
			
		});
		
		/**
		 * ����
		 * �ҵĽ���취��ֱ�ӽ�aa���е�ȫ������ɾ����
		 * ������е��������ݻ�ȡ��,
		 * Ȼ�󽫱����������д��aa��
		 */
		save.addActionListener(new ActionListener(){
 
			public void actionPerformed(ActionEvent e) {	
				int column = table.getColumnCount();		// �������
				int row = table.getRowCount();		// �������
				
				System.out.println("��"+row+"��");
				System.out.println("��"+column+"��");
				// value�����ű���е���������
				String[][] value = new String[row][column];
				
				for(int i = 0; i < row; i++){
					for(int j = 0; j < column; j++){
						
						System.out.println(i);
						System.out.println(j);
						value[i][j] = table.getValueAt(i, j).toString();
						System.out.println("����ɹ�");
					}
				}
				
				
				// ���¾�Ϊ�����ݿ�Ĳ���
				String sql_url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";	//���ݿ�·����һ�㶼������д����haha�����ݿ�����
				String name = "root";		//�û���
				String password = "root";	//����
				Connection conn;
				PreparedStatement preparedStatement = null;
					System.out.println("���ɹ�����");
				try {
					Class.forName("com.mysql.jdbc.Driver");		//��������
					conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
					if(!conn.isClosed())
						System.out.println("�ɹ��������ݿ�");
					
					// ɾ��aa������������
					preparedStatement = conn.prepareStatement("delete from WITHDRAWAL where true");
					preparedStatement.executeUpdate();
					System.out.println("ɾ���ɹ�");
					// ��value�����е��������δ�ŵ�aa����
					for(int i = 0; i < row; i++){
						
						preparedStatement = conn.prepareStatement("insert into WITHDRAWAL values("+ Integer.parseInt(value[i][0]) + ",'" + value[i][1] + "')");
						preparedStatement.executeUpdate();
						System.out.println("��ӳɹ�");
					}
				
				} catch (ClassNotFoundException e1) {
					System.out.println("δ�ɹ�����������");
					e1.printStackTrace();
				} catch (SQLException e1) {
					System.out.println("δ�ɹ������ݿ⡣");
					e1.printStackTrace();
				}
				
				// ������˳�
				System.exit(0);
			}
		});
		
		// �˳�
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
