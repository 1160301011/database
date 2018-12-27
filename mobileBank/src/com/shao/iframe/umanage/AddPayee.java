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


/**添加客户的收款人
 * @author Administrator
 *
 */

public class AddPayee extends JFrame{

	private JPanel contentPane;
	private JTextField payee_id;  //收款人ID输入框
	private JTextField payee_uid;		//账户ID输入框
	private JTextField payee_name;		//收款人姓名输入框
	private JTextField payee_card;		//收款人卡号输入框
	private JTextField belong_bank;		//卡号所属银行输入框
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
		
//		JLabel lblNewLabel = new JLabel("收款人ID ：");
//		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
//		lblNewLabel.setBounds(105, 72, 126, 30);
//		contentPane.add(lblNewLabel);
//		
//		payee_id = new JTextField();		
//		payee_id.setBounds(235, 77, 152, 20);
//		contentPane.add(payee_id);
//		payee_id.setColumns(10);
		
//		JLabel lblNewLabel2 = new JLabel("账 户 ID ：");
//		lblNewLabel2.setFont(new Font("新宋体", Font.BOLD, 20));
//		lblNewLabel2.setBounds(105, 112, 126, 30);
//		contentPane.add(lblNewLabel2);
//		
//		payee_uid = new JTextField();			//账户ID输入框
//		payee_uid.setBounds(235, 117, 152, 20);
//		contentPane.add(payee_uid);
//		payee_uid.setColumns(10);
//		
		
		JLabel lblNewLabel3 = new JLabel("收款姓名 :");
		lblNewLabel3.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel3.setBounds(110, 100, 126, 30);
		contentPane.add(lblNewLabel3);
		
		payee_name = new JTextField();			//客户姓名输入框
		payee_name.setBounds(225, 105, 152, 20);
		contentPane.add(payee_name);
		payee_name.setColumns(8);
		
		JLabel lblNewLabel4 = new JLabel("收款卡号 :");
		lblNewLabel4.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel4.setBounds(110, 170, 126, 30);
		contentPane.add(lblNewLabel4);
		
		payee_card = new JTextField();			//收款卡号输入框
		payee_card.setBounds(225, 175, 152, 20);
		contentPane.add(payee_card);
		payee_card.setColumns(19);
		
		JLabel lblNewLabel5 = new JLabel("所属银行 :");
		lblNewLabel5.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabel5.setBounds(110, 240, 126, 30);
		contentPane.add(lblNewLabel5);
		
		belong_bank = new JTextField();			//所属银行输入框
		belong_bank.setBounds(225, 245, 152, 20);
		contentPane.add(belong_bank);
		belong_bank.setColumns(15);
		

	JButton backButton_1 = new JButton("返回");
	backButton_1.setFont(new Font("新宋体", Font.BOLD, 18));
	backButton_1.setBounds(282, 295, 113, 27);
	contentPane.add(backButton_1);
	backButton_1.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			AtmFrame af = new AtmFrame(name,client_id);
			setVisible(false);
			af.setVisible(true);			
		}		
	});
			JButton editButton = new JButton("添加");
		editButton.setFont(new Font("新宋体", Font.BOLD, 18));
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
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：添加收款人；责任人：业务员20"+"\n";
								asi.addpayee(bu.getUid(),payee_name.getText(),payee_card.getText(),belong_bank.getText());
								setVisible(true);
//								atmFrame frame = new atmFrame(name);
//								user_query = asi.query(name);
//								frame.setVisible(true);	
								string=string+"添加成功！";
								JOptionPane.showMessageDialog(null, "添加成功！");
							} else {
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：添加收款人；责任人：用户"+"\n";
								string=string+"收款ID已经存在！";
								JOptionPane.showMessageDialog(null, "收款ID已经存在！");
							}
//							}else{
//								JOptionPane.showMessageDialog(null, "账户ID已经存在！");
//							}
							}else{
								SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								string=string+df1.format(new Date());
								string=string+"当前程序：添加收款人；责任人：用户"+"\n";
								string=string+"未输入所属银行！";
								JOptionPane.showMessageDialog(null, "未输入所属银行！");
							}
						} else {
							SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df1.format(new Date());
							string=string+"当前程序：添加收款人；责任人：用户"+"\n";
							string=string+"未输入收款人卡号！";
							JOptionPane.showMessageDialog(null, "未输入收款人卡号！");
						}

					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：添加收款人；责任人：用户"+"\n";
						string=string+"收款入姓名！";
						JOptionPane.showMessageDialog(null, "收款入姓名！");
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
//					JOptionPane.showMessageDialog(null, "未输入账户ID！");
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "未输入收款人ID！");
//			}
		}
	
	}
}
