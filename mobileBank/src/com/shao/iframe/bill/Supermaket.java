package com.shao.iframe.bill;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.model.*;

import org.omg.PortableInterceptor.SUCCESSFUL;


/**
 * @author HGX
 *��ʾ��
 *�̳ǹ������ 
 *
 */
public class Supermaket extends JFrame implements ActionListener {
	
	JTable table;
	JLabel label1,lblgoodsID,num_label4,total_moneylbl,lblcardid;
	Object a[][];
	Object name[] = {"��ƷID","��Ʒ����","����ʱ��","�۸�","����","��Ʒ����"};
	JButton buttonOfKe,btnbrowse,btnconfirm,btnreback,btnbuyrecord;
	Box box1,box2;
	JTextField goodsid_field,num_field,tomoney_field,cardid_field;
	JPanel jPanel4,jPanel5;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	static String username =null;
	static String client_id = null ;
	private com.shao.model.T_productchosen query_product;
	private com.shao.model.Creditcard crd;
	private com.shao.model.Bankuser bu;
	
	public Supermaket(final String username,final String client_id)
	{
		
		init();
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 640, 520);
		setTitle("�𾴵�:"+username+"�û�����");
		this.username = username;
		this.client_id = client_id;
		
	}
	
	void init()
	{
		label1 = new JLabel("�̳���Ϣ���");
		
	
		btnbrowse = new JButton("   ��Ʒ��Ϣ���   ");
		btnbrowse .addActionListener(this);
//		buttonOfQyueding = new JButton("    ȷ	            ��       ");
//		buttonOfQyueding.addActionListener(this);
		btnreback = new JButton("   ��       ��      ");
		btnreback.addActionListener(this);
		buttonOfKe = new JButton("  ��  ��  ��  ��    ");
		buttonOfKe.addActionListener(this);
		btnconfirm = new JButton(" ȷ �� �� ��   ");
//		btnconfirm.addActionListener(this);
		btnbuyrecord = new JButton(" �� �� �� ¼   ");
		btnbuyrecord.addActionListener(this);
		lblgoodsID = new JLabel("������ƷID��");
		num_label4 = new JLabel("��������:");
		total_moneylbl = new JLabel("��ǰ����(Ԫ):");
		lblcardid = new JLabel("�����:");
		goodsid_field = new JTextField();
		num_field = new JTextField();
		tomoney_field = new JTextField();
		cardid_field = new JTextField();
		
		a = new Object[50][6];
		table = new JTable(a, name);//����Ĵ���
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		
		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(20));
	
		box1.add(btnbrowse);
		box1.add(Box.createVerticalStrut(15));
		box1.add(lblgoodsID);
		box1.add(Box.createVerticalStrut(5));
		box1.add(goodsid_field);
		box1.add(Box.createVerticalStrut(5));
		box1.add(num_label4);
		box1.add(Box.createVerticalStrut(5));
		box1.add(num_field);
		box1.add(Box.createVerticalStrut(5));
		
//		box1.add(buttonOfQyueding);
		box1.add(Box.createVerticalStrut(15));
		box1.add(total_moneylbl);
		box1.add(Box.createVerticalStrut(5));
		box1.add(tomoney_field);
		box1.add(Box.createVerticalStrut(5));
		
		box1.add(buttonOfKe);
		box1.add(Box.createVerticalStrut(10));
		
		box1.add(lblcardid);
		box1.add(Box.createVerticalStrut(5));
		box1.add(cardid_field);
		box1.add(Box.createVerticalStrut(5));
		box1.add(btnconfirm);
		box1.add(Box.createVerticalStrut(20));
		
		box1.add(btnbuyrecord);
		box1.add(Box.createVerticalStrut(10));
		box1.add(btnreback);
		
		box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(10));
		box2.add(box1);   //��ߵİ�ť������ box����
		
		jPanel4 = new JPanel();
		jPanel5 = new JPanel();
		jPanel4.setLayout(new BorderLayout());
		jPanel4.add(box2,BorderLayout.NORTH);//����ߵİ�ť���ַŵ�jpanel4�С�
