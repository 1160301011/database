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
 *��ʾ��
 *���ܺ�̨���������� 
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
		setTitle("���ݲ�ѯ");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblchoose = new JLabel("��ѯѡ��:");
		lblchoose.setFont(new Font("������", Font.BOLD, 20));
		lblchoose.setBounds(30, 100, 113, 35);
		contentPane.add(lblchoose);

		choosebill = new JComboBox();
		choosebill.setBounds(140, 107, 130, 21);
		choosebill.addItem("ע����");
		choosebill.addItem("��ʧ��");
		choosebill.addItem("����");
		choosebill.addItem("ת������");
		choosebill.addItem("����ת������");
		choosebill.addItem("ȡ������");
		choosebill.addItem("����ɷ��ܶ�");
		choosebill.addItem("���ÿ�����");
		
		contentPane.add(choosebill);
		// cre_closepay.addActionListener();
		choosebill.addActionListener(new choosebillAction());
		
		JLabel lblrestrictive = new JLabel("�����˻�:");
		lblrestrictive.setFont(new Font("������", Font.BOLD, 20));
		lblrestrictive.setBounds(30, 140, 113, 35);
		contentPane.add(lblrestrictive);
		
		username_Field = new JTextField();  //�˻��������
		username_Field.setBounds(140, 144, 117, 24);
		contentPane.add(username_Field);
		username_Field.setColumns(10);
		
		
		
		jt_showdata = new JTextArea(10,20);
		jt_showdata.setBounds(20,200,250,100);
		jt_showdata.setLineWrap(true);  //�Զ�����
		jt_showdata.setFont(new Font("г��",Font.BOLD|Font.ITALIC,16));
        // ��ӵ��������
		contentPane.add(jt_showdata);
		
		
		JButton relieveBtn = new JButton("�������");
		relieveBtn.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		relieveBtn.setBorderPainted(true); // �ޱ߿�
		relieveBtn.setOpaque(false); // ���ð�ť͸��
		relieveBtn.setContentAreaFilled(false); // �رյ�ɫ
		relieveBtn.setMargin(new Insets(0,0,0,0));
		relieveBtn.setBounds(80, 440, 140, 20);
		contentPane.add(relieveBtn);
		relieveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				asi.upuser_status3(username_Field.getText());
				JOptionPane.showMessageDialog(null, "����ɹ���");
			}
		});
		
		JButton restrictiveBtn = new JButton("���ƽ���");
		restrictiveBtn.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		restrictiveBtn.setBorderPainted(true); // �ޱ߿�
		restrictiveBtn.setOpaque(false); // ���ð�ť͸��
		restrictiveBtn.setContentAreaFilled(false); // �رյ�ɫ
		restrictiveBtn.setMargin(new Insets(0,0,0,0));
		restrictiveBtn.setBounds(80, 390, 140, 20);
		contentPane.add(restrictiveBtn);
		restrictiveBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				asi.upuser_status2(username_Field.getText());
				JOptionPane.showMessageDialog(null, "���Ƴɹ���");
			}
		});
		
		
		JButton resetBtn = new JButton("����");
		resetBtn.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		resetBtn.setBorderPainted(true); // �ޱ߿�
		resetBtn.setOpaque(false); // ���ð�ť͸��
		resetBtn.setContentAreaFilled(false); // �رյ�ɫ
		resetBtn.setMargin(new Insets(0,0,0,0));
		resetBtn.setBounds(160, 340, 110, 20);
		contentPane.add(resetBtn);
		resetBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 clear();
			}
		});
		
		
		JButton reback = new JButton("����");
		reback.setBounds(20, 340, 113, 20);
		reback.setBorderPainted(true); // �ޱ߿�
		reback.setOpaque(false); // ���ð�ť͸��
		reback.setForeground(Color.BLACK); // �����������
		reback.setContentAreaFilled(false); // �رյ�ɫ
		// forgetBtn.setFocusPainted(false); //�۽���
		reback.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		contentPane.add(reback);
		reback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(name,client_id);
				setVisible(false);
				af.setVisible(true);			
			}		
		});

	}
	// �������ݿ�
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

	public static void closeDB() // �ر�����
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
			Date dadate = new Date();//���ϵͳʱ��
			String nowDaDate = sdf.format(dadate);
			Timestamp daTs = Timestamp.valueOf(nowDaDate);
			
			if(Strchoose.equals("ע����")){
			String sql = "select count(1) as countnumber from bank_user group by userstatus";
			System.out.println(sql);
			Statement stat;
			try {
				stat = (Statement) con.createStatement();
				ResultSet rs;
				rs = stat.executeQuery(sql);
				while (rs.next()) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
					string=string+"��ֹ"+daTs+"\n"+"ע�������ƣ�"+rs.getString(1)+"\n";
					System.out.println(rs.getString("countnumber"));
					//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
					jt_showdata.setText("��ֹ"+daTs+"\n"+"ע�������ƣ�"+rs.getString(1));
				}
//				closeDB();
//				repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			}else if (Strchoose.equals("��ʧ��")){
				String sql = "select count(1) as losenumber from bank_user where userstatus=2";
				System.out.println(sql);
				Statement stat;
				try {
					stat = (Statement) con.createStatement();
					ResultSet rs;
					rs = stat.executeQuery(sql);
					while (rs.next()) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df.format(new Date());
						string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
						string=string+"��ֹ"+daTs+"\n"+"��ʧ�����ƣ�"+rs.getString(1)+"\n";
						System.out.println(rs.getString("losenumber"));
						//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
						jt_showdata.setText("��ֹ"+daTs+"\n"+"��ʧ�����ƣ�"+rs.getString(1));
					}
//					closeDB();
//					repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
			}
		}else if (Strchoose.equals("����")){
			String sql = "select count(*) as bkdnumber from bankcard";
			System.out.println(sql);
			Statement stat;
			try {
				stat = (Statement) con.createStatement();
				ResultSet rs;
				rs = stat.executeQuery(sql);
				while (rs.next()) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
					string=string+"��ֹ"+daTs+"\n"+"�������ƣ�"+rs.getString(1)+"\n";
					System.out.println(rs.getString("bkdnumber"));
					//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
					jt_showdata.setText("��ֹ"+daTs+"\n"+"�������ƣ�"+rs.getString(1));
				}
//				closeDB();
//				repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			
		}
	}else if(Strchoose.equals("ת������")){
		String sql = " select t.client_id,sum(t.trade_money) from TRANSFER t join client_info c on t.client_id = c.client_id";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df.format(new Date());
				string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
				string=string+"��ֹ"+daTs+"\n"+"ת���ܽ��:"+rs.getString("sum(t.trade_money)")+"\n";
				System.out.println(rs.getString("t.client_id")+"\n " +rs.getString("sum(t.trade_money)"));
				//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
				jt_showdata.setText("��ֹ"+daTs+"\n"+"ת���ܽ��:"+rs.getString("sum(t.trade_money)"));
			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
	}else if(Strchoose.equals("����ת������")){
		String sql = " select sum(t.trade_money) from TRANSFER t join client_info c on t.client_id = c.client_id where int_out=2";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df.format(new Date());
				string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
				string=string+"��ֹ"+daTs+"\n"+"����ת���ܽ��:"+rs.getString("sum(t.trade_money)")+"\n";
				//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
				jt_showdata.setText("��ֹ"+daTs+"\n"+"����ת���ܽ��:"+rs.getString("sum(t.trade_money)"));
			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		
	}else if(Strchoose.equals("ȡ������")){
		String sql = "select sum(wid_pre_money) as ���׽��,sum(wid_interest) as ������Ϣ�ܶ� from WITHDRAWAL";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df.format(new Date());
				string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
				string=string+"��ֹ"+daTs+"\n"+"ȡ������ : "+rs.getString("���׽��")+"\n";
				//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
				jt_showdata.setText("��ֹ"+daTs+"\n"+"ȡ������ : "+rs.getString("���׽��"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		
	}else if(Strchoose.equals("����ɷ��ܶ�")){
		String sql = "select sum(con_money) as ����ɷ��ܶ� from CONSUMPTION";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df.format(new Date());
				string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
				string=string+"��ֹ"+daTs+"\n"+"����ɷ��ܶ� : "+rs.getString("����ɷ��ܶ�")+"\n";
				//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
				jt_showdata.setText("��ֹ"+daTs+"\n"+"����ɷ��ܶ� : "+rs.getString("����ɷ��ܶ�"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
		
	}else if(Strchoose.equals("���ÿ�����")){
		String sql = " select sum(crecon_money) as ���ÿ������ܶ� from CREDIT_CONSUMPTION";
		System.out.println(sql);
		Statement stat;
		try {
			stat = (Statement) con.createStatement();
			ResultSet rs;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df.format(new Date());
				string=string+"��ǰ���򣺳��ܺ�̨���������ˣ���̨����"+"\n";
				string=string+"��ֹ"+daTs+"\n"+"���ÿ��������� : "+rs.getString("���ÿ������ܶ�")+"\n";
				//					JOptionPane.showMessageDialog(null, "ע�������ƣ�" +rs.getString(1));
				jt_showdata.setText("��ֹ"+daTs+"\n"+"���ÿ��������� : "+rs.getString("���ÿ������ܶ�"));
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
			System.out.println("��ѡ�е��ǵ�"+i+"��"+",������:"+Strchoose);
	
		}
		
	}
	private void clear() {
		jt_showdata.setText("");    //����Ϊ��
	}
	
	
}
