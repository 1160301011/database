package com.shao.iframe.bill;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;

/**
 * @author HGX
 *��ʾ��
 *��ͨ�ɷѽ��� 
 *
 */

public class TrafficFrame extends JFrame{

	private JPanel contentPane;
	private JTextField pay_cardField;  
	private JTextField pay_moneyField;
	private JTextField pay_remarkField;
	private JPasswordField pay_pwdField;
	private com.shao.model.Bankcard query_paycard;
	private com.shao.model.Bankcard query_paycard_serial1;
	private com.shao.model.Bankcard bankcard_query_cardid;
	
//	private com.shao.model.bankcard 
	
	public TrafficFrame(final String username,final String client_id) {
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 400);
		setTitle("�𾴵�:"+username+ "��ӭ���뽻ͨ�ɷ�");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�������п���");
		lblNewLabel.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 72, 126, 30);
		contentPane.add(lblNewLabel);
		
		pay_cardField = new JTextField();
		pay_cardField.setBounds(245, 77, 150, 24);
		contentPane.add(pay_cardField);
		pay_cardField.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("�����");
		lblNewLabel2.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel2.setBounds(105, 141, 126, 30);
		contentPane.add(lblNewLabel2);
		
		pay_moneyField = new JTextField();
		pay_moneyField.setBounds(245, 141, 150, 24);
		contentPane.add(pay_moneyField);
		pay_moneyField.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("֧�����룺");
		lblNewLabel_1.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel_1.setBounds(105, 210, 112, 30);
		contentPane.add(lblNewLabel_1);
		
		pay_pwdField = new JPasswordField();
		pay_pwdField.setBounds(243, 210, 152, 24);
		contentPane.add(pay_pwdField);
		pay_pwdField.setColumns(10);
		

		
		JButton OKButton = new JButton("ȷ��");
		OKButton.setFont(new Font("������", Font.BOLD, 17));
		OKButton.setBounds(104, 270, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				int res = JOptionPane.showConfirmDialog(null, "ȷ�ϴ˴νɷѣ�", "�ɷ�ȷ��", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
//					try {
//						bank_user_query_to = asi.bu_query(out_nameField.getText());
					query_paycard = asi.tra_bkd_query(pay_cardField.getText());
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
				if(!pay_cardField.getText().equals("")){
					if (!pay_moneyField.getText().equals("")){
						if (pay_pwdField.getText().equals(query_paycard.getCard_ps())) {
						if (Float.parseFloat(pay_moneyField.getText()) < 100000) {
							if (query_paycard.getCurrent() > Double.parseDouble(pay_moneyField.getText())) {
							//��ѯ�ɷѿ����Ƿ����
							query_paycard = asi.tra_bkd_query(pay_cardField.getText());
							query_paycard_serial1 = asi.query_paycard_serial(pay_cardField.getText());
							//������е���������Ҫ�ɷѵ���� ������һ���ж�
							if (query_paycard.getCurrent() > Double.parseDouble(pay_moneyField.getText())) {
								DecimalFormat df = new DecimalFormat("0.00 ");
								//ִ�нɷ�ҵ��ۿ�
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺽�ͨ�ɷѣ������ˣ�ҵ��Ա9"+"\n";
								asi.paybill_trade(query_paycard_serial1.getCard_id(),Double.parseDouble(pay_moneyField.getText()));
								try {
									asi.addConsumtion_traffic(query_paycard_serial1.getCard_serial(),Double.parseDouble(pay_moneyField.getText()));
								} catch (NumberFormatException e1) {
									e1.printStackTrace();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								string=string+"�ɷѳɹ���" + "\n" + "ʣ�����Ϊ��" + bankcard_query_cardid.getCurrent()+"\n";
									setVisible(false);
									PaymentFrame frame = new PaymentFrame(username,client_id);
									frame.setVisible(true);
										bankcard_query_cardid = asi.tra_bkd_query(pay_cardField.getText());
									JOptionPane.showMessageDialog(null, "�ɷѳɹ���" + "\n" + "ʣ�����Ϊ��" + bankcard_query_cardid.getCurrent());
														
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
								string=string+"���㣬���������룡" + "\n" + "��ǰ���Ϊ��" + bankcard_query_cardid.getCurrent()+"\n";
								JOptionPane.showMessageDialog(null,
										"���㣬���������룡" + "\n" + "��ǰ���Ϊ��" + bankcard_query_cardid.getCurrent());
							}
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
							string=string+"���Ŀ������㣬���������룡"+"\n";
							JOptionPane.showMessageDialog(null, "���Ŀ������㣬���������룡");
							pay_moneyField.setText("");
						}
						
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
							string=string+"�ɷѽ�����С��10000�����������룡"+"\n";
							JOptionPane.showMessageDialog(null, "�ɷѽ�����С��10000�����������룡");
							pay_moneyField.setText("");
						} 
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
							string=string+"֧�����벻��ȷ�����������룡"+"\n";
							JOptionPane.showMessageDialog(null, "֧�����벻��ȷ�����������룡");
							pay_moneyField.setText("");
						}
							
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
						string=string+"֧�����벻��ȷ�����������룡"+"\n";
						JOptionPane.showMessageDialog(null, "������ɷѽ��");
//						pay_moneyField.setText("");
					}
					} else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ�����̳ǹ�������ˣ��û�"+"\n";
						string=string+"�������������п���"+"\n";
						JOptionPane.showMessageDialog(null, "�������������п���");
						pay_cardField.setText("");
					}
				} else {
					return;
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
		
		JButton backButton_1 = new JButton("����");
		backButton_1.setFont(new Font("������", Font.BOLD, 18));
		backButton_1.setBounds(282, 270, 113, 27);
		contentPane.add(backButton_1);
		backButton_1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PaymentFrame frame = new PaymentFrame(username,client_id);
				frame.setVisible(true);			
			}		
		});
		
	
}
	
}
	

