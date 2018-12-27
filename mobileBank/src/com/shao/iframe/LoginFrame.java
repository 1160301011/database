package com.shao.iframe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;
import com.shao.Service.impl.AccountServiceImpl;
import com.shao.model.*;
/**
 * @author hgx
 * ��ʾ��
 * ��¼������
 */
public class LoginFrame extends JFrame {
	private com.shao.model.Bankuser bu;
	private Image image;
	private static final long serialVersionUID = -1588458291133087637L;
	private JPanel contentPane;
	private JTextField bank_name_Field;
	private JPasswordField bank_pwd_Field;
	private LoginFrame logframe;
	private Container container;
	final AccountServiceImpl asi = new AccountServiceImpl();
	// ��¼�¼�������
	class loginAction implements ActionListener {
		@SuppressWarnings({ "deprecation", "null" })
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			// user user = new user();
			Bankuser bu = new Bankuser();
			Clientinfo cfo = new Clientinfo();
			Bankinfo bif = new Bankinfo();
			try {
				cfo = asi.cfo_check(bank_name_Field.getText());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// ��ȡ�������Ϣ �����������Ƿ��и��û�����
			bu = asi.bankuser_check(bank_name_Field.getText(),
					bank_pwd_Field.getText());
			if (!bank_name_Field.getText().equals("")) {
				if (bu.getUsername()!=null) {
					if (bu.getUserpwd().equals(bank_pwd_Field.getText())) {
						if(bu.getUserstatus().equals("1")||bu.getUserstatus().equals("3")){
						try {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df.format(new Date());
							string=string+bu.getUsername()+"�ѳɹ���¼����ǰ�����ˣ�ҵ��Ա1"+"\n";
							AtmFrame frame = new AtmFrame(bu.getUsername(),
									cfo.getClient_id());
							frame.setTitle(bu.getUsername());
							frame.setVisible(true);
							setVisible(false);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						}else{
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df.format(new Date());
							string=string+"��ǰ���򣺵�¼����ǰ�����ˣ��û�"+bu.getUsername()+"\n";
							string=string+"�˺��Ѷ��ᣡ���Ƚ�����"+"\n";
							JOptionPane.showMessageDialog(null, "�˺��Ѷ��ᣡ���Ƚ�����");
						}
					} else {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df.format(new Date());
						string=string+"��ǰ���򣺵�¼����ǰ�����ˣ��û�"+bu.getUsername()+"\n";
						string=string+ "���벻��ȷ������������"+"\n";
						JOptionPane.showMessageDialog(null, "���벻��ȷ������������");
//						bank_name_Field.setText("");
						bank_pwd_Field.setText("");
					}
				} else {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ���򣺵�¼����ǰ�����ˣ��û�"+bu.getUsername()+"\n";
					string=string+ "�Ҳ������û�������ע�ᣡ"+"\n";
					JOptionPane.showMessageDialog(null, "�Ҳ������û�������ע�ᣡ");
					bank_name_Field.setText("");
					bank_pwd_Field.setText("");
				}
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df.format(new Date());
				string=string+"��ǰ���򣺵�¼����ǰ�����ˣ��û�"+bu.getUsername()+"\n";
				string=string+ "�������û�����"+"\n";
				JOptionPane.showMessageDialog(null, "�������û�����");
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

	// ע���¼�������
	class signAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SignFrame frame = new SignFrame();
			frame.setVisible(true);
			setVisible(false);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * ��Ҫ�޸�Ƥ���Ļ���ֻ��Ҫ���ģ�������������Ĳ���������ĳ�ʲô�������Դ򿪣�Referenced
					 * Libraries -> ���substance.jar ->
					 * �ҵ�org.jvnet.substance.skin����� ->
					 * �������SubstanceDustCoffeeLookAndFeel �滻��
					 * �ոմ򿪵İ��µ�����һ����Substance....LookAndFeel������
					 */
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					// ���簴������Ĳ��裬���Կ���һ���У�"SubstanceOfficeBlue2007LookAndFeel.class"����Ҫ�滻�����Ƥ�����Ϳ�������������д
					// UIManager
					// .setLookAndFeel(new
					// org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// ����һ�£�Ƥ���Ϳ��Ի���
//					 ��Ҫ��ϸ�˽��ͬѧ������ȥ�ٶ��������������substance.jar
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				LoginFrame browser = new LoginFrame();
				browser.setVisible(true);
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {

		logframe = this;
		// �����ƴ��ڱ�ǩ�ͱ���
		this.setUndecorated(true);
		// ��ȡ��Ļ��Ⱥ͸߶�
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		// ����ͼƬ
		ImageIcon icon = new ImageIcon("images/bg5.png");
		// ��ͼƬ����label��
		JLabel lbl_showbg = new JLabel(icon);
		// ����label�Ĵ�С
		lbl_showbg.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		// ��ȡ���ڵĵڶ��㣬��label����
		this.getLayeredPane().add(lbl_showbg, new Integer(Integer.MIN_VALUE));
		// ��ȡframe�Ķ�������,������Ϊ͸��
		JPanel j = (JPanel) this.getContentPane();
		j.setOpaque(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, screenWidth, screenHeight);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton forgetBtn = new JButton("�������룿");
		forgetBtn.setBorder(null);
		forgetBtn.setBorderPainted(false); // �ޱ߿�
		forgetBtn.setOpaque(false); // ���ð�ť͸��
		forgetBtn.setForeground(Color.LIGHT_GRAY); // �����������
		forgetBtn.setBounds(485, 480, 115, 20);
		forgetBtn.setContentAreaFilled(false); // �رյ�ɫ
		// forgetBtn.setFocusPainted(false); //�۽���
		forgetBtn.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(forgetBtn);
		forgetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FounuserFrame fof = new FounuserFrame();
				fof.setVisible(true);
			}
		});
	
		
		JButton lossBtn = new JButton("��ʧ");
		lossBtn.setBorder(null);
		lossBtn.setBorderPainted(false); // �ޱ߿�
		lossBtn.setOpaque(false); // ���ð�ť͸��
		lossBtn.setForeground(Color.WHITE); // �����������
		lossBtn.setBounds(780, 480, 115, 20);
		lossBtn.setContentAreaFilled(false); // �رյ�ɫ
		// forgetBtn.setFocusPainted(false); //�۽���
		lossBtn.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		contentPane.add(lossBtn);
		lossBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bu =  asi.bu_query(bank_name_Field.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(!bank_name_Field.getText().equals("")){
				
				if(bu.getUserstatus().equals("1")){
					int res = JOptionPane.showConfirmDialog(null, "ȷ�Ϲ�ʧ��", "��ʧȷ��", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
					//�����˺�״̬Ϊ����
					asi.upuser_status(bank_name_Field.getText());
					JOptionPane.showMessageDialog(null, "�˺��Ѷ��ᣡ");
					}else{
						
					}
				}else{
					JOptionPane.showMessageDialog(null, "�˺��ѹ�ʧ,������Ч��");
				}
				}else{
					JOptionPane.showMessageDialog(null, "�������˺ţ�");
				}
				
			}
		});
		

