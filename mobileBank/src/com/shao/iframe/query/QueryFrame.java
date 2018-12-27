package com.shao.iframe.query;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;
import com.shao.model.*;
/**
 * @author HGX
 *表示层
 *查询主界面 
 *
 */
public class QueryFrame extends JFrame{

	private JPanel contentPane;
	private Bankuser bu;
	private Clientinfo clientinfo;
	
	private static String client_id;
	private static String username;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					QueryFrame frame = new QueryFrame(username,client_id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public QueryFrame(final String username,final String client_id) {
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 650);
		contentPane = new JPanel();
		setTitle(username+": 查询业务");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton mycardBtn = new JButton("我的银行卡");
		mycardBtn.setFont(new Font("新宋体", Font.BOLD, 20));
		mycardBtn.setBounds(95, 100, 140, 27);
		contentPane.add(mycardBtn);
		mycardBtn.addActionListener(new ActionListener(){
			
				public void actionPerformed(ActionEvent e) {
					QuerymcdFrame qf = new QuerymcdFrame(username,client_id);
					setVisible(false);
					qf.setVisible(true);
				}
			});
		
		JButton query_wit_Btn = new JButton("查询取款记录");
		query_wit_Btn.setFont(new Font("新宋体", Font.BOLD, 20));
		query_wit_Btn.setMargin(new Insets(0,0,0,0));
		query_wit_Btn.setBounds(85, 170, 160, 27);
		contentPane.add(query_wit_Btn);
		query_wit_Btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					QuerywitFrame qwf = new QuerywitFrame(username,client_id);
					setVisible(false);
					qwf.setVisible(true);
				}
			});
		
		JButton query_tra_Btn = new JButton("查询转账记录");
		query_tra_Btn.setFont(new Font("新宋体", Font.BOLD, 20));
		query_tra_Btn.setMargin(new Insets(0,0,0,0));
		query_tra_Btn.setBounds(85, 240, 160, 27);
		contentPane.add(query_tra_Btn);
		query_tra_Btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String string=new String();
					string=string+"当前程序：查询；责任人：业务员18"+"\n";
					string=string+"查询转账记录"+"\n";
					QuerytraFrame qtf = new QuerytraFrame(username,client_id);
					setVisible(false);
					qtf.setVisible(true);
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
		
		JButton queryButton = new JButton("查询余额");
		queryButton.setFont(new Font("新宋体", Font.BOLD, 20));
		queryButton.setBounds(90, 310, 150, 27);
		contentPane.add(queryButton);
		queryButton.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			try {
				bu = asi.bu_query(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}    
			string=string+"当前程序：查询；责任人：业务员18"+"\n";
			string=string+"查询余额"+"\n";
			DecimalFormat df = new DecimalFormat( "0.00 ");   //数字格式化 ，取一位整数和两位小数  
			JOptionPane.showMessageDialog(null,   //居中位于屏幕上
		              "您的账户名:"+bu.getUsername()+"\n"+"当前余额:￥"+bu.getUserbalance()+"\n","信息提示",
		              JOptionPane.INFORMATION_MESSAGE);	
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
		

		
		JButton queryClientButton = new JButton("查看个人信息");
		queryClientButton.setFont(new Font("新宋体", Font.BOLD, 20));
		queryClientButton.setMargin(new Insets(0,0,0,0));
		queryClientButton.setBounds(88, 380, 160, 27);
		contentPane.add(queryClientButton);
		queryClientButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			clientinfo=asi.cquery(client_id);
//			DecimalFormat df = new DecimalFormat( "0.00 ");   //数字格式化 ，取一位整数和两位小数  
			String string=new String();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			string=string+df1.format(new Date());
			string=string+"当前程序：查询；责任人：业务员18"+"\n";
			string=string+"查看个人信息"+"\n";
			JOptionPane.showMessageDialog(null,   //居中位于屏幕上
		              "您的客户ID:"+clientinfo.getClient_id()+"\n"+"您的账户ID:"+clientinfo.getUid()+"\n"+"您的客户名:"+clientinfo.getClient_name()+"\n"
		              +"您的身份证:"+clientinfo.getClient_idcard()+"\n"+"您的手机号:"+clientinfo.getClient_phone(),"信息提示",
		              JOptionPane.INFORMATION_MESSAGE);		
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
		
		//查询信用卡账单 及 还款功能
		JButton queryCreBill = new JButton("查看信用卡账单");
		queryCreBill.setFont(new Font("新宋体", Font.BOLD, 20));
		queryCreBill.setMargin(new Insets(0,0,0,0));
		queryCreBill.setBounds(85, 450, 170, 27);
		contentPane.add(queryCreBill);
		queryCreBill.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			string=string+df1.format(new Date());
			string=string+"当前程序：查询；责任人：业务员18"+"\n";
			string=string+"查看信用卡账单"+"\n";
			QueryCreBillInfo qcb = new QueryCreBillInfo(username,client_id);
			setVisible(false);
			qcb.setVisible(true);
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
		
		JButton queryPyeBtn = new JButton("查看收款名单");
		queryPyeBtn.setFont(new Font("新宋体", Font.BOLD, 20));
		queryPyeBtn.setMargin(new Insets(0,0,0,0));
		queryPyeBtn.setBounds(85, 520, 170, 27);
		contentPane.add(queryPyeBtn);
		queryPyeBtn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			string=string+df1.format(new Date());
			string=string+"当前程序：查询；责任人：业务员18"+"\n";
			string=string+"查看收款名单"+"\n";
			QryPyeFrame qpf = new QryPyeFrame(username,client_id);
			setVisible(false);
			qpf.setVisible(true);
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
		
		
		
		
		
		JButton rollback = new JButton("返回");
		rollback.setFont(new Font("新宋体", Font.BOLD, 15));
		rollback.setBounds(120, 574, 113, 27);
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
