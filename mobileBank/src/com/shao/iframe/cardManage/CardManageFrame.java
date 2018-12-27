package com.shao.iframe.cardManage;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;


/**
 * @author HGX
 *��ʾ��
 *���п����������� 
 *
 */
public class CardManageFrame extends JFrame{
	private JPanel contentPane;
	private Bankuser bu;
	private Clientinfo clientinfo;
	
public CardManageFrame(final String username,final String client_id) {
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 480);
		contentPane = new JPanel();
		setTitle("�𾴵��û�:"+username+" ��ӭ�������п�����");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addbankcardBtn = new JButton("һ����");
		addbankcardBtn.setFont(new Font("������", Font.BOLD, 20));
		addbankcardBtn.setMargin(new Insets(0,0,0,0));
		addbankcardBtn.setBounds(110, 290, 130, 30);
		contentPane.add(addbankcardBtn);
		addbankcardBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddbankcardFrame abk = new AddbankcardFrame(username, client_id);
				abk.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton delecardBtn = new JButton("����");
		delecardBtn.setFont(new Font("������", Font.BOLD, 20));
		delecardBtn.setMargin(new Insets(0,0,0,0));
		delecardBtn.setBounds(110, 200, 130, 30);
		contentPane.add(delecardBtn);
		delecardBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DelcardFrame dcf = new DelcardFrame(username, client_id);
				dcf.setVisible(true);
				setVisible(false);
			}
		});
		
		
		
		JButton create_creditBtn = new JButton("���ÿ���ͨ");
		create_creditBtn.setFont(new Font("������", Font.BOLD, 20));
		create_creditBtn.setMargin(new Insets(0,0,0,0));
		create_creditBtn.setBounds(110, 110, 130, 30);
		contentPane.add(create_creditBtn);
		create_creditBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddcreCardFrame abk = new AddcreCardFrame(username, client_id);
				abk.setVisible(true);
				setVisible(false);
			}
		});
		
		//���ذ�ť
		JButton rollback = new JButton("����");
		rollback.setFont(new Font("������", Font.BOLD, 15));
		rollback.setBounds(120, 380, 113, 27);
		contentPane.add(rollback);
		rollback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(username,client_id);
				setVisible(false);
				af.setVisible(true);			
			}		
		});
	
}
}
