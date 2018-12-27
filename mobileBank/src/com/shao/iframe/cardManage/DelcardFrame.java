package com.shao.iframe.cardManage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shao.iframe.AtmFrame;
import com.shao.model.Bankcard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.model.Bankuser;
import com.shao.model.User;
import com.shao.model.Bankcard;

/**
 * @author HGX
 *��ʾ��
 *�������� 
 *
 */

public class DelcardFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField jf_delcardid;
	private JTextField jf_cardpwd;
	private Bankuser bu;
	private Bankcard bkd;
	final AccountServiceImpl asi =new AccountServiceImpl();
	
	public DelcardFrame(final String username, final String client_id) {
		setTitle("�𾴵� " + username+"�� ���ã�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lbldelcard = new JLabel("��󿨺�:");
		lbldelcard.setFont(new Font("������", Font.BOLD, 18));
		lbldelcard.setBounds(70, 140, 130, 28);
		contentPane.add(lbldelcard);

		jf_delcardid = new JTextField();
		jf_delcardid.setBounds(190, 145, 130, 21);
		contentPane.add(jf_delcardid);
		jf_delcardid.setColumns(10);
		
		JLabel lblpwd= new JLabel("��������:");
		lblpwd.setFont(new Font("������", Font.BOLD, 18));
		lblpwd.setBounds(70, 240, 130, 28);
		contentPane.add(lblpwd);

		jf_cardpwd = new JTextField();
		jf_cardpwd.setBounds(190, 245, 130, 21);
		contentPane.add(jf_cardpwd);
		jf_cardpwd.setColumns(10);
		
		JButton OKButton = new JButton("ȷ��");
		OKButton.setFont(new Font("������", Font.BOLD, 17));
		OKButton.setBounds(104, 345, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bu = asi.bu_query(username);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String string=new String();
				bkd = asi.card_query(jf_delcardid.getText());
				if(bkd!=null){
				if(jf_cardpwd.getText().equals(bkd.getCard_ps())){
					if(bkd.getUid().equals(bu.getUid())){
					int res = JOptionPane.showConfirmDialog(null, "ȷ�Ͻ��˿���", "���ȷ��", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ���򣺽����������ˣ�ҵ��Ա11"+"\n";
						asi.del_card(jf_delcardid.getText());
						string=string+"���ɹ���"+"\n";
						JOptionPane.showMessageDialog(null, "���ɹ���");
					}else{
						
					}
					}else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ���򣺽����������ˣ��û�"+"\n";
						string=string+"�������������Ŀ��ţ�"+"\n";
						JOptionPane.showMessageDialog(null, "�������������Ŀ��ţ�");
					}
				}else{
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df1.format(new Date());
					string=string+"��ǰ���򣺽����������ˣ��û�"+"\n";
					string=string+"���벻��ȷ��"+"\n";
					JOptionPane.showMessageDialog(null, "���벻��ȷ��");
				}
			}else{
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				string=string+df1.format(new Date());
				string=string+"��ǰ���򣺽����������ˣ��û�"+"\n";
				string=string+"������Ŀ��Ų����ڣ�"+"\n";
				JOptionPane.showMessageDialog(null, "������Ŀ��Ų����ڣ�");
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
		JButton rollback = new JButton("����");
		rollback.setFont(new Font("������", Font.BOLD, 20));
		rollback.setBounds(260, 345, 113, 27);
		contentPane.add(rollback);
		rollback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CardManageFrame cmf = new CardManageFrame(username,client_id);
				setVisible(false);
				cmf.setVisible(true);			
			}		
		});
	}

}