		JButton LoginButton = new JButton(); // ��¼��ť
		LoginButton.setContentAreaFilled(false);
		LoginButton.setBounds(540, 420, 120, 45);
		ImageIcon logbtn = new ImageIcon("images/ensure.png"); // ���ȷ����ťͼƬ
		Image temp = logbtn.getImage().getScaledInstance(
				LoginButton.getWidth(), LoginButton.getHeight(),
				logbtn.getImage().SCALE_DEFAULT);
		logbtn = new ImageIcon(temp);
		LoginButton.setIcon(logbtn);
		LoginButton.setToolTipText("image");
		contentPane.add(LoginButton);
		LoginButton.addActionListener(new loginAction());

		JButton signButton = new JButton(); // ע�ᰴť
		signButton.setContentAreaFilled(false);
		signButton.setBounds(720, 420, 120, 45);
		ImageIcon sigbtn = new ImageIcon("images/sign.png");
		Image temp2 = sigbtn.getImage().getScaledInstance(
				signButton.getWidth(), signButton.getHeight(),
				sigbtn.getImage().SCALE_DEFAULT);
		logbtn = new ImageIcon(temp2);
		signButton.setIcon(logbtn);
		signButton.setToolTipText("image");
		contentPane.add(signButton);
		signButton.addActionListener(new signAction());

		// JLabel u_nameLabel = new JLabel("\u7528\u6237\u540D\uFF1A"); // �û�����ǩ
		// u_nameLabel.setFont(new Font("������", Font.BOLD, 18));
		// u_nameLabel.setBounds(92, 49, 88, 37);
		// contentPane.add(u_nameLabel);

		// JLabel u_passwordLabel_1 = new JLabel("\u5BC6\u7801\uFF1A"); // �����ǩ
		// u_passwordLabel_1.setFont(new Font("������", Font.BOLD, 18));
		// u_passwordLabel_1.setBounds(400, 430, 88, 27);
		// contentPane.add(u_passwordLabel_1);

		bank_name_Field = new JTextField(); // �û��������
		bank_name_Field.setBounds(507, 245, 350, 50);
		bank_name_Field.setBorder(null);
		bank_name_Field.setOpaque(false);
		bank_name_Field.setForeground(Color.LIGHT_GRAY);
		bank_name_Field.setColumns(10);
		bank_name_Field.setSelectionColor(Color.GRAY);
		bank_name_Field.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		contentPane.add(bank_name_Field);

		bank_pwd_Field = new JPasswordField(); // ���������
		bank_pwd_Field.setBounds(507, 345, 350, 50);
		bank_pwd_Field.setBorder(null);
		bank_pwd_Field.setOpaque(false);
		bank_pwd_Field.setForeground(Color.LIGHT_GRAY);
		bank_pwd_Field.setColumns(10);
		bank_pwd_Field.setSelectionColor(Color.GRAY);
		bank_pwd_Field.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		contentPane.add(bank_pwd_Field);

		// ���ϵͳʱ����ʾ��ǩ
		JLabel time = new JLabel();
		this.getContentPane().add(time);
		this.setTimer(time);
		time.setBounds(50, 637, 300, 29);
		time.setForeground(Color.RED);
		time.setFont(new Font("������", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("�û�ָ��");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(1200, 647, 300, 29);
		lblNewLabel.setFont(new Font("������", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		// ��������Ϊ͸���ġ����򿴲���ͼƬ
		contentPane.setOpaque(false);
//		lblNewLabel.addAncestorListener(listener);

		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setVisible(true);
	}

	// ����Timer 1000msʵ��һ�ζ��� ʵ����һ���߳�
	private void setTimer(JLabel time) {
		final JLabel varTime = time;
		Timer timeAction = new Timer(1000, new ActionListener() {

			//����
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();
				// ת��������ʾ��ʽ
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				varTime.setText(df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
	}
}
