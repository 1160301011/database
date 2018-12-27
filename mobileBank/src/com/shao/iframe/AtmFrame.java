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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
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
/**
 * @author HGX
 *��ʾ��
 *����ϵͳ������ 
 *
 */
public class AtmFrame extends JFrame implements ActionListener, HyperlinkListener{
	
	private String name;
	private String client_id;
	private JEditorPane pane;
	private JPanel contentPane;
	private Bankinfo bif;
	private String Strbank_id;
	private com.shao.model.Bankuser bu;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AtmFrame frame = new AtmFrame(null,null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the frame.
	 * @param bank_id 
	 */
	public AtmFrame(final String name,final String client_id) {
		this.name = name;
		this.client_id = client_id;
		MenuBar();
		
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// ����pane�ĳ������Ӽ���
		pane.addHyperlinkListener(this);
		this.add(new JScrollPane(pane), "Center");
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		
		try {
			bu = asi.bu_query(name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 600);
		contentPane = new JPanel();
		setTitle(name);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltitle = new JLabel();
		lbltitle.setFont(new Font("΢���ź�", Font.BOLD, 25));
		lbltitle.setForeground(Color.BLACK); // ������ɫ����
		lbltitle.setText("ATM  ��  ��");
		lbltitle.setBounds(70, 35, 162, 34);
		contentPane.add(lbltitle);
		
			//������Ϣ��ť	
		JButton queryButton2 = new JButton();
		queryButton2.setText("������Ϣ");
		queryButton2.setFont(new Font("������κ", Font.BOLD, 20));
		queryButton2.setBounds(80, 100, 123, 30);
		contentPane.add(queryButton2);
		queryButton2.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e) {
		
			Strbank_id = "1";
			bif = asi.bif_query(Strbank_id);
			String string=new String();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			string=string+df.format(new Date());
			string=string+"��ǰ���򣺲鿴������Ϣ�������ˣ�ҵ��Ա4"+"\n";
			string=string+"��ǰ��������:"+bif.getBank_name()+"\n"+"���е�ַ:"+bif.getBank_addr()+"\n";
			JOptionPane.showMessageDialog(null,   //����λ����Ļ��
		              "��ǰ��������:"+bif.getBank_name()+"\n"+"���е�ַ:"+bif.getBank_addr()+"\n","��Ϣ��ʾ",
		              JOptionPane.INFORMATION_MESSAGE);		
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
		JButton operationBtn = new JButton("��ȡҵ��");
		operationBtn.setFont(new Font("������", Font.BOLD, 20));
		operationBtn.setMargin(new Insets(0,0,0,0));
		operationBtn.setBounds(80, 170, 123, 30);
		contentPane.add(operationBtn);
		operationBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!bu.getUserstatus().equals("3")){
				OperationFrame of = new OperationFrame(name, client_id);
				setVisible(false);
				of.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "�����˻��������޷����ף�");
				}
			}
		});
		
		JButton payBtn = new JButton("�ɷ�ҵ��");
		payBtn.setFont(new Font("������", Font.BOLD, 20));
		payBtn.setMargin(new Insets(0,0,0,0));
		payBtn.setBounds(80, 240, 123, 30);
		contentPane.add(payBtn);
		payBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!bu.getUserstatus().equals("3")){
				PaymentFrame of = new PaymentFrame(name, client_id);
				setVisible(false);
				of.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "�����˻��������޷����ף�");
				}
			}
		});
		
		JButton queryBusiness = new JButton("��ѯҵ��");
		queryBusiness.setFont(new Font("������", Font.BOLD, 20));
		queryBusiness.setMargin(new Insets(0,0,0,0));
		queryBusiness.setBounds(80, 310, 123, 30);
		contentPane.add(queryBusiness);
		queryBusiness.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				QueryFrame qf = new QueryFrame(name, client_id);
				setVisible(false);
				qf.setVisible(true);
			}
		});
		JButton cardmanageBtn = new JButton("���п�����");
		cardmanageBtn.setFont(new Font("������", Font.BOLD, 20));
		cardmanageBtn.setMargin(new Insets(0,0,0,0));
		cardmanageBtn.setBounds(70, 380, 140, 30);
		contentPane.add(cardmanageBtn);
		cardmanageBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CardManageFrame abk = new CardManageFrame(name, client_id);
				abk.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton umanageBtn = new JButton("�˻�����");
		umanageBtn.setFont(new Font("������", Font.BOLD, 20));
		umanageBtn.setMargin(new Insets(0,0,0,0));
		umanageBtn.setBounds(80, 450, 123, 30);
		contentPane.add(umanageBtn);
		umanageBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UmanageFrame of = new UmanageFrame(name, client_id);
				setVisible(false);
				of.setVisible(true);
			}
		});
		
	}
	public void actionPerformed(ActionEvent e) {
		
	}
	public void hyperlinkUpdate(HyperlinkEvent e) {
		
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
		JMenuItem set_ad_JMitem = new JMenuItem("��̨");
		 
		set_JM.add(set_chuser_JMitem);
		set_JM.add(set_ad_JMitem);
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
				AtmFrame af = new AtmFrame(name,client_id);
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
                	AdminFrame af = new AdminFrame(name,client_id);
    				setVisible(false);
    				af.setVisible(true);
                }
//				Object[] possibleValues = { "�ͻ�����", "ά��", "����" }; 
//				Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "��̨", 
//				JOptionPane.INFORMATION_MESSAGE, null, 
//				possibleValues, possibleValues[0]);
			} 
	});
	}

}
