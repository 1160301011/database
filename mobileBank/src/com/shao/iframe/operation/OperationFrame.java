package com.shao.iframe.operation;
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
import com.shao.model.User;
import com.shao.model.Clientinfo;
import com.shao.model.Bankuser;

/**
 * @author HGX
 *表示层
 *业务操作主界面 
 *
 */
public class OperationFrame extends JFrame {
	
	private JPanel contentPane;
	private Bankuser bu;
	private Clientinfo clientinfo;
	/**
	 * @param username
	 * @param client_id
	 */
	public OperationFrame(final String username,final String client_id) {
		
		final AccountServiceImpl asi =new AccountServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 434);
		contentPane = new JPanel();
		setTitle(username+"：存取业务");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * @param username
		 * @param client_id
		 * 存款操作
		 */
		JButton depositButton = new JButton("存款");
		depositButton.setFont(new Font("新宋体", Font.BOLD, 20));
		depositButton.setBounds(100, 109, 113, 27);
		contentPane.add(depositButton);
		depositButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DepositFrame df = new DepositFrame(username,client_id);
				setVisible(false);
				df.setVisible(true);
			}
			
		});
		
		JButton withdrawButton = new JButton("取款");
		withdrawButton.setFont(new Font("新宋体", Font.BOLD, 20));
		withdrawButton.setBounds(288, 109, 113, 27);
		contentPane.add(withdrawButton);
		withdrawButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CompulateInterest cli = new CompulateInterest(username,client_id);
				setVisible(false);
				cli.setVisible(true);
			}
			
		});
		
		JButton tfBtn_in = new JButton("同行转账");
		tfBtn_in.setFont(new Font("新宋体", Font.BOLD, 20));
		tfBtn_in.setBounds(278, 209, 135, 27);
		contentPane.add(tfBtn_in);
		tfBtn_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TransferFrame tf = new TransferFrame(username,client_id);
				setVisible(false);
				tf.setVisible(true);
			}
		});
		
		JButton tfBtn_out = new JButton("跨行转账");
		tfBtn_out.setFont(new Font("新宋体", Font.BOLD, 20));
		tfBtn_out.setBounds(90, 209, 135, 27);
		contentPane.add(tfBtn_out);
		tfBtn_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TfoutFrame tfo = new TfoutFrame(username,client_id);
				setVisible(false);
				tfo.setVisible(true);
			}
		});
		
		
		//返回按钮
		JButton rollback = new JButton("返回");
		rollback.setFont(new Font("新宋体", Font.BOLD, 15));
		rollback.setBounds(200, 309, 113, 27);
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
