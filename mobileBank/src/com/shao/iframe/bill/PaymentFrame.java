package com.shao.iframe.bill;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.query.QuerytraFrame;
import com.shao.model.*;
/**
 * @author HGX
 *��ʾ��
 *����ɷ������� 
 *
 */

public class PaymentFrame extends JFrame{

	
	private JPanel contentPane;
	private Bankuser bu;
	private Clientinfo clientinfo;
	private Bankcard bkd;
	private static String client_id;
	private static String username;
	
	
	public PaymentFrame(final String username,final String client_id) {
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 550);
		contentPane = new JPanel();
		setTitle(username+"���ɷ�ҵ��");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton PhoneBtn = new JButton("�ֻ���ֵ");
		PhoneBtn.setFont(new Font("������", Font.BOLD, 20));
		PhoneBtn.setBounds(80, 90, 120, 27);
		contentPane.add(PhoneBtn);
		PhoneBtn.addActionListener(new ActionListener(){
			
				public void actionPerformed(ActionEvent e) {
					PayphoenFrame qf = new PayphoenFrame(username,client_id);
					setVisible(false);
					qf.setVisible(true);
				}
			});
		
		JButton supermaketBtn = new JButton("�����̳�");
		supermaketBtn.setFont(new Font("������", Font.BOLD, 20));
		supermaketBtn.setBounds(80, 160, 120, 27);
		contentPane.add(supermaketBtn);
		supermaketBtn.addActionListener(new ActionListener(){
			
				public void actionPerformed(ActionEvent e) {
					Supermaket skf = new Supermaket(username,client_id);
					setVisible(false);
					skf.setVisible(true);
				}
			});
		
		
		
		JButton traffic_Btn = new JButton("��ͨΥ�´���");
		traffic_Btn.setFont(new Font("������", Font.BOLD, 20));
		traffic_Btn.setMargin(new Insets(0,0,0,0));
		traffic_Btn.setBounds(67, 230, 150, 27);
		contentPane.add(traffic_Btn);
		traffic_Btn.addActionListener(new ActionListener(){
			
				public void actionPerformed(ActionEvent e) {
					 TrafficFrame trf = new TrafficFrame(username,client_id);
					setVisible(false);
					trf.setVisible(true);
				}
			});
		
		JButton LifeBtn = new JButton("����ɷ�");
		LifeBtn.setFont(new Font("������", Font.BOLD, 20));
		LifeBtn.setBounds(80, 300, 120, 27);
		contentPane.add(LifeBtn);
		LifeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 LifeFrame lf = new LifeFrame(username,client_id);
				setVisible(false);
				lf.setVisible(true);
			}
		});
		

		
		JButton MedicalBtn = new JButton("ҽ���ɷ�");
		MedicalBtn.setFont(new Font("������", Font.BOLD, 20));
		MedicalBtn.setMargin(new Insets(0,0,0,0));
		MedicalBtn.setBounds(80, 370, 120, 27);
		contentPane.add(MedicalBtn);
		MedicalBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 MedicalFrame lf = new MedicalFrame(username,client_id);
				setVisible(false);
				lf.setVisible(true);
			}
		});
		
		JButton rollback = new JButton("����");
		rollback.setFont(new Font("������", Font.BOLD, 15));
		rollback.setBounds(85, 450, 113, 27);
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
	

