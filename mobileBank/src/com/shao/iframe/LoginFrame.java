package com.shao.iframe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;
import com.shao.Service.impl.AccountServiceImpl;
import com.shao.model.*;
/**
 * @author hgx
 * 表示层
 * 登录主界面
 */
public class LoginFrame extends JFrame {
	private com.shao.model.Bankuser bu;
	private Image image;
	private static final long serialVersionUID = -1588458291133087637L;
	private JPanel contentPane;
	private JTextField bank_name_Field;
	private JPasswordField bank_pwd_Field;
	private LoginFrame logframe;
	private Container container;
	final AccountServiceImpl asi = new AccountServiceImpl();
	// 登录事件监听器
	class loginAction implements ActionListener {
		@SuppressWarnings({ "deprecation", "null" })
		public void actionPerformed(ActionEvent e) {
			String string=new String();
			// user user = new user();
			Bankuser bu = new Bankuser();
			Clientinfo cfo = new Clientinfo();
			Bankinfo bif = new Bankinfo();
			try {
				cfo = asi.cfo_check(bank_name_Field.getText());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// 获取输入框信息 检验数据中是否有该用户存在
			bu = asi.bankuser_check(bank_name_Field.getText(),
					bank_pwd_Field.getText());
			if (!bank_name_Field.getText().equals("")) {
				if (bu.getUsername()!=null) {
					if (bu.getUserpwd().equals(bank_pwd_Field.getText())) {
						if(bu.getUserstatus().equals("1")||bu.getUserstatus().equals("3")){
						try {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df.format(new Date());
							string=string+bu.getUsername()+"已成功登录，当前责任人：业务员1"+"\n";
							AtmFrame frame = new AtmFrame(bu.getUsername(),
									cfo.getClient_id());
							frame.setTitle(bu.getUsername());
							frame.setVisible(true);
							setVisible(false);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						}else{
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							string=string+df.format(new Date());
							string=string+"当前程序：登录，当前责任人：用户"+bu.getUsername()+"\n";
							string=string+"账号已冻结！请先解锁！"+"\n";
							JOptionPane.showMessageDialog(null, "账号已冻结！请先解锁！");
						}
					} else {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						string=string+df.format(new Date());
						string=string+"当前程序：登录，当前责任人：用户"+bu.getUsername()+"\n";
						string=string+ "密码不正确！请重新输入"+"\n";
						JOptionPane.showMessageDialog(null, "密码不正确！请重新输入");
//						bank_name_Field.setText("");
						bank_pwd_Field.setText("");
					}
				} else {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					string=string+df.format(new Date());
					string=string+"当前程序：登录，当前责任人：用户"+bu.getUsername()+"\n";
					string=string+ "找不到该用户，请先注册！"+"\n";
					JOptionPane.showMessageDialog(null, "找不到该用户，请先注册！");
					bank_name_Field.setText("");
					bank_pwd_Field.setText("");
				}
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				string=string+df.format(new Date());
				string=string+"当前程序：登录，当前责任人：用户"+bu.getUsername()+"\n";
				string=string+ "请输入用户名！"+"\n";
				JOptionPane.showMessageDialog(null, "请输入用户名！");
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

	// 注册事件监听器
	class signAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SignFrame frame = new SignFrame();
			frame.setVisible(true);
			setVisible(false);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced
					 * Libraries -> 点击substance.jar ->
					 * 找到org.jvnet.substance.skin这个包 ->
					 * 将下面的SubstanceDustCoffeeLookAndFeel 替换成
					 * 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					// 例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					// UIManager
					// .setLookAndFeel(new
					// org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
//					 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				LoginFrame browser = new LoginFrame();
				browser.setVisible(true);
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {

		logframe = this;
		// 不绘制窗口标签和标题
		this.setUndecorated(true);
		// 获取屏幕宽度和高度
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		// 加载图片
		ImageIcon icon = new ImageIcon("images/bg5.png");
		// 将图片放入label中
		JLabel lbl_showbg = new JLabel(icon);
		// 设置label的大小
		lbl_showbg.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		// 获取窗口的第二层，将label放入
		this.getLayeredPane().add(lbl_showbg, new Integer(Integer.MIN_VALUE));
		// 获取frame的顶层容器,并设置为透明
		JPanel j = (JPanel) this.getContentPane();
		j.setOpaque(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, screenWidth, screenHeight);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton forgetBtn = new JButton("忘记密码？");
		forgetBtn.setBorder(null);
		forgetBtn.setBorderPainted(false); // 无边框
		forgetBtn.setOpaque(false); // 设置按钮透明
		forgetBtn.setForeground(Color.LIGHT_GRAY); // 设置组件字体
		forgetBtn.setBounds(485, 480, 115, 20);
		forgetBtn.setContentAreaFilled(false); // 关闭底色
		// forgetBtn.setFocusPainted(false); //聚焦线
		forgetBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(forgetBtn);
		forgetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FounuserFrame fof = new FounuserFrame();
				fof.setVisible(true);
			}
		});
	
		
		JButton lossBtn = new JButton("挂失");
		lossBtn.setBorder(null);
		lossBtn.setBorderPainted(false); // 无边框
		lossBtn.setOpaque(false); // 设置按钮透明
		lossBtn.setForeground(Color.WHITE); // 设置组件字体
		lossBtn.setBounds(780, 480, 115, 20);
		lossBtn.setContentAreaFilled(false); // 关闭底色
		// forgetBtn.setFocusPainted(false); //聚焦线
		lossBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(lossBtn);
		lossBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bu =  asi.bu_query(bank_name_Field.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(!bank_name_Field.getText().equals("")){
				
				if(bu.getUserstatus().equals("1")){
					int res = JOptionPane.showConfirmDialog(null, "确认挂失？", "挂失确认", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
					//设置账号状态为冻结
					asi.upuser_status(bank_name_Field.getText());
					JOptionPane.showMessageDialog(null, "账号已冻结！");
					}else{
						
					}
				}else{
					JOptionPane.showMessageDialog(null, "账号已挂失,操作无效！");
				}
				}else{
					JOptionPane.showMessageDialog(null, "请输入账号！");
				}
				
			}
		});
		

