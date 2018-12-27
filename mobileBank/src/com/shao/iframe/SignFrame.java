package com.shao.iframe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.model.*;
import com.shao.tool.ValidCode;

import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
/**
 * @author HGX
 *��ʾ��
 *ע���˻����� 
 *
 */
public class SignFrame extends JFrame implements ActionListener, HyperlinkListener{
	JMenuBar mentuBar;
	JMenuItem skin1,skin2,fileMenu2;
	// ����ѡ��Ƥ��ʱ���ݲ���
	Object ob;
	private JEditorPane pane;
	private JPanel contentPane;				
	private JTextField u_name;
	private JPasswordField u_password;
	private JPasswordField u_password_1;
	private Bankuser bu;
	private User user;
	private JLabel validcode = new JLabel("��֤�룺");
	private JTextField jtf_code = new JTextField();         /* ��֤�������*/
	private ValidCode vcode = new ValidCode();
	


	public SignFrame() {
		
		MenuBar();
		/*
		 * ������ʾ��ҳ�Ĳ���
		 */
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// ����pane�ĳ������Ӽ���
		pane.addHyperlinkListener(this);
		this.add(new JScrollPane(pane), "Center");
		
		setTitle("ע�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("�û��� :");
		lblNewLabel.setFont(new Font("������", Font.BOLD, 18));
		lblNewLabel.setBounds(74, 78, 102, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("��  �� :");
		lblNewLabel_1.setFont(new Font("������", Font.BOLD, 18));
		lblNewLabel_1.setBounds(74, 149, 88, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ȷ������ :");
		lblNewLabel_2.setFont(new Font("������", Font.BOLD, 18));
		lblNewLabel_2.setBounds(70, 211, 101, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel validcode = new JLabel("��֤��  :");
		validcode.setFont(new Font("������", Font.BOLD, 18));
		validcode.setBounds(74, 275, 101, 28);
		contentPane.add(validcode);
		
		vcode.setBounds(197, 315, 123, 30);
		contentPane.add(vcode);
		
		u_name = new JTextField();
		u_name.setBounds(187, 85, 123, 24);
		contentPane.add(u_name);
		u_name.setColumns(10);
		
		u_password = new JPasswordField();
		u_password.setBounds(187, 153, 123, 24);
		contentPane.add(u_password);
		
		u_password_1 = new JPasswordField();
		u_password_1.setBounds(187, 215, 123, 24);
		contentPane.add(u_password_1);
		
		jtf_code = new JTextField();
		jtf_code.setBounds(187, 275, 123, 24);
		contentPane.add(jtf_code);
		
		JButton OKButton = new JButton("ע��");  //ע�ᰴť����
		OKButton.setFont(new Font("������", Font.BOLD, 20));
		OKButton.setBounds(150, 397, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new OKButtonAction());
	}
	

	private void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("����");
		JMenu skin = new JMenu("����Ƥ��");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileExitMenuItem = new JMenuItem("�˳�", KeyEvent.VK_X);
		fileMenu2= new JMenuItem("����");
		skin1 = new JMenuItem("VIP SVIP �˽�һ��");
		skin.add(skin1);
		menuBar.add(fileMenu);
		menuBar.add(skin);
		setJMenuBar(menuBar);
		fileMenu.add(fileExitMenuItem);
		fileMenu.add(fileMenu2);
		// ���õ���˳�������˳�
		fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);
			}
		});
	}


	//ע�ᰴť������
	class OKButtonAction implements ActionListener {
		
		AccountServiceImpl asi =new AccountServiceImpl();
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			  user = asi.query(u_name.getText());
			  Bankinfo bif = new Bankinfo();
			  try {
				bu =asi.bu_query(u_name.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		if(!jtf_code.getText().equals("")){
			if(!vcode.getCode().equals(jtf_code.getText())) {
			  if (!u_name.getText().equals("")) {
					if (!u_password.getText().equals("")) {
						if (!u_password_1.getText().equals("")) {
							if (u_password.getText().equals(u_password_1.getText())) {                              
								if (bu==null) {   //  !u_name.getText().trim().equals(user.getName())                             //  !u_name.getText().equals(user.getName())
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
									string=string+df.format(new Date());
									string=string+"��ǰ����ע�᣻��ǰ�����ˣ�ҵ��Ա2"+"\n";
									string=string+u_name.getText()+" ע��ɹ�,���Լ�����¼��"+"\n";
									asi.addUser(u_name.getText(),u_password.getText());
									asi.addClientinfo2(u_name.getText());
									setVisible(false);
									LoginFrame frame = new LoginFrame();
									frame.setVisible(true);
									JOptionPane.showMessageDialog(null, "ע��ɹ�,���Լ�����¼��");
								} else {
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
									string=string+df.format(new Date());
									string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
									string=string+u_name.getText()+" �û����Ѿ����ڣ�"+"\n";
									JOptionPane.showMessageDialog(null, "�û����Ѿ����ڣ�");
								}
							} else {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df.format(new Date());
								string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
								string=string+u_name.getText()+" ����ȷ�ϲ�����"+"\n";
								JOptionPane.showMessageDialog(null, "����ȷ�ϲ�����");
							}

						} else {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df.format(new Date());
							string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
							string=string+u_name.getText()+" δ����ȷ�����룡"+"\n";
							JOptionPane.showMessageDialog(null, "δ����ȷ�����룡");
						}

					} else {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df.format(new Date());
						string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
						string=string+u_name.getText()+" δ�������룡"+"\n";
						JOptionPane.showMessageDialog(null, "δ�������룡");
					}
				} else {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
					string=string+u_name.getText()+" δ�����û�����"+"\n";
					JOptionPane.showMessageDialog(null, "δ�����û�����");
				}
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
					string=string+u_name.getText()+" ���������֤������"+"\n";
					JOptionPane.showMessageDialog(null, "���������֤������");
				}
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ����ע�᣻��ǰ�����ˣ��û�"+"\n";
					string=string+u_name.getText()+" δ������֤�룡"+"\n";
					JOptionPane.showMessageDialog(null, "δ������֤�룡");
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
	
	public boolean isValidCodeRight() {
		if(jtf_code == null) {
			return false;
		}else if(vcode == null) {
			return true;
		}else if(vcode.getCode().equals(jtf_code.getText())) {
			return true;
		}else 
			return false;
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Create the frame.
	 * @return 
	 */
	}

	

