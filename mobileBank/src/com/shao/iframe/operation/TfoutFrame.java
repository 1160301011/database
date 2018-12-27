package com.shao.iframe.operation;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.shao.model.*;
import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
/**
 * @author HGX
 *��ʾ��
 *����ת�˽��� 
 *
 */
public class TfoutFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField toname_Field;
	private JTextField out_moneyField;
	private JTextField tocard_Field;
	private JTextField from_cardField;
	//private com.shao.model.user user;
	private com.shao.model.Clientinfo cif;
	private com.shao.model.Bankcard bkd_cif;
	private com.shao.model.Bankcard bankcard_query_from;
	private com.shao.model.Payee pye;
	private com.shao.model.Bankcard bankcard_query_cardid;
	private com.shao.model.Bankcard bkd_query_card_serial;
	private com.shao.model.Bankuser bu;
	public String bankname ="δ֪";
	public TfoutFrame(final String username,final String client_id) {
	    
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 400);
		setTitle(username);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�Է�������");
		lblNewLabel.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel.setBounds(115, 72, 126, 30);
		contentPane.add(lblNewLabel);
		
		toname_Field = new JTextField();
		toname_Field.setBounds(245, 77, 150, 24);
		contentPane.add(toname_Field);
		toname_Field.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("�Է����ţ�");
		lblNewLabel2.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel2.setBounds(115, 141, 126, 30);
		contentPane.add(lblNewLabel2);
		
		tocard_Field = new JTextField();
		tocard_Field.setBounds(245, 141, 150, 24);
		contentPane.add(tocard_Field);
		tocard_Field.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("����ţ�");
		lblNewLabel_1.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel_1.setBounds(115, 210, 112, 30);
		contentPane.add(lblNewLabel_1);
		
		from_cardField = new JTextField();
		from_cardField.setBounds(243, 215, 152, 24);
		contentPane.add(from_cardField);
		from_cardField.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("ת�˽�");
		lblNewLabel_2.setFont(new Font("������", Font.BOLD, 20));
		lblNewLabel_2.setBounds(115, 280, 112, 30);
		contentPane.add(lblNewLabel_2);
		
		out_moneyField = new JTextField();
		out_moneyField.setBounds(243, 285, 152, 24);
		contentPane.add(out_moneyField);
		out_moneyField.setColumns(10);
		
		
		JButton OKButton = new JButton("ȷ��");
		OKButton.setFont(new Font("������", Font.BOLD, 17));
		OKButton.setBounds(104, 320, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				int res = JOptionPane.showConfirmDialog(null, "ȷ�ϴ˴�ת�ˣ�", "ת��ȷ��", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					bkd_cif = asi.bkd_cif_query(tocard_Field.getText());
					//��ѯ�ÿͻ���Ϣ 
					cif = asi.cquery(bkd_cif.getClient_id());
					//��ѯ�տ��������޴˿��ŵ���Ϣ
					pye = asi.pye_query(tocard_Field.getText());
					//��ѯ�˻���Ϣ
					try {
						bu = asi.bu_query(username);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
				if(!tocard_Field.getText().equals("")){
					if (tocard_Field.getText().equals(bkd_cif.getCard_id())) {
						if(!toname_Field.getText().equals("")){
							if(toname_Field.getText().equals(cif.getClient_name())){
								if(!from_cardField.getText().equals("")){
						if (Float.parseFloat(out_moneyField.getText()) < 100000) {
							String str = JOptionPane.showInputDialog("������֧�����룺");
							//��ѯת�˿����Ƿ����
								bankcard_query_from = asi.tra_bkd_query(from_cardField.getText());
								//��ѯת�˿��ŵ����к�
								bkd_query_card_serial = asi.query_card_serial(from_cardField.getText());
								if (str.equals(bankcard_query_from.getCard_ps())){
							if (bankcard_query_from.getCurrent() > Double.parseDouble(out_moneyField.getText())) {
								DecimalFormat df = new DecimalFormat("0.00 ");
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺿���ת�˽��ף������ˣ�ҵ��Ա14"+"\n";
								//������Ϣ����
								double fee = Double.parseDouble(out_moneyField.getText())*0.001;
								double temp = Double.parseDouble(out_moneyField.getText())+Double.parseDouble(out_moneyField.getText())*0.001;
								asi.bkdtransfer_out(from_cardField.getText(), tocard_Field.getText(), Double.parseDouble(out_moneyField.getText()),temp);
								try {
									asi.addtra_out_record(bkd_query_card_serial.getCard_serial(),client_id,tocard_Field.getText(),df.format(temp),fee);
								} catch (ClassNotFoundException e1) {
									string=string+e1.toString()+"\n";
									e1.printStackTrace();
								}
								if(pye == null){
									asi.addpayee(bu.getUid(), toname_Field.getText(), tocard_Field.getText(),bankname);
								}else{
									System.out.println("�տ����Ѵ���");
								}
								OperationFrame opf = new OperationFrame(username,client_id);
								setVisible(false);
								opf.setVisible(true);	
//									try {
										bankcard_query_cardid = asi.tra_bkd_query(from_cardField.getText());
//									} catch (SQLException e1) {
//										e1.printStackTrace();
//									}
									string=string+"ת�˽��׳ɹ���" + "\n" + "ʣ�����Ϊ��" + bankcard_query_cardid.getCurrent()+"\n"+"������Ϊ:"+df.format(fee)+"\n";
									JOptionPane.showMessageDialog(null, "ת�˽��׳ɹ���" + "\n" + "ʣ�����Ϊ��" + bankcard_query_cardid.getCurrent()+"\n"+"������Ϊ:"+df.format(fee));
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
								string=string+"���㣬���������룡" + "\n" + "��ǰ���Ϊ��" + bankcard_query_from.getCurrent()+"\n";
								JOptionPane.showMessageDialog(null,
										"���㣬���������룡" + "\n" + "��ǰ���Ϊ��" + bankcard_query_from.getCurrent());
								out_moneyField.setText("");
								}
							}else{
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
								string=string+"֧�����벻��ȷ��"+"\n";
								JOptionPane.showMessageDialog(null, "֧�����벻��ȷ��");
						}
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
							string=string+"ת�˽�����С��10000�����������룡"+"\n";
							JOptionPane.showMessageDialog(null, "ת�˽�����С��10000�����������룡");
							out_moneyField.setText("");
						}
						
								}else{
									SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
									string=string+df1.format(new Date());
									string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
									string=string+"�����븶��ţ�"+"\n";
									JOptionPane.showMessageDialog(null, "�����븶��ţ�");
								}
							}else{
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
								string=string+"�����뿨�Ų�����"+"\n";
								JOptionPane.showMessageDialog(null, "�����뿨�Ų�����");
								toname_Field.setText("");
							}
						}else{
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
							string=string+"������Է�������"+"\n";
							JOptionPane.showMessageDialog(null, "������Է�������");
						}
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
						string=string+"����������п������ڣ�"+"\n";
						JOptionPane.showMessageDialog(null, "����������п������ڣ�");
						tocard_Field.setText("");
					}
					} else{
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ���򣺿���ת�˽��ף������ˣ��û�"+"\n";
						string=string+"���������п��ţ�"+"\n";
						JOptionPane.showMessageDialog(null, "���������п��ţ�");
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
		backButton_1.setBounds(282, 320, 113, 27);
		contentPane.add(backButton_1);
		backButton_1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				OperationFrame opf = new OperationFrame(username,client_id);
				setVisible(false);
				opf.setVisible(true);			
			}		
		});
	
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
