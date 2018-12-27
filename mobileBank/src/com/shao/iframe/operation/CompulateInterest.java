package com.shao.iframe.operation;
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
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.shao.Service.impl.AccountServiceImpl;
import com.shao.iframe.AdminFrame;
import com.shao.iframe.AtmFrame;
import com.shao.iframe.LoginFrame;

/**
 * @author HGX
 *表示层
 *取款利息计算界面 
 *
 */


public class CompulateInterest extends JFrame  implements ActionListener, HyperlinkListener{

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_2;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_cardid;
    private JEditorPane pane;
    private JPanel panel;
    private JLabel lblNewLabel; //小标题标签
    private JPanel panel_2;
    private JButton btnNewButton;
    private JButton btnWithdraw;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_8;
    private JLabel lblNewLabel_1;  //存款金额标签
    private JPanel panel_9;
    private JLabel lblNewLabel_2; //到期利息标签
    private JPanel panel_5;
    private JPanel panel_10;
    private JLabel lblNewLabel_3;  //存款时间标签
    private JButton bu;
    private JComboBox comboBox;
    private String username;
    private String client_id;
    private com.shao.model.Bankcard card_query;
    private com.shao.model.Bankuser user_query;
//    public static void main(String[] args) {
//    
//                    compulateInterest frame = new compulateInterest();
//        
//    
//    }
    public CompulateInterest(final String username,final String client_id) {
    	
    	final AccountServiceImpl asi =new AccountServiceImpl();
    	this.client_id = client_id;
    	this.username = username;
    	MenuBar();
		
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// 设置pane的超级链接监听
		pane.addHyperlinkListener(this);
		this.add(new JScrollPane(pane), "Center");
    	
        setTitle("尊敬的"+username+ ":  欢迎进入"+"取款业务");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 3, 0));
        
        panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        lblNewLabel = new JLabel("存款利息计算");
        panel.add(lblNewLabel, BorderLayout.NORTH);
        
        panel_2 = new JPanel();						//计算取出 按钮 面板
        panel.add(panel_2, BorderLayout.SOUTH);
        
        btnNewButton = new JButton("计算");
        
        btnWithdraw = new JButton("取出");
        
        panel_2.add(btnWithdraw); 
        panel_2.add(btnNewButton);
        
        panel_3 = new JPanel();
        panel.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new GridLayout(0, 1, 4, 0));
        
        panel_4 = new JPanel();
        panel_3.add(panel_4);
        panel_4.setLayout(new GridLayout(1, 0, 0, 0));
        
        panel_8 = new JPanel();
        panel_4.add(panel_8);
        
        lblNewLabel_1 = new JLabel("存款金额");
        panel_8.add(lblNewLabel_1);
        
        textField = new JTextField();
        panel_8.add(textField);
        textField.setColumns(10);    //存款金额输入框		--取款金额
        
        JLabel lblNewLabel_16 = new JLabel("元");
        panel_8.add(lblNewLabel_16);
        
        JLabel label_3 = new JLabel();
        panel_8.add(label_3);
        
        panel_9 = new JPanel();
        panel_4.add(panel_9);
        
        lblNewLabel_2 = new JLabel("到期利息");
        panel_9.add(lblNewLabel_2);
        
        textField_1 = new JTextField();
        panel_9.add(textField_1);				//"到期利息"		--利息
        textField_1.setColumns(10);
        
        panel_5 = new JPanel();
        panel_3.add(panel_5);
        panel_5.setLayout(new GridLayout(1, 0, 0, 0));
        
        panel_10 = new JPanel();
        panel_5.add(panel_10);
        
        lblNewLabel_3 = new JLabel("存款时间");
        panel_10.add(lblNewLabel_3);
        
        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","5"}));
        panel_10.add(comboBox);
        
        JLabel label = new JLabel("年");
        panel_10.add(label);
        
        JPanel panel_11 = new JPanel();
        panel_5.add(panel_11);
        
        JLabel lblNewLabel_4 = new JLabel("到期本息");
        panel_11.add(lblNewLabel_4);
        
        textField_3 = new JTextField();
        panel_11.add(textField_3);
        textField_3.setColumns(10);			//"到期本息"输入框	--应得金额
        			
        												
        JPanel panel_6 = new JPanel();
        panel_3.add(panel_6);
        panel_6.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_12 = new JPanel();
        panel_6.add(panel_12);
        
        JLabel lblNewLabel_5 = new JLabel("存款利率");
        panel_12.add(lblNewLabel_5);			
        
        textField_4 = new JTextField();
        panel_12.add(textField_4);
        textField_4.setColumns(10);
        
        JLabel label_1 = new JLabel("%");
        panel_12.add(label_1);
        
        JPanel panel_13 = new JPanel();
        panel_6.add(panel_13);
        
        JLabel lblNewLabel_6 = new JLabel("到期时间");
        panel_13.add(lblNewLabel_6);
        
        textField_5 = new JTextField();
        panel_13.add(textField_5);
        textField_5.setColumns(10);
        
        
        JPanel panel_7 = new JPanel();
        panel_3.add(panel_7);
        panel_7.setLayout(new GridLayout(1, 2, 0, 0));
        
        JPanel panel_14 = new JPanel();
        panel_7.add(panel_14);
        
        JLabel lblNewLabel_7 = new JLabel("起始时间");
        panel_14.add(lblNewLabel_7);
        
        textField_6 = new JTextField();
        panel_14.add(textField_6);
        textField_6.setColumns(10);
        
        
        JPanel panel_141 = new JPanel();
        panel_7.add(panel_141);
        
        JLabel lblNewLabel_71 = new JLabel("银行卡号");
        panel_141.add(lblNewLabel_71);
        
        textField_cardid = new JTextField();
        panel_141.add(textField_cardid);
        textField_cardid.setColumns(10);
        

        //贷款区域
        JPanel panel_15 = new JPanel();
        panel_7.add(panel_15);
        
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        
        JLabel lblNewLabel_8 = new JLabel("存款利息计算");
        panel_1.add(lblNewLabel_8, BorderLayout.NORTH);
        
        JPanel panel_16 = new JPanel();
        panel_1.add(panel_16, BorderLayout.SOUTH);
        
         bu = new JButton("计算");
        
        panel_16.add(bu);
