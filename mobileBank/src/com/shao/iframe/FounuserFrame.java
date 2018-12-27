package com.shao.iframe;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JFrame;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.bill.PaymentFrame;
import com.shao.iframe.umanage.UmanageFrame;
import com.shao.model.*;
/**
 * @author HGX
 *��ʾ��
 *�һ�������� 
 *
 */

public class FounuserFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextField username_field;
	private JTextField idcard_field;
	private JTextField cname_Field;
	private JTextArea showpwd_jta;
	private com.shao.model.Clientinfo cif;
	private com.shao.model.Bankuser bu;

	public FounuserFrame(){
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 520);
		setTitle("�һ�����");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbluname = new JLabel("�˻�����");
		lbluname.setFont(new Font("������", Font.BOLD, 20));
		lbluname.setBounds(100, 72, 126, 30);
		contentPane.add(lbluname);
		
		username_field = new JTextField();
		username_field.setBounds(230, 77, 150, 24);
		contentPane.add(username_field);
		username_field.setColumns(10);
		
		JLabel lblidcard = new JLabel("���֤�ţ�");
		lblidcard.setFont(new Font("������", Font.BOLD, 20));
		lblidcard.setBounds(100, 141, 126, 30);
		contentPane.add(lblidcard);
		
		idcard_field = new JTextField();
		idcard_field.setBounds(230, 141, 150, 24);
		contentPane.add(idcard_field);
		idcard_field.setColumns(10);
		
		JLabel lblcname = new JLabel("��ʵ������");
		lblcname.setFont(new Font("������", Font.BOLD, 20));
		lblcname.setBounds(100, 210, 112, 30);
		contentPane.add(lblcname);
		
		cname_Field = new JTextField();
		cname_Field.setBounds(230, 215, 152, 24);
		contentPane.add(cname_Field);
		cname_Field.setColumns(10);
		
		showpwd_jta = new JTextArea(5,10);
		showpwd_jta.setBounds(105,275,300,50);
		showpwd_jta.setLineWrap(true);  //�Զ�����
		showpwd_jta.setFont(new Font("г��",Font.BOLD|Font.ITALIC,16));
        // ��ӵ��������
		contentPane.add(showpwd_jta);
		
		JButton resetBtn = new JButton("����");
		resetBtn.setFont(new Font("������", Font.BOLD, 20));
		resetBtn.setMargin(new Insets(0,0,0,0));
		resetBtn.setBounds(280, 355, 130, 30);
		contentPane.add(resetBtn);
		resetBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 clear();
			}
		});
			
		
		JButton findpwdBtn = new JButton("�һ�����");
		findpwdBtn.setFont(new Font("������", Font.BOLD, 20));
		findpwdBtn.setMargin(new Insets(0,0,0,0));
		findpwdBtn.setBounds(95, 355, 130, 30);
		contentPane.add(findpwdBtn);
		findpwdBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				try {
					bu = asi.bu_query(username_field.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					cif = asi.cinfo_query(bu.getUid());
					
				if(!username_field.getText().equals("")){
					if(!idcard_field.getText().equals("")){
						if(!cname_Field.getText().equals("")){
							if(idcard_field.getText().equals(cif.getClient_idcard())){
								if(cname_Field.getText().equals(cif.getClient_name())){
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
									string=string+df.format(new Date());
									string=string+"��ǰ�����һ����룻��ǰ�����ˣ�ҵ��Ա3"+"\n";
									string=string+cname_Field.getText()+"�ѳɹ��һ�����"+"\n";
									asi.Foun_upuser_sta(username_field.getText());
									showpwd_jta.setText("����������:"+bu.getUserpwd()+"�����Ʊ��ܡ�");
								}else{
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
									string=string+df.format(new Date());
									string=string+"��ǰ�����һ����룻��ǰ�����ˣ��û�"+"\n";
									string=string+cname_Field.getText()+" ��������������֤������"+"\n";
									JOptionPane.showMessageDialog(null, "��������������֤������");
								}
							}else{
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df.format(new Date());
								string=string+"��ǰ�����һ����룻��ǰ�����ˣ��û�"+"\n";
								string=string+cname_Field.getText()+" ���֤����"+"\n";
								JOptionPane.showMessageDialog(null, "���֤����");
							}
						}else{
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df.format(new Date());
							string=string+"��ǰ�����һ����룻��ǰ�����ˣ��û�"+"\n";
							string=string+cname_Field.getText()+" ������������"+"\n";
							JOptionPane.showMessageDialog(null, "������������");
						}
					}else{
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df.format(new Date());
						string=string+"��ǰ�����һ����룻��ǰ�����ˣ��û�"+"\n";
						string=string+cname_Field.getText()+" ���������֤�ţ�"+"\n";
						JOptionPane.showMessageDialog(null, "���������֤�ţ�");
					}
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df.format(new Date());
					string=string+"��ǰ�����һ����룻��ǰ�����ˣ��û�"+"\n";
					string=string+cname_Field.getText()+" �������˺ţ�"+"\n";
					JOptionPane.showMessageDialog(null, "�������˺ţ�");
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
		
		JButton reback = new JButton("����");
		reback.setFont(new Font("������", Font.BOLD, 18));
		reback.setBounds(350, 455, 113, 27);
		reback.setBorder(null);
		reback.setBorderPainted(false); // �ޱ߿�
		reback.setOpaque(false); // ���ð�ť͸��
		reback.setForeground(Color.RED); // �����������
		reback.setContentAreaFilled(false); // �رյ�ɫ
		// forgetBtn.setFocusPainted(false); //�۽���
		reback.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		contentPane.add(reback);
		reback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);			
			}		
		});
	}
	
	//���ð�ť
	private void clear() {
		username_field.setText("");    //����Ϊ��
		idcard_field.setText("");  
		cname_Field.setText("");
		showpwd_jta.setText("");
	}
}
