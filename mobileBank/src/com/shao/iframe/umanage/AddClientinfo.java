package com.shao.iframe.umanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AdminFrame;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.LoginFrame;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;


/**��ӿͻ���Ϣ
 * @author Administrator
 *
 */
public class AddClientinfo extends JFrame implements ActionListener, HyperlinkListener{
	private JPanel contentPane;
	private JTextField addrField;		//�ͻ���ַ�����
	private JTextField client_name;		//�ͻ����������
	private JTextField client_idcard;		//�ͻ���������
	private JTextField client_phone;		//�ͻ��ֻ������
	private Clientinfo cfo;
	private Bankuser bu;
	private String name;
	private String client_id1;
	private JEditorPane pane;
	final AccountServiceImpl asi =new AccountServiceImpl();
	
	
	public AddClientinfo(final String name,final String client_id1) {
		
		
		this.name = name;   //����ͻ�ID ���û���  
		this.client_id1 = client_id1;
		MenuBar();
		
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// ����pane�ĳ������Ӽ���
		pane.addHyperlinkListener(this);
		this.add(new JScrollPane(pane), "Center");
		
		
		
		//��ѯ�ͻ���Ϣ
		cfo = asi.cquery(client_id1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 500, 400);
		setTitle(name);
		setLocationRelativeTo(null);  //������Ļ����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel lblNewLabel = new JLabel("��  ��ID ��");
//		lblNewLabel.setFont(new Font("������", Font.BOLD, 20));
//		lblNewLabel.setBounds(105, 72, 126, 30);
//		contentPane.add(lblNewLabel);
//		
//		client_id = new JTextField();			//�ͻ�ID�����
//		client_id.setBounds(235, 77, 152, 20);
//		contentPane.add(client_id);
//		client_id.setColumns(10);
		
														//�ͻ�������ť
		JLabel lblNewLabel3 = new JLabel("�ͻ����� ��");
		lblNewLabel3.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel3.setBounds(105, 72, 126, 30);
		contentPane.add(lblNewLabel3);
		
		client_name = new JTextField();			//�ͻ����������
		client_name.setBounds(235, 77, 152, 20);
		contentPane.add(client_name);
		client_name.setColumns(8);
		client_name.setText(cfo.getClient_name());  //���ݿ��Զ����ؿͻ�����
		
		//���֤��ť
		JLabel lblNewLabel4 = new JLabel("�� �� ֤ ��");
		lblNewLabel4.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel4.setBounds(105, 112, 126, 30);
		contentPane.add(lblNewLabel4);
		
		client_idcard = new JTextField();			//���֤�����
		client_idcard.setBounds(235, 119, 152, 20);
		contentPane.add(client_idcard);
		client_idcard.setColumns(19);
		client_idcard.setText(cfo.getClient_idcard()); //���ݿ������Ϣ
		//�ͻ��ֻ���ť
		JLabel lblNewLabel5 = new JLabel("�ͻ��ֻ� ��");
		lblNewLabel5.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel5.setBounds(105, 152, 126, 30);
		contentPane.add(lblNewLabel5);
		
		client_phone = new JTextField();			//�ֻ��������
		client_phone.setBounds(235, 159, 152, 20);
		contentPane.add(client_phone);
		client_phone.setColumns(15);
		client_phone.setText(cfo.getClient_phone());  //���ݿ������Ϣ
		
		
		JLabel lbladdr = new JLabel("�ͻ���ַ ��");
		lbladdr.setFont(new Font("������", Font.BOLD, 20));
		lbladdr.setBounds(105, 192, 126, 30);
		contentPane.add(lbladdr);
		
		addrField = new JTextField();			//�ͻ���ַ�����
		addrField.setBounds(235, 199, 152, 20);
		contentPane.add(addrField);
		addrField.setColumns(10);
		addrField.setText(cfo.getAddr()); //���ݿ������Ϣ
		
		
		//���ؼ���ʱ��
	JButton backButton_1 = new JButton("����");
	backButton_1.setFont(new Font("������", Font.BOLD, 18));
	backButton_1.setBounds(290, 275, 113, 27);
	contentPane.add(backButton_1);
	backButton_1.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			UmanageFrame uf = new UmanageFrame(name,client_id1);
			setVisible(false);
			uf.setVisible(true);			
		}		
	});
	
				//�༭��ť����
			JButton editButton = new JButton("�༭");
		editButton.setFont(new Font("������", Font.BOLD, 18));
		editButton.setBounds(152, 275, 113, 27);
		contentPane.add(editButton);
		editButton.addActionListener(new editButtonAction());
	}
	
	private void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		//����˵�Ŀ¼
		JMenu oper_JM = new JMenu("����");
		JMenu set_JM = new JMenu("����");
		
		oper_JM.setMnemonic(KeyEvent.VK_F);
		JMenuItem oper_exit_JMitem = new JMenuItem("�˳�", KeyEvent.VK_X);
		JMenuItem oper_reback_JMitem = new JMenuItem("����", KeyEvent.VK_X);
		JMenuItem set_chuser_JMitem = new JMenuItem("�����˺�");
		JMenuItem set_pwd_JMitem = new JMenuItem("��������");
		JMenuItem set_ad_JMitem = new JMenuItem("��̨");
		 
		set_JM.add(set_chuser_JMitem);
		set_JM.add(set_ad_JMitem);
		set_JM.add(set_pwd_JMitem);
		//�˵�������˵���
		menuBar.add(oper_JM);
		menuBar.add(set_JM);
		//����˵�����
		setJMenuBar(menuBar);
		oper_JM.add(oper_exit_JMitem);
		oper_JM.add(oper_reback_JMitem);
		// ���õ���˳�������˳�
		oper_exit_JMitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		oper_reback_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(name,client_id1);
				setVisible(false);
				af.setVisible(true);
			}
		});
		set_chuser_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);
			}
		});
		
		set_ad_JMitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strid = JOptionPane.showInputDialog("�����볬�ܿ���");
                if (strid.equals("root"))
                {
                	AdminFrame af = new AdminFrame(name,client_id1);
    				setVisible(false);
    				af.setVisible(true);
                }
