package com.shao.iframe.operation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import com.shao.model.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author HGX
 *��ʾ��
 *���ÿ����ڼ������ 
 *
 */
public class ComCreditcard extends JFrame implements ActionListener {

	 private JPanel contentPane;
	 private JTextField textField;  //�����
	 private JLabel lbltitle,lblshow_return_money; //С�����ǩ
	 private JButton btnNewButton;  //��ť
	 private JComboBox comboBox;   //����ѡ���
	 private String username;    
	 private String client_id;
	 
	 public ComCreditcard(final String username,final String client_id) {
		 	this.username = username;
		 	this.client_id = client_id;
		 
		 	setTitle("�𾴵��û�: " + username  +"    �� �� �� �� ��");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 500, 500);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			setLocationRelativeTo(null);
			contentPane.setLayout(null);
			//�����ǩ
			JLabel lbltitle = new JLabel();
			lbltitle.setFont(new Font("������", Font.BOLD, 20));
			lbltitle.setForeground(Color.BLUE); // ������ɫ����
			lbltitle.setText("���ڻ������");
			lbltitle.setBounds(15, 8, 140, 24);
			contentPane.add(lbltitle);
			
			JLabel lblshow_return_money = new JLabel();
			lblshow_return_money.setFont(new Font("������", Font.BOLD, 15));
			lblshow_return_money.setForeground(Color.BLACK); // ������ɫ����
			lblshow_return_money.setText("������");
			lblshow_return_money.setBounds(40, 50, 113, 34);
			contentPane.add(lblshow_return_money);
			
	 }
	 

	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		ComCreditcard ccd = new ComCreditcard(null, null);
					 ccd.setVisible(true);
		
	}
}
