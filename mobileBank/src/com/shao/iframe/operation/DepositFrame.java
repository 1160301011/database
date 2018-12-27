package com.shao.iframe.operation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.model.Bankcard;
import com.shao.model.User;
import com.shao.model.Bankuser;

/**
 * @author HGX
 *��ʾ��
 *������ 
 *
 */

public class DepositFrame extends JFrame {
	
	private JComboBox<String> card_choose_JCo;	
	private JPanel contentPane;
	private JTextField inputField,chocard_Field;
	private User user_query;
	private User user_modMoney;
	private Bankuser bank_user_query;
	private Bankcard bkd;
	/**
	 * Create the frame.
	 */
	/**
	 * @param username  �û���
	 * @param client_id  �ͻ���
	 */
	public DepositFrame(final String username,final String client_id) {
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 380);
		setTitle(username);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�����:");
		lblNewLabel.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel.setBounds(80, 92, 121, 27);
		contentPane.add(lblNewLabel);
		
		inputField = new JTextField();			//ת���û��������
		inputField.setBounds(225, 95, 133, 24);
		contentPane.add(inputField);
		inputField.setColumns(10);
		
		JLabel lbldepcard = new JLabel("����:");
		lbldepcard.setFont(new Font("������", Font.BOLD, 20));
		lbldepcard.setBounds(80, 162, 121, 27);
		contentPane.add(lbldepcard);
		
		chocard_Field = new JTextField();
		chocard_Field.setBounds(225, 165, 130, 21);
//		card_choose_JCo.addItem(card_id1);
		contentPane.add(chocard_Field);
//		cre_closepay.addActionListener();
		chocard_Field.setColumns(10);
		
													//���ڴ�ť 
		JButton CurrBtn = new JButton("���ڴ���");
		CurrBtn.setFont(new Font("������", Font.BOLD, 15));
		CurrBtn.setBounds(90, 250, 130, 27);
		contentPane.add(CurrBtn);
		CurrBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				if(Float.parseFloat(inputField.getText())<100000){		//���ս����޶�ʮ��
					try {
						bank_user_query = asi.bu_query(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					bkd = asi.card_query(chocard_Field.getText());
					double ubalance = bank_user_query.getUserbalance()-Double.parseDouble(inputField.getText());
					double bcurrent = bkd.getCurrent()+Double.parseDouble(inputField.getText());
					DecimalFormat df = new DecimalFormat( "0.00 "); 
					asi.update(bank_user_query.getUsername(), ubalance);
					asi.upcurrent(chocard_Field.getText(),bcurrent);
//						setVisible(false);
//						AtmFrame frame = new AtmFrame(username,client_id);
//						frame.setVisible(true);
						try {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ���򣺴��ף������ˣ�ҵ��Ա13"+"\n";
							bank_user_query = asi.bu_query(username);
						} catch (SQLException e1) {
							string=string+e1.toString()+"\n";
							e1.printStackTrace();
						}
						string=string+"���׳ɹ���"+"\n" + "��ǰ�˻����Ϊ��" + bank_user_query.getUserbalance();
						JOptionPane.showMessageDialog(null, "���׳ɹ���"+"\n" + "��ǰ�˻����Ϊ��" + bank_user_query.getUserbalance());							
				}else{
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					string=string+df1.format(new Date());
					string=string+"��ǰ���򣺴��ף������ˣ��û�"+"\n";
					string=string+"���������1000000�����������룡"+"\n";
					JOptionPane.showMessageDialog(null, "���������1000000�����������룡");
					inputField.setText("");
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
		
		JButton DeathBtn = new JButton("���ڴ���");
		DeathBtn.setFont(new Font("������", Font.BOLD, 15));
		DeathBtn.setBounds(290, 250, 130, 27);
		contentPane.add(DeathBtn);
		DeathBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Float.parseFloat(inputField.getText())<100000){		//���ս����޶�ʮ��
					try {
						bank_user_query = asi.bu_query(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					bkd = asi.card_query(chocard_Field.getText());
					double ubalance = bank_user_query.getUserbalance()-Double.parseDouble(inputField.getText());
					double bdeath = bkd.getDeath()+Double.parseDouble(inputField.getText());
					DecimalFormat df = new DecimalFormat( "0.00 "); 
					asi.update(bank_user_query.getUsername(), ubalance);
					asi.updeath(chocard_Field.getText(),bdeath);
//						setVisible(false);
//						AtmFrame frame = new AtmFrame(username,client_id);
//						frame.setVisible(true);
						try {
							bank_user_query = asi.bu_query(username);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "���׳ɹ���"+"\n" + "��ǰ�˻����Ϊ��" + bank_user_query.getUserbalance());							
				}else{
					JOptionPane.showMessageDialog(null, "���������1000000�����������룡");
					inputField.setText("");
				}				
			}
			
		});
		
		//���ذ�ť
		JButton rebackBtn = new JButton("����");
		rebackBtn.setFont(new Font("������", Font.BOLD, 15));
		rebackBtn.setBorderPainted(true); // �ޱ߿�
		rebackBtn.setOpaque(false); // ���ð�ť͸��
		rebackBtn.setContentAreaFilled(false); // �رյ�ɫ
		rebackBtn.setBounds(350, 310, 113, 27);
		contentPane.add(rebackBtn);
		rebackBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				OperationFrame of = new OperationFrame(username,client_id);
				setVisible(false);
				of.setVisible(true);			
			}		
		});
		//�����޶��ǩ
		JLabel lblNewLabel_1 = new JLabel("*�޶�100000");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("������", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(369, 98, 105, 18);
		contentPane.add(lblNewLabel_1);
	}
	
	class card_chooseAction implements ActionListener {
		
		
		public void actionPerformed(ActionEvent e) {
			int i=card_choose_JCo.getSelectedIndex()+1;
			String s=(String)card_choose_JCo.getSelectedItem();
			System.out.println("��ѡ�е��ǵ�"+i+"��"+",������:"+s);
		}
	}
}
