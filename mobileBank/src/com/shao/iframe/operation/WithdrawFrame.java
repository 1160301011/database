package com.shao.iframe.operation;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.shao.model.*;
import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AtmFrame;


/**
 * @author HGX
 *表示层
 *取款界面 
 *
 */
public class WithdrawFrame extends JFrame {

	private JPanel contentPane;
	private JTextField outputField;
	private com.shao.model.User user_query;
	private com.shao.model.User user_modMoney;


	/**
	 * Create the frame.
	 */
	public WithdrawFrame(final String name,final String client_id) {
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setTitle(name);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabe = new JLabel("\u53D6\u6B3E\u91D1\u989D\uFF1A");
		lblNewLabe.setFont(new Font("新宋体", Font.BOLD, 20));
		lblNewLabe.setBounds(84, 75, 117, 38);
		contentPane.add(lblNewLabe);
		
		outputField = new JTextField();
		outputField.setBounds(199, 84, 117, 24);
		contentPane.add(outputField);
		outputField.setColumns(10);
		
		JButton OKButton = new JButton("\u786E\u5B9A");
		OKButton.setFont(new Font("新宋体", Font.BOLD, 15));
		OKButton.setBounds(84, 159, 113, 27);
		contentPane.add(OKButton);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=new String();
				if (Float.parseFloat(outputField.getText()) < 100000) {
					user_query = asi.query(name);
					if (user_query.getbalance() > Double.parseDouble(outputField.getText())) {
						double temp = user_query.getbalance() - Double.parseDouble(outputField.getText());
						DecimalFormat df = new DecimalFormat("0.00 ");
						asi.update(user_query.getName(), temp);
						setVisible(false);
						AtmFrame frame = new AtmFrame(name,null);
						frame.setVisible(true);
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：取款；责任人：业务员16"+"\n";
						string=string+"取钱交易成功！" + "\n" + "剩余余额为：" + df.format(temp)+"\n";
						JOptionPane.showMessageDialog(null, "取钱交易成功！" + "\n" + "剩余余额为：" + df.format(temp));

					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：取款；责任人：用户"+"\n";
						string=string+"余额不足，请重新输入！" + "\n" + "当前余额为：" + user_query.getbalance()+"\n";
						JOptionPane.showMessageDialog(null, "余额不足，请重新输入！" + "\n" + "当前余额为：" + user_query.getbalance());
						outputField.setText("");
					}
				} else {
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df1.format(new Date());
					string=string+"当前程序：取款；责任人：用户"+"\n";
					string=string+"输入金额大于100000，请重新输入！"+"\n";
					JOptionPane.showMessageDialog(null, "输入金额大于100000，请重新输入！");
					outputField.setText("");
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
		
		JButton backButton_1 = new JButton("\u8FD4\u56DE");
		backButton_1.setFont(new Font("新宋体", Font.BOLD, 15));
		backButton_1.setBounds(233, 159, 113, 27);
		contentPane.add(backButton_1);
		backButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(name,client_id);
				setVisible(false);
				af.setVisible(true);			
			}		
		});
		
		JLabel remindLabel = new JLabel("*\u5355\u7B14\u6700\u5927\u53EF\u53D610000");
		remindLabel.setForeground(Color.RED);
		remindLabel.setBounds(199, 126, 138, 18);
		contentPane.add(remindLabel);
	}

}