//        bu.addActionListener(this);
        
        JPanel panel_17 = new JPanel();
        panel_1.add(panel_17, BorderLayout.CENTER);
        panel_17.setLayout(new GridLayout(0, 1, 4, 0));
        
        JPanel panel_18 = new JPanel();
        panel_17.add(panel_18);
        panel_18.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_22 = new JPanel();
        panel_18.add(panel_22);
        
        JLabel lblNewLabel_9 = new JLabel("贷款金额");
        panel_22.add(lblNewLabel_9);
        
        textField_2 = new JTextField();  //贷款金额输入框
        panel_22.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("元");
        panel_22.add(lblNewLabel_17);
        
        JPanel panel_23 = new JPanel();
        panel_18.add(panel_23);
        
        JLabel lblNewLabel_10 = new JLabel("应还利息");
        panel_23.add(lblNewLabel_10);
        
        textField_7 = new JTextField();
        panel_23.add(textField_7);
        textField_7.setColumns(10);
        
        JPanel panel_19 = new JPanel();
        panel_17.add(panel_19);
        panel_19.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_26 = new JPanel();
        panel_19.add(panel_26);
        
        JLabel lblNewLabel_11 = new JLabel("贷款时间");
        panel_26.add(lblNewLabel_11);
        
        textField_8 = new JTextField();
        panel_26.add(textField_8);
        textField_8.setColumns(10);
        
        JLabel label_2 = new JLabel("年");
        panel_26.add(label_2);
        
        JPanel panel_27 = new JPanel();
        panel_19.add(panel_27);
        
        JLabel lblNewLabel_12 = new JLabel("应还本息");
        panel_27.add(lblNewLabel_12);
        
        textField_9 = new JTextField();
        panel_27.add(textField_9);
        textField_9.setColumns(10);
        
        JPanel panel_20 = new JPanel();
        panel_17.add(panel_20);
        panel_20.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_24 = new JPanel();
        panel_20.add(panel_24);
        
        JLabel lblNewLabel_13 = new JLabel("贷款利率");
        panel_24.add(lblNewLabel_13);
        
       
        textField_10 = new JTextField();
        panel_24.add(textField_10);
        textField_10.setColumns(10);
        
         JLabel label_31 = new JLabel("%");
        panel_24.add(label_31);
        
        
        JPanel panel_25 = new JPanel();
        panel_20.add(panel_25);
        
        JLabel lblNewLabel_14 = new JLabel("还款时间");
        panel_25.add(lblNewLabel_14);
        
        textField_11 = new JTextField();
        panel_25.add(textField_11);
        textField_11.setColumns(10);
        
        JPanel panel_21 = new JPanel();
        panel_17.add(panel_21);
        panel_21.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_28 = new JPanel();
        panel_21.add(panel_28);
        
        JLabel lblNewLabel_15 = new JLabel("贷款日期");
        panel_28.add(lblNewLabel_15);
        
        textField_12 = new JTextField();
        panel_28.add(textField_12);
        textField_12.setColumns(10);
        
        JPanel panel_29 = new JPanel();
        panel_21.add(panel_29);
        setVisible(true);
        
        btnWithdraw.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		card_query = asi.card_query(textField_cardid.getText());
        		String string=new String();
        		if (Float.parseFloat(textField.getText()) < 100000) {
        			card_query = asi.card_query(textField_cardid.getText());
        			try {
						user_query = asi.bu_query(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
        			//查询输入的卡号是否属于自己的账户
        			if(card_query.getUid().equals(user_query.getUid())){
					if (card_query.getDeath() >= Double.parseDouble(textField.getText())) {
						double temp = card_query.getDeath() - Double.parseDouble(textField.getText());
						double temp2 = user_query.getUserbalance() + Double.parseDouble(textField_3.getText());
						DecimalFormat df = new DecimalFormat("0.00 ");
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：取款交易；责任人：业务员12"+"\n";
						//账户余额更新
						asi.update_bankuser_balance(username, temp2);
						//所取款的银行卡 死期余额更新
						asi.update_card_death(card_query.getCard_serial(), temp);
						//添加取款表单
						try {
							asi.insert_WITHDRAWAL(card_query.getCard_serial(),textField.getText(),textField_1.getText(),textField_3.getText(),temp);
						} catch (ClassNotFoundException e1) {
							string=string+e1.toString()+"\n";
							e1.printStackTrace();
						}
						setVisible(false);
						OperationFrame frame = new OperationFrame(username,client_id);
						frame.setVisible(true);
						SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df2.format(new Date());
						string=string+"取钱交易成功！" + "\n" +"卡号："+textField_cardid.getText()+"\n"+ "剩余余额为：" + df.format(temp)+"\n"+"您的钱包余额为: "+ df.format(temp2)+"\n";
						JOptionPane.showMessageDialog(null, "取钱交易成功！" + "\n" +"卡号："+textField_cardid.getText()+"\n"+ "剩余余额为：" + df.format(temp)+"\n"+"您的钱包余额为: "+ df.format(temp2));

					} else {
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df1.format(new Date());
						string=string+"当前程序：取款交易；责任人：用户"+"\n";
						string=string+"余额不足，请重新输入！" + "\n" + "当前余额为：" + card_query.getDeath()+"\n";
						JOptionPane.showMessageDialog(null, "余额不足，请重新输入！" + "\n" + "当前余额为：" + card_query.getDeath());
						textField.setText("");
					}
        			}else{
        				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    					string=string+df1.format(new Date());
        				string=string+"当前程序：取款交易；责任人：用户"+"\n";
						string=string+"请输入属于您的卡号！"+"\n";
        				JOptionPane.showMessageDialog(null, "请输入属于您的卡号！");
        			}
        			
				} else {
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df1.format(new Date());
					string=string+"当前程序：取款交易；责任人：用户"+"\n";
					string=string+"输入金额大于100000，请重新输入！"+"\n";
					JOptionPane.showMessageDialog(null, "输入金额大于100000，请重新输入！");
					textField.setText("");
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
        
         bu.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     Double i1 = new Double(textField_2.getText());
                    
                    Double i4 = new Double(textField_8.getText());  //贷款时间
                    
                        Double i2 = new Double(textField_10.getText());
                    
                        Double i3 = new Double(textField_12.getText()); //贷款日期
                    
                    textField_7.setText(i1*i4*i2*0.01+"");		//应还利息

                        textField_9.setText(i1*i4*i2*0.01+i1+""); //应还本息
                        textField_11.setText(i4+i3+"");			//还款时间
                 
                 }
             });
         
    btnNewButton.addActionListener(new ActionListener() {
        
            
            public void actionPerformed(ActionEvent e) {
                
                 Double i1 = new Double(textField.getText());
                
                Double i4 = new Double(comboBox.getSelectedItem().toString());
                
                    Double i2 = new Double(textField_4.getText());
                
                    Double i3 = new Double(textField_6.getText());
                
                    textField_1.setText(i1*i4*i2*0.01+"");

                    textField_3.setText(i1*i4*i2*0.01+i1+"");
                    textField_5.setText(i4+i3+"");
            
            
            
                }
                
    
        });
    
    }
    
	private void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		//定义菜单目录
		JMenu oper_JM = new JMenu("操作");
		JMenu set_JM = new JMenu("设置");
		
		oper_JM.setMnemonic(KeyEvent.VK_F);
		JMenuItem oper_exit_JMitem = new JMenuItem("退出", KeyEvent.VK_X);
		JMenuItem oper_reback_JMitem = new JMenuItem("返回", KeyEvent.VK_X);
		JMenuItem set_chuser_JMitem = new JMenuItem("更改账号");
		JMenuItem set_ad_JMitem = new JMenuItem("后台");
		 
		set_JM.add(set_chuser_JMitem);
		set_JM.add(set_ad_JMitem);
		//菜单栏加入菜单名
		menuBar.add(oper_JM);
		menuBar.add(set_JM);
		//加入菜单功能
		setJMenuBar(menuBar);
		oper_JM.add(oper_exit_JMitem);
		oper_JM.add(oper_reback_JMitem);
		// 设置点击退出后可以退出
		oper_exit_JMitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		oper_reback_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				AtmFrame af = new AtmFrame(username,client_id);
				setVisible(false);
				af.setVisible(true);
			}
		});
		set_chuser_JMitem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				setVisible(false);
				lf.setVisible(true);
			}
		});
		
		set_ad_JMitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strid = JOptionPane.showInputDialog("请输入超管口令");
                if (strid.equals("root"))
                {
                	AdminFrame af = new AdminFrame(username,client_id);
    				setVisible(false);
    				af.setVisible(true);
                }
//				Object[] possibleValues = { "客户经理", "维护", "数据" }; 
//				Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "后台", 
//				JOptionPane.INFORMATION_MESSAGE, null, 
//				possibleValues, possibleValues[0]);
			} 
	});
	}
public void hyperlinkUpdate(HyperlinkEvent e) {
	// TODO Auto-generated method stub
	
}
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

    
}
