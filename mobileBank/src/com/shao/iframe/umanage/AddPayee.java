package com.shao.iframe.umanage;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.umanage.AddClientinfo.editButtonAction;
import com.shao.model.Bankuser;
import com.shao.model.Payee;


/**��ӿͻ����տ���
 * @author Administrator
 *
 */

public class AddPayee extends JFrame{

	private JPanel contentPane;
	private JTextField payee_id;  //�տ���ID�����
	private JTextField payee_uid;		//�˻�ID�����
	private JTextField payee_name;		//�տ������������
	private JTextField payee_card;		//�տ��˿��������
	private JTextField belong_bank;		//�����������������
	private Payee pye;
	private Bankuser bu;
	private String name;
	final AccountServiceImpl asi =new AccountServiceImpl();
	
	public AddPayee(final String name,final String client_id) {
		this.name = name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 500, 400);
		setTitle(name);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel lblNewLabel = new JLabel("�տ���ID ��");
//		lblNewLabel.setFont(new Font("������", Font.BOLD, 20));
//		lblNewLabel.setBounds(105, 72, 126, 30);
//		contentPane.add(lblNewLabel);
//		
//		payee_id = new JTextField();		
//		payee_id.setBounds(235, 77, 152, 20);
//		contentPane.add(payee_id);
//		payee_id.setColumns(10);
		
//		JLabel lblNewLabel2 = new JLabel("�� �� ID ��");
//		lblNewLabel2.setFont(new Font("������", Font.BOLD, 20));
//		lblNewLabel2.setBounds(105, 112, 126, 30);
//		contentPane.add(lblNewLabel2);
//		
//		payee_uid = new JTextField();			//�˻�ID�����
//		payee_uid.setBounds(235, 117, 152, 20);
//		contentPane.add(payee_uid);
//		payee_uid.setColumns(10);
//		
		
		JLabel lblNewLabel3 = new JLabel("�տ����� :");
		lblNewLabel3.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel3.setBounds(110, 100, 126, 30);
		contentPane.add(lblNewLabel3);
		
		payee_name = new JTextField();			//�ͻ����������
		payee_name.setBounds(225, 105, 152, 20);
		contentPane.add(payee_name);
		payee_name.setColumns(8);
		
		JLabel lblNewLabel4 = new JLabel("�տ�� :");
		lblNewLabel4.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel4.setBounds(110, 170, 126, 30);
		contentPane.add(lblNewLabel4);
		
		payee_card = new JTextField();			//�տ�������
		payee_card.setBounds(225, 175, 152, 20);
		contentPane.add(payee_card);
		payee_card.setColumns(19);
		
		JLabel lblNewLabel5 = new JLabel("�������� :");
		lblNewLabel5.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel5.setBounds(110, 240, 126, 30);
		contentPane.add(lblNewLabel5);
		
		belong_bank = new JTextField();			//�������������
		belong_bank.setBounds(225, 245, 152, 20);
		contentPane.add(belong_bank);
		belong_bank.setColumns(15);
		

	JButton backButton_1 = new JButton("����");
	backButton_1.setFont(new Font("������", Font.BOLD, 18));
	backButton_1.setBounds(282, 295, 113, 27);
	contentPane.add(backButton_1);
	backButton_1.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			AtmFrame af = new AtmFrame(name,client_id);
			setVisible(false);
			af.setVisible(true);			
		}		
	});
			JButton editButton = new JButton("���");
		editButton.setFont(new Font("������", Font.BOLD, 18));
		editButton.setBounds(102, 295, 113, 27);
		contentPane.add(editButton);
		editButton.addActionListener(new addButtonAction());
	}
	
	class addButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			pye = asi.pye_query(payee_card.getText());
			try {
				bu = asi.bu_query(name);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
//			try {
//				bu = asi.bu_queryuid(payee_uid.getText());
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			if (!payee_id.getText().equals("")) {
//				if (!payee_uid.getText().equals("")) {
					if (!payee_name.getText().equals("")) {
						if (!payee_card.getText().equals("")) {  
							if (!belong_bank.getText().equals("")) {
//						if( bu == null){
							if (pye==null) {   //  !u_name.getText().trim().equals(user.getName())                             //  !u_name.getText().equals(user.getName())
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ��������տ��ˣ������ˣ�ҵ��Ա20"+"\n";
								asi.addpayee(bu.getUid(),payee_name.getText(),payee_card.getText(),belong_bank.getText());
								setVisible(true);
//								atmFrame frame = new atmFrame(name);
//								user_query = asi.query(name);
//								frame.setVisible(true);	
								string=string+"��ӳɹ���";
								JOptionPane.showMessageDialog(null, "��ӳɹ���");
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ��������տ��ˣ������ˣ��û�"+"\n";
								string=string+"�տ�ID�Ѿ����ڣ�";
								JOptionPane.showMessageDialog(null, "�տ�ID�Ѿ����ڣ�");
							}
//							}else{
//								JOptionPane.showMessageDialog(null, "�˻�ID�Ѿ����ڣ�");
//							}
							}else{
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ��������տ��ˣ������ˣ��û�"+"\n";
								string=string+"δ�����������У�";
								JOptionPane.showMessageDialog(null, "δ�����������У�");
							}
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ��������տ��ˣ������ˣ��û�"+"\n";
							string=string+"δ�����տ��˿��ţ�";
							JOptionPane.showMessageDialog(null, "δ�����տ��˿��ţ�");
						}

					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ��������տ��ˣ������ˣ��û�"+"\n";
						string=string+"�տ���������";
						JOptionPane.showMessageDialog(null, "�տ���������");
					}
					try {
						FileWriter writer = new FileWriter("log.txt", true);
			            writer.write(string);
			            writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//				} else {
//					JOptionPane.showMessageDialog(null, "δ�����˻�ID��");
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "δ�����տ���ID��");
//			}
		}
	
	}
}