//				Object[] possibleValues = { "�ͻ�����", "ά��", "����" }; 
//				Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "��̨", 
//				JOptionPane.INFORMATION_MESSAGE, null, 
//				possibleValues, possibleValues[0]);
			} 
	});
		set_pwd_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				ModifpwdFrame mpf = new ModifpwdFrame(name,client_id1);
				setVisible(false);
				mpf.setVisible(true);
			}
		});
		
		
		
	}
	
	
	//������Ϣ�༭��ť������	
		class editButtonAction implements ActionListener {
			AccountServiceImpl asi =new AccountServiceImpl();
//			public void editButtonAction(final String name){
//			}
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				cfo = asi.cquery(client_id1);
				System.out.println(client_id1);
				try {
					bu = asi.bu_query(name);  //����UID
					System.out.println(name);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (!client_name.getText().equals("")) {
					if (!client_idcard.getText().equals("")) {
						if (!client_phone.getText().equals("")) {
							if (!addrField.getText().equals("")) {  
//								if (cfo.getClient_idcard()==null) {   //  !u_name.getText().trim().equals(user.getName())                             //  !u_name.getText().equals(user.getName())
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺸�����Ϣ�༭�������ˣ�ҵ��Ա19"+"\n";	
								asi.updateClientinfo(client_id1,bu.getUid(),client_name.getText(),client_idcard.getText(),client_phone.getText(),addrField.getText());
									setVisible(true);
									string=string+"�༭�ɹ�,���Լ���ʹ�ã�";
									JOptionPane.showMessageDialog(null, "�༭�ɹ�,���Լ���ʹ�ã�");
//								} else {
//									JOptionPane.showMessageDialog(null, "���֤�Ѵ��ڣ�");
//								}
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺸�����Ϣ�༭�������ˣ��û�"+"\n";
								string=string+"δ�����ַ��";
								JOptionPane.showMessageDialog(null, "δ�����ַ��");
							}

						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ���򣺸�����Ϣ�༭�������ˣ��û�"+"\n";
							string=string+"δ�����ֻ��ţ�";
							JOptionPane.showMessageDialog(null, "δ�����ֻ��ţ�");
						}
				

					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ���򣺸�����Ϣ�༭�������ˣ��û�"+"\n";
						string=string+"δ�������֤��";
						JOptionPane.showMessageDialog(null, "δ�������֤��");
					}
				} else {
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df1.format(new Date());
					string=string+"��ǰ���򣺸�����Ϣ�༭�������ˣ��û�"+"\n";
					string=string+"��δ��������";
					JOptionPane.showMessageDialog(null, "��δ��������");
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
			}


		public void hyperlinkUpdate(HyperlinkEvent e) {
			
		}
		public void actionPerformed(ActionEvent e) {
			
		}
				
	}
		

