package com.shao.iframe.umanage;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
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
import com.shao.iframe.LoginFrame;
import com.shao.iframe.AtmFrame;
//import com.shao.iframe.SignFrame.OKButtonAction;
import com.shao.model.Bankuser;
import com.shao.model.User;
/**
 * @author HGX
 *��ʾ��
 *�޸��û�������� 
 *
 */

public class ModifpwdFrame extends JFrame{

	private JPanel contentPane;				
	private JTextField bank_uname;
	private JPasswordField oldpwdField;
	private JPasswordField newpwdField;
	private Bankuser bu;
	private User user;
	
	public ModifpwdFrame(final String username, final String client_id) {
		final AccountServiceImpl asi =new AccountServiceImpl();
		setTitle(username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblbankname = new JLabel("�û��� :");
		lblbankname.setFont(new Font("������", Font.BOLD, 18));
		lblbankname.setBounds(74, 78, 102, 34);
		contentPane.add(lblbankname);
		
		JLabel lbloldpwd = new JLabel("������ :");
		lbloldpwd.setFont(new Font("������", Font.BOLD, 18));
		lbloldpwd.setBounds(74, 149, 88, 28);
		contentPane.add(lbloldpwd);
		
		JLabel lblnewpwd = new JLabel("������ :");
		lblnewpwd.setFont(new Font("������", Font.BOLD, 18));
		lblnewpwd.setBounds(74, 211, 101, 28);
		contentPane.add(lblnewpwd);
		
		bank_uname = new JTextField();
		bank_uname.setBounds(187, 85, 123, 24);
		contentPane.add(bank_uname);
		bank_uname.setColumns(10);
		
		oldpwdField = new JPasswordField();
		oldpwdField.setBounds(187, 153, 123, 24);
		contentPane.add(oldpwdField);
		
		newpwdField = new JPasswordField();
		newpwdField.setBounds(187, 215, 123, 24);
		contentPane.add(newpwdField);
		
		JButton modifyButton = new JButton("�޸�");  //ע�ᰴť����
		modifyButton.setFont(new Font("������", Font.BOLD, 20));
		modifyButton.setBounds(100, 311, 113, 27);
		contentPane.add(modifyButton);
		modifyButton.addActionListener(new mdfButtonAction());
		
		JButton rollback = new JButton("����");
		rollback.setFont(new Font("������", Font.BOLD, 20));
		rollback.setBounds(259, 311, 113, 27);
		contentPane.add(rollback);
		rollback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(username,client_id);
				setVisible(false);
				af.setVisible(true);			
			}		
		});
		
	}
	
class mdfButtonAction implements ActionListener {
		
		AccountServiceImpl asi =new AccountServiceImpl();
	
		public void actionPerformed(ActionEvent e) {
			String string=new String();
//			  user = asi.query(bank_uname.getText());
			try {
				bu =asi.bu_query(bank_uname.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 if (!bank_uname.getText().equals("")) {
						if (!oldpwdField.getText().equals("")) {
							if (!newpwdField.getText().equals("")) {
									if (bu == null) { 
										JOptionPane.showMessageDialog(null, "�û��������ڣ�");
									} else {
										try {
											SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
											string=string+df1.format(new Date());
											string=string+"��ǰ�����޸��û����룻�����ˣ�ҵ��Ա21"+"\n";
											asi.updateUser(bank_uname.getText(),newpwdField.getText());
											setVisible(false);
											LoginFrame frame = new LoginFrame();
											frame.setVisible(true);
											string=string+"�޸ĳɹ�,�����µ�¼��"+"\n";
											JOptionPane.showMessageDialog(null, "�޸ĳɹ�,�����µ�¼��");
										} catch (SQLException e2) {
											string=string+e2.toString()+"\n";
											e2.printStackTrace();
										}
									}
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
								string=string+df1.format(new Date());
								string=string+"��ǰ�����޸��û����룻�����ˣ��û�"+"\n";
								string=string+"δ���������룡";
								JOptionPane.showMessageDialog(null, "δ���������룡");
							}

						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							string=string+df1.format(new Date());
							string=string+"��ǰ�����޸��û����룻�����ˣ��û�"+"\n";
							string=string+"δ��������룡";
							JOptionPane.showMessageDialog(null, "δ��������룡");
						}
					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						string=string+df1.format(new Date());
						string=string+"��ǰ�����޸��û����룻�����ˣ��û�"+"\n";
						string=string+"δ�����û�����";
						JOptionPane.showMessageDialog(null, "δ�����û�����");
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
}


}