//		
		jPanel5.setLayout(new BorderLayout());
		jPanel5.add(label1,BorderLayout.NORTH);
		jPanel5.add(scrollPane,BorderLayout.CENTER);//�ѱ�� ��jpanel5��
	
		this.setLayout(new BorderLayout());
		add(jPanel5,BorderLayout.EAST);
		add(jPanel4,BorderLayout.WEST);//���������panel�ŵ���������
		
		// ���ñ����ɫ
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
		
		//ȷ�ϸ���� �����ÿ� ���ö�ȿۿ��㷨  �����¼�
		btnconfirm.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				final AccountServiceImpl asi =new AccountServiceImpl();
				crd = asi.query_creditcard_info(cardid_field.getText());
				String string=new String();
				
				
		if(crd.getCre_closepay().equals("1")){
			String str = JOptionPane.showInputDialog("�����뿨�����룺");
			if (str.equals(crd.getCre_pwd())){
				if (crd.getCre_available() > Double.parseDouble(tomoney_field.getText())) {
					double temp = crd.getCre_available() - Double.parseDouble(tomoney_field.getText());
					double totalmoney = Double.parseDouble(tomoney_field.getText());
					System.out.println(totalmoney);
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df1.format(new Date());
					string=string+"��ǰ�����̳ǹ�������ˣ�ҵ��Ա8"+"\n";
					DecimalFormat df = new DecimalFormat("0.00 ");
					//�������ÿ����ö��
					asi.update_cre_available(cardid_field.getText(),temp);
					//�������ÿ��˵�
					asi.update_cre_bill(cardid_field.getText(),totalmoney);
				
//					System.out.println(crd.getCre_serial());
//					String cre_id = crd.getCre_serial();
//					System.out.println(cre_id);
						//������ÿ����Ѽ�¼��
					try {
						asi.add_cre_billrecord(cardid_field.getText(),totalmoney,crd.getCre_serial());
					} catch (ClassNotFoundException e1) {
						string=string+e1.toString()+"\n";
						e1.printStackTrace();
					}
					System.out.println("ִ��");
					string=string+"����ɹ���" + "\n" +"���ţ�"+cardid_field.getText()+"\n"+ "ʣ����ö��Ϊ��" + df1.format(temp)+"\n";
					JOptionPane.showMessageDialog(null, "����ɹ���" + "\n" +"���ţ�"+cardid_field.getText()+"\n"+ "ʣ����ö��Ϊ��" + df1.format(temp));
				}else{
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df1.format(new Date());
					string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
					string=string+"���Ŀ��ö�Ȳ��㣡" + "\n" + "��ǰ���Ϊ��" + crd.getCre_available()+"\n";
					JOptionPane.showMessageDialog(null, "���Ŀ��ö�Ȳ��㣡" + "\n" + "��ǰ���Ϊ��" + crd.getCre_available());
				}
			}else{
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df1.format(new Date());
				string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
				string=string+"����������벻��ȷ��"+"\n";
				JOptionPane.showMessageDialog(null, "����������벻��ȷ��");
			}
		}else if(crd.getCre_closepay().equals("2")){
			if (crd.getCre_available() > Double.parseDouble(tomoney_field.getText())) {
				double temp = crd.getCre_available() - Double.parseDouble(tomoney_field.getText());
				double totalmoney = Double.parseDouble(tomoney_field.getText());
				System.out.println(totalmoney);
				DecimalFormat df = new DecimalFormat("0.00 ");
				string=string+"��ǰ�����̳ǹ�������ˣ�ҵ��Ա8"+"\n";
				//�������ÿ����ö��
				asi.update_cre_available(cardid_field.getText(),temp);
				//�������ÿ��˵�
				asi.update_cre_bill(cardid_field.getText(),totalmoney);
				//������ÿ����Ѽ�¼��
//				System.out.println(crd.getCre_serial());
//				String cre_id = crd.getCre_serial();
//				System.out.println(cre_id);
				try {
					asi.add_cre_billrecord(cardid_field.getText(),totalmoney,crd.getCre_serial());
				} catch (ClassNotFoundException e1) {
					string=string+e1.toString()+"\n";
					e1.printStackTrace();
				}
				System.out.println("ִ��");
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df1.format(new Date());
				string=string+"����ɹ���" + "\n" +"���ţ�"+cardid_field.getText()+"\n"+ "ʣ����ö��Ϊ��" + df.format(temp)+"\n";
				JOptionPane.showMessageDialog(null, "����ɹ���" + "\n" +"���ţ�"+cardid_field.getText()+"\n"+ "ʣ����ö��Ϊ��" + df.format(temp));
			}else{
				string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
				string=string+"���Ŀ��ö�Ȳ��㣡" + "\n" + "��ǰ���Ϊ��" + crd.getCre_available()+"\n";
				JOptionPane.showMessageDialog(null, "���Ŀ��ö�Ȳ��㣡" + "\n" + "��ǰ���Ϊ��" + crd.getCre_available());
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
			
		});
	}
	

	public void connDB() { // �������ݿ�
		try {
			Class.forName("com.mysql.jdbc.Driver");//ע������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {//��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobilebank3?characterEncoding=utf-8", "root", "root");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void closeDB() // �ر�����
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
				 String sql= "select * from t_productchosen";
				 rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
					 String id = rs.getString("id");
					 String productName = rs.getString("productName");
					 String productTime = rs.getString("productTime");
					 String price = rs.getString("price");
					 String productDesc = rs.getString("productDesc");
					 String  productTypeId= rs.getString("productTypeId");
					 a[i][0]=id;
					 a[i][1]=productName;
					 a[i][2]=productTime;
					 a[i][3]=price;
					 a[i][4]=productDesc;
					 a[i][5]=productTypeId;
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
		Object source = e.getSource();
		if(source == btnbrowse)//�����Ϣ�����ť
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
				 String sql= "select * from t_productchosen";
				 rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
					 String id = rs.getString("id");
					 String productName = rs.getString("productName");
					 String productTime = rs.getString("productTime");
					 String price = rs.getString("price");
					 String productDesc = rs.getString("productDesc");
					 String  productTypeId= rs.getString("productTypeId");
					 a[i][0]=id;
					 a[i][1]=productName;
					 a[i][2]=productTime;
					 
					 a[i][3]=price;
					 a[i][4]=productDesc;
					 a[i][5]=productTypeId;
					 
					 i++;
				 }
				 this.closeDB();
				 repaint();
			} catch (SQLException e1){
				e1.printStackTrace();
			}
		}
		
		if(source == btnreback)//�����Ϣ�����ť
		{
				PaymentFrame pmf = new PaymentFrame(username,client_id);
				setVisible(false);
				pmf.setVisible(true);			
			}		
		if(source == btnbuyrecord){
			 this.connDB();
			 try {
				stmt = con.createStatement();
				 String sql= "select * from CREDIT_CONSUMPTION order by crecon_date desc limit 1";
				 rs = stmt.executeQuery(sql);
				 while (rs.next()) {
				 JOptionPane.showMessageDialog(null, "�𾴵Ĺ˿�����" +"\n"+"��������ƷIDΪ��"+goodsid_field.getText()+ "\n"+"����������"+rs.getString("crecon_money")+"Ԫ" );
				 }
				 this.closeDB();
				 repaint();
				 }catch (SQLException e1){
						e1.printStackTrace();
					}
		}
		

		buttonOfKe.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	final AccountServiceImpl asi =new AccountServiceImpl();
        	query_product = asi.query_product(goodsid_field.getText());
        	
            Double price = new Double(query_product.getPrice());
           
            Double num = new Double(num_field.getText());
           
            tomoney_field.setText(price*num+"");		

        }
    });
}
	
	public static void main(String[] args) {
		Supermaket skt = new Supermaket(username,client_id);
	}
	
}