		JButton LoginButton = new JButton(); // 登录按钮
		LoginButton.setContentAreaFilled(false);
		LoginButton.setBounds(540, 420, 120, 45);
		ImageIcon logbtn = new ImageIcon("images/ensure.png"); // 添加确定按钮图片
		Image temp = logbtn.getImage().getScaledInstance(
				LoginButton.getWidth(), LoginButton.getHeight(),
				logbtn.getImage().SCALE_DEFAULT);
		logbtn = new ImageIcon(temp);
		LoginButton.setIcon(logbtn);
		LoginButton.setToolTipText("image");
		contentPane.add(LoginButton);
		LoginButton.addActionListener(new loginAction());

		JButton signButton = new JButton(); // 注册按钮
		signButton.setContentAreaFilled(false);
		signButton.setBounds(720, 420, 120, 45);
		ImageIcon sigbtn = new ImageIcon("images/sign.png");
		Image temp2 = sigbtn.getImage().getScaledInstance(
				signButton.getWidth(), signButton.getHeight(),
				sigbtn.getImage().SCALE_DEFAULT);
		logbtn = new ImageIcon(temp2);
		signButton.setIcon(logbtn);
		signButton.setToolTipText("image");
		contentPane.add(signButton);
		signButton.addActionListener(new signAction());

		// JLabel u_nameLabel = new JLabel("\u7528\u6237\u540D\uFF1A"); // 用户名标签
		// u_nameLabel.setFont(new Font("新宋体", Font.BOLD, 18));
		// u_nameLabel.setBounds(92, 49, 88, 37);
		// contentPane.add(u_nameLabel);

		// JLabel u_passwordLabel_1 = new JLabel("\u5BC6\u7801\uFF1A"); // 密码标签
		// u_passwordLabel_1.setFont(new Font("新宋体", Font.BOLD, 18));
		// u_passwordLabel_1.setBounds(400, 430, 88, 27);
		// contentPane.add(u_passwordLabel_1);

		bank_name_Field = new JTextField(); // 用户名输入框
		bank_name_Field.setBounds(507, 245, 350, 50);
		bank_name_Field.setBorder(null);
		bank_name_Field.setOpaque(false);
		bank_name_Field.setForeground(Color.LIGHT_GRAY);
		bank_name_Field.setColumns(10);
		bank_name_Field.setSelectionColor(Color.GRAY);
		bank_name_Field.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		contentPane.add(bank_name_Field);

		bank_pwd_Field = new JPasswordField(); // 密码输入框
		bank_pwd_Field.setBounds(507, 345, 350, 50);
		bank_pwd_Field.setBorder(null);
		bank_pwd_Field.setOpaque(false);
		bank_pwd_Field.setForeground(Color.LIGHT_GRAY);
		bank_pwd_Field.setColumns(10);
		bank_pwd_Field.setSelectionColor(Color.GRAY);
		bank_pwd_Field.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		contentPane.add(bank_pwd_Field);

		// 添加系统时间显示标签
		JLabel time = new JLabel();
		this.getContentPane().add(time);
		this.setTimer(time);
		time.setBounds(50, 637, 300, 29);
		time.setForeground(Color.RED);
		time.setFont(new Font("新宋体", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("用户指南");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(1200, 647, 300, 29);
		lblNewLabel.setFont(new Font("新宋体", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		// 必须设置为透明的。否则看不到图片
		contentPane.setOpaque(false);
//		lblNewLabel.addAncestorListener(listener);

		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setVisible(true);
	}

	// 设置Timer 1000ms实现一次动作 实际是一个线程
	private void setTimer(JLabel time) {
		final JLabel varTime = time;
		Timer timeAction = new Timer(1000, new ActionListener() {

			//监听
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();
				// 转换日期显示格式
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				varTime.setText(df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
	}
}
