package com.shao.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.DAO.AccountDao;
import com.shao.model.Bankinfo;
import com.shao.model.Bankuser;
import com.shao.model.Bankcard;
import com.shao.model.Creditcard;
import com.shao.model.Payee;
import com.shao.model.T_productchosen;
import com.shao.model.User;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
import com.shao.model.Clientinfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import org.apache.commons.dbutils.QueryRunner;

/**
 * 
 * @author hgx
 *用户表
 *数据层接口实例
 */
public class AccountDaoImpl implements AccountDao{

	
	public User query(String name, String password) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank where name = ? and password = ?",new BeanHandler<User>(User.class), name,password);
	}
	/**
	 * @param name...password
	 * @throws SQLException 
	 * 添加账户
	 */
	public void addUser(String name, String password) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//		String sql ="insert into bank_user (username,userpwd,userbalance) values (?,?,?)";
//		Object[] params = {name,password,CONCAT('6',ceiling(rand()*10+100))};
//			int row = qr.update(sql,params);
		qr.update("insert into bank_user (username,userpwd) values (?,?)",name,password);
//		qr.update("insert into bank (name,password,balance) values (?,?,?)",name,password,0);
		}
//	public void updateAccount(String FromName, String ToName, double userbalance) throws SQLException {
//		QueryRunner qr = new QueryRunner();
//		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userbalance =userbalance - ? where username = ?",userbalance,FromName);
//		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userbalance =userbalance + ? where username = ?",userbalance,ToName);
//	}
	
	/*
	 * @hgx
	 * 更新账户余额
	 */
	public void updateAccount(String username,double userbalance) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userbalance = ? where username = ? ",userbalance,username);
	}
	/*
	 * @hgx
	 * 
	 */
//	public User findUserByName(String name) throws SQLException {
//		QueryRunner qr = new QueryRunner();
//		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank where name = ?",new BeanHandler<User>(User.class), name);
//	}
	
	/*
	 * @hgx
	 * 输入客户ID查找客户信息
	 */
		//检验同名客户ID 无法添加
	public Clientinfo findClientID(String client_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from client_info where client_id = ?",new BeanHandler<Clientinfo>(Clientinfo.class), client_id);
	}

	/**
	 * @param client_id...uid
	 * @throws SQLException 
	 * 更新客户信息
	 */
	public void updateClientinfo(String client_id,String uid, String client_name, String client_idcard,String client_phone,String client_addr) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("update CLIENT_INFO set uid = ?,client_name = ?,client_idcard = ?,client_phone = ?,addr = ? where client_id = ?",
				uid,client_name,client_idcard,client_phone,client_addr,client_id);
	}
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * 客户信息查询
	 */
	public Clientinfo cfo_query(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select client_id from client_info where username = ? ",new BeanHandler<Clientinfo>(Clientinfo.class), username);
	}
	/**
	 * @param username...userpwd
	 * @throws SQLException 
	 * @return
	 * 账户信息查询
	 */
	public Bankuser findbank_userquery(String username,String userpwd) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user where username = ? and userpwd = ?",new BeanHandler<Bankuser>(Bankuser.class),username,userpwd);
	}
	/**
	 * @param username
	 * @throws SQLException 
	 * @return
	 * 查询账户信息
	 */
	public Bankuser findbank_user_Name(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user where username = ?",new BeanHandler<Bankuser>(Bankuser.class), username);
	}
	/**
	 * @param payee_card
	 * @throws SQLException 
	 * @return
	 * 查询收款人表信息
	 */
	public Payee pye_findByPayee_info(String payee_card) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from payee where payee_card = ?",new BeanHandler<Payee>(Payee.class), payee_card);
	}
	
	/**
	 * @param payee_uid 
	 * @throws SQLException 
	 * 
	 * 添加收款人表信息
	 */
	public void addpayinfo(String payee_uid,String payee_name, String payee_card, String belong_bank) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into payee (uid,payee_name,payee_card,belong_bank) values (?,?,?,?)",
				payee_uid,payee_name,payee_card,belong_bank);
	}
	/**
	 * 用户信息查询
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Bankuser findbank_user_uid(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user where uid = ?",new BeanHandler<Bankuser>(Bankuser.class), uid);
	}
	/**
	 * 添加用户信息
	 */
	public void addbank_User(String username, String userpwd) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into bank_user (username,userpwd,userbalance) values (?,?,?)",new BeanHandler<Bankuser>(Bankuser.class),username,userpwd,0);
	}

	/**
	 * 更新用户密码
	 * @param bank_uname
	 * @param newpwdField
	 * @throws SQLException
	 */
	public void updatebank_User(String bank_uname, String newpwdField) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userpwd = ? where username = ? ",newpwdField,bank_uname);
		}
	
	/*
	 * 绑定银行卡信息
	 */
	public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps,String current, String death) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into bankcard (card_serial,uid,client_id,card_id,int_serial,card_ps,current,death) values (?,?,?,?,?,?,?,?)",
				card_serial,uid,bclient_id,card_id,int_serial,card_ps,current,death);
		
	}
	
	/**
	 * 查询银行卡信息
	 * @param card_serial
	 * @return
	 * @throws SQLException
	 */
	public Bankcard bkd_FindCard_id(String card_serial) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard where card_serial = ?",new BeanHandler<Bankcard>(Bankcard.class), card_serial);
	}
	
	/**
	 * 查询银行具体信息
	 * @param strbank_id
	 * @return
	 * @throws SQLException
	 */
	public Bankinfo bif_Findbank_id(String strbank_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_info where bank_id = ?",new BeanHandler<Bankinfo>(Bankinfo.class), 1);
	}

	
	/*储蓄卡信息查询
	 * 转账用户查询
	 * 多表查询  bankcard & bank_user
	 */
	public Bankcard findbkd_Descard(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user bu join bankcard bkd on bu.uid=bkd.uid where bkd.card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
	}
	
	
	/**
	 * 查询信用卡信息
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard findcrd_Descard(String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user bu join CREDIT_CARD crd on bu.uid=crd.uid where crd.cre_id = ?",new BeanHandler<Creditcard>(Creditcard.class), cre_id);
	}

	/*
	 * 更新转账后的银行卡余额信息
	 */
	public void upd_bankcard_balance(String outcard,String descard, double tra_money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set card_balance =card_balance - ? where card_id = ?",tra_money,outcard);
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set card_balance =card_balance + ? where card_id = ?",tra_money,descard);
	
	}
	/*
	 * 更新转账后的银行卡余额信息
	 */
	public void upd_bankcard_balance(String card,double tra_money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ? ",tra_money,card);
	}

	/**
	 * 查询银行卡信息
	 * @param card_id
	 * @return
	 * @throws SQLException
	 */
	public Bankcard findbkd_card_serial(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard where card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
	}
/*
 * 添加转账交易记录
 */
	public void addtra_record(String card_serial, String client_id,
			String des_card, String tra_money) throws SQLException, ClassNotFoundException {
//		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//		qr.update("insert into bank_user (username,userpwd,userbalance) values (?,?,?)",new BeanHandler<bank_user>(bank_user.class),username,userpwd,0);
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//		Date dadate = new Date();
		//获得系统时间
//		String nowDaDate = sdf.format(dadate);
//		Timestamp daTs = Timestamp.valueOf(nowDaDate);
		
		Statement stat = (Statement) con.createStatement();
		System.out.println("转账测试2");
		String insert_tra = "insert into TRANSFER (trade_id,card_serial,client_id,to_cardid,trade_money,int_out) values(CONCAT('T911',ceiling(rand()*10000+10000)),"+card_serial+","+client_id+","+des_card+","+tra_money+",'1')";
		System.out.println("转账测试3");
//		stat.executeUpdate("insert into TRANSFER (trade_id) values(CONCAT('999',ceiling(rand()*10000+10000)))");
		stat.executeUpdate(insert_tra);
//		String str11 = "CONCAT('666',ceiling(rand()*10000+10000))";
//		String sql = "insert into TRANSFER (trade_id,client_id,to_cardid,trade_money,trade_time) values(?,?,?,?,?)";
////		String sql2 ="update TRANSFER set trade_time = ? where trade_id = ?";
//		PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//		pst.setObject(1, "+str11+");
//		pst.setObject(2, client_id);
//		pst.setObject(3, des_card);
//		pst.setObject(4, tra_money);
//		pst.setObject(5, daTs);
//		pst.executeUpdate();
		System.out.println("转账记录表插入成功");
//		System.out.println(daTs);
//		String sql = "Select * from TRANSFER";
//		ResultSet rs = stat.executeQuery(sql);
//		while (rs.next()) {
//		System.out.println(rs.getString("trade_id"));
//		}
//		rs.close();
		stat.close();
//		pst.close();
		con.close();
}
/*
 * 查询付款卡号的序列号
 */
	public Bankcard finpay_card_serial(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard bkd where card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
	}
/*
 * 更新/储蓄卡缴费后的银行卡余额
 */
	public void upd_paycard_balance(String card_id, double pay_money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ? ",pay_money,card_id);
	}
	
	/**
	 * 更新信用卡可用余额
	 * @param cre_serial
	 * @param cre_available
	 * @throws SQLException
	 */
	public void upd_paycard_cre_available(String cre_serial,
			double cre_available) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update credit_card set cre_available = ? where cre_serial = ? ",cre_available,cre_serial);
	}

/*手机缴费记录
 * 添加银行卡消费记录表(接口实例化)
 */
	public void addConsumtion_phoenrecord(String card_serial, double trade_money) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		Statement stat = (Statement) con.createStatement();
		String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+trade_money+",'1')";
		stat.executeUpdate(insert_Com);

		System.out.println("消费记录表插入成功");
		
		stat.close();
		con.close();
	}
	/*
	 * 插入交通缴费消费记录
	 */
	public void addConsumtion_trarecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		Statement stat = (Statement) con.createStatement();
		String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+com_money+",'2')";
		stat.executeUpdate(insert_Com);

		System.out.println("消费记录表插入成功");
		
		stat.close();
		con.close();
	}
	/*
	 * 插入生活缴费 消费记录
	 */
	public void addConsumtion_liferecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		Statement stat = (Statement) con.createStatement();
		String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+com_money+",'3')";
		stat.executeUpdate(insert_Com);

		System.out.println("消费记录表插入成功");
		
		stat.close();
		con.close();
	}
/*
 * 插入医保消费记录
 */
	public void addConsumtion_medirecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		Statement stat = (Statement) con.createStatement();
		String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+com_money+",'4')";
		stat.executeUpdate(insert_Com);

		System.out.println("消费记录表插入成功");
		
		stat.close();
		con.close();
	}
	/**
	 * 信用卡信息查询
	 * @param paycard
	 * @return
	 * @throws SQLException
	 */
	public Creditcard findCredit_card_serial(String paycard) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select cre_serial from credit_card where cre_id = ? ",new BeanHandler<Creditcard>(Creditcard.class),paycard);
	}
	/**
	 * 开通信用卡
	 * @param uid
	 * @param client_id
	 * @param cre_pwd
	 * @param return_card
	 * @param cre_closepay
	 * @param cre_auto
	 * @param cre_balance
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void addCredit_Card(String uid,String client_id,String cre_pwd, String return_card,String cre_closepay, String cre_auto,
			String cre_balance)throws SQLException, ClassNotFoundException  {
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		Statement stat = (Statement) con.createStatement();
		String insert_Cre = "insert into CREDIT_CARD (cre_serial,client_id,uid,cre_id,cre_pwd,cre_balance,"
				+ "cre_available,return_card,cre_auto,cre_closepay)values"
				+ "(CONCAT('0002',ceiling(rand()*100000000000+100000000000)),"+client_id+","+uid+",CONCAT('7521',ceiling(rand()*100000000000+100000000000)),"+cre_pwd+","+cre_balance+","+cre_balance+","+return_card+","+cre_auto+","+cre_closepay+")";
		stat.executeUpdate(insert_Cre);

		System.out.println("成功开通信用卡");
		
		stat.close();
		con.close();
		
	}
//	public credit_card findcrd_Descard(String cre_id) throws SQLException {
//		QueryRunner qr = new QueryRunner();
//		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user bu join CREDIT_CARD crd on bu.uid=crd.uid where crd.cre_id = ?",new BeanHandler<credit_card>(credit_card.class), cre_id);
//	}
	
	/**
	 * 储蓄卡信息查询
	 * @param card_id
	 * @return
	 * @throws SQLException
	 */
	public Bankcard findcard_serial(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard  where card_id = ?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
		
	}
/*
 * 取款 卡号的死期余额 扣除SQL语句
 */
	public void updatebankcard_death(String card_serial, double temp) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set death = ? where card_serial = ? ",temp,card_serial);
	
	}

	/*
	 * 更新账户余额
	 */
	public void updatebankuser_balance(String username, double temp2) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userbalance = ? where username = ? ",temp2,username);
	
	}
	
	
	/**
	 * 添加取款记录
	 * @param card_serial
	 * @param withmoney
	 * @param interest
	 * @param totalmoney
	 * @param temp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addinsert_WITHDRAWAL(String card_serial, String withmoney,
			String interest, String totalmoney, double temp) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		
		Statement stat = (Statement) con.createStatement();
		String insert_wit = "insert into WITHDRAWAL (wid_num,card_serial,wid_pre_money,wid_interest,wid_get_money,card_balance)"
				+ "values(CONCAT('w001',ceiling(rand()*10000+10000)),"+card_serial+","+withmoney+","+interest+","+totalmoney+","+temp+")";
		stat.executeUpdate(insert_wit);

		System.out.println("取款记录表插入成功");
		
		stat.close();
		con.close();
		
	}
	
	/**
	 * 查询超市商品表信息
	 * @param goods_id
	 * @return
	 * @throws SQLException
	 */
	public T_productchosen find_product(String goods_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from t_productchosen  where id=?",new BeanHandler<T_productchosen>(T_productchosen.class), goods_id);
	}
	/**
	 * 信用卡信息查询
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard find_crecard_info(String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from credit_card where cre_id = ?",new BeanHandler<Creditcard>(Creditcard.class), cre_id);
	}
	/**
	 * 更新信用卡可用余额
	 * @param cre_id
	 * @param temp
	 * @throws SQLException
	 */
	public void update_cre_available(String cre_id, double temp) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update credit_card set cre_available = ? where cre_id = ? ",temp,cre_id);
	}

	/**
	 * 更新信用卡消费账单
	 * @param cre_id
	 * @param totalmoney
	 * @throws SQLException
	 */
	public void update_cre_bill(String cre_id, double totalmoney) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update credit_card set this_month_money = this_month_money + ? ,his_month_money = his_month_money + ? where cre_id = ? ",totalmoney,totalmoney,cre_id);
	
	}
	/**
	 * 添加信用卡消费记录
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add_cre_billrecord(String cre_id, double summoeny,String cre_serial) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
//		String cre = cre_id.toString();
//		System.out.println(cre);
		
		Statement stat = (Statement) con.createStatement();
		String Cre_consumption = "insert into CREDIT_CONSUMPTION (crecon_serial,crecon_money,cre_id) values(CONCAT('c101',ceiling(rand()*100000000+100000000)),"+summoeny+","+cre_id+")";
		stat.executeUpdate(Cre_consumption);
		
//		String upCre_consumption="update CREDIT_CONSUMPTION set cre_serial = "+cre_serial+" where cre_id ="+cre_id+"";
//		stat.executeUpdate(upCre_consumption);
		System.out.println("成功插入信用卡消费记录");
		stat.close();
		con.close();
	}
	/**
	 * 添加客户信息
	 * @param username
	 * @throws SQLException
	 */
	public void addClientinfo2(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into client_info(username) values(?)",username);
	}
	/**
	 * 信用卡信息查询
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard query_cre_card(String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from credit_card where cre_id=?",new BeanHandler<Creditcard>(Creditcard.class), cre_id);
	}
	
	/***
	 * 更新信用卡可用余额
	 * @param cre_balance
	 * @param cre_id
	 * @throws SQLException
	 */
	public void upcredit_card_available(double cre_balance, String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update credit_card set cre_available = ? where cre_id = ? ",cre_balance,cre_id);
	
	}
	/**
	 * 储蓄卡信息查询
	 * @param card_id
	 * @return
	 * @throws SQLException
	 */
	public Bankcard query_bankcard(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard where card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
	}
	
	/**
	 * 更新储蓄卡余额
	 * @param card_id
	 * @param temp
	 * @throws SQLException
	 */
	public void upbankcard_balance(String card_id, double temp) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set card_balance = card_balance - ? where card_id = ? ",temp,card_id);
	
	}
	
	/**
	 * 储蓄卡信息查询
	 * @param tocard_id
	 * @return
	 * @throws SQLException
	 */
	public Bankcard bkd_cif_query(String tocard_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from client_info ci join bankcard bc on ci.client_id =bc.client_id where bc.card_id = ?",new BeanHandler<Bankcard>(Bankcard.class), tocard_id);
	}
	
	/**
	 * 更新储蓄卡活期余额
	 * @param card_id
	 * @param tra_money
	 * @throws SQLException
	 */
	public void upd_bankcard_current(String card_id,  double tra_money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ?",tra_money,card_id);
	
	
	}
	/**
	 * 添加转账记录表
	 * @param card_serial
	 * @param client_id
	 * @param tocard
	 * @param formatmoney
	 * @param fee
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addtra_out_record(String card_serial, String client_id,
			String tocard, String formatmoney,double fee) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
//		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//		Date dadate = new Date();
		//获得系统时间
//		String nowDaDate = sdf.format(dadate);
//		Timestamp daTs = Timestamp.valueOf(nowDaDate);
		
		Statement stat = (Statement) con.createStatement();
		String insert_tra = "insert into TRANSFER (trade_id,card_serial,client_id,to_cardid,trade_money,trade_remark,int_out,trade_fee) values(CONCAT('T911',ceiling(rand()*10000+10000)),"+card_serial+","+client_id+","+tocard+","+formatmoney+",'跨行','2',"+fee+")";
//		stat.executeUpdate("insert into TRANSFER (trade_id) values(CONCAT('999',ceiling(rand()*10000+10000)))");
		stat.executeUpdate(insert_tra);
//		String str11 = "CONCAT('666',ceiling(rand()*10000+10000))";
//		String sql = "insert into TRANSFER (trade_id,client_id,to_cardid,trade_money,trade_time) values(?,?,?,?,?)";
////		String sql2 ="update TRANSFER set trade_time = ? where trade_id = ?";
//		PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//		pst.setObject(1, "+str11+");
//		pst.setObject(2, client_id);
//		pst.setObject(3, des_card);
//		pst.setObject(4, tra_money);
//		pst.setObject(5, daTs);
//		pst.executeUpdate();
		System.out.println("转账记录表插入成功");
//		System.out.println(daTs);
//		String sql = "Select * from TRANSFER";
//		ResultSet rs = stat.executeQuery(sql);
//		while (rs.next()) {
//		System.out.println(rs.getString("trade_id"));
//		}
		stat.close();
		con.close();
	}
	
	/**
	 * 更新用户状态
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userstatus = ? where username = ? ",2,username);
	}
	/**
	 * 客户信息查询
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Clientinfo cinfo_query(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from client_info where uid = ?",new BeanHandler<Clientinfo>(Clientinfo.class), uid);
	}
	/**
	 * 忘记密码挂失找回
	 * 更新账户状态
	 * @param username
	 * @throws SQLException
	 */
	public void Foun_upuser_sta(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userstatus = ? where username = ? ",1,username);
	}
	/**
	 * 更新储蓄卡活期余额
	 * @param card_id
	 * @param bcurrent
	 * @throws SQLException
	 */
	public void upcurrent(String card_id, double bcurrent) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ? ",bcurrent,card_id);
	
	}
	
	/**
	 * 更新储蓄卡定期余额
	 * @param card_id
	 * @param bdeath
	 * @throws SQLException
	 */
	public void updeath(String card_id, double bdeath) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set death = ? where card_id = ? ",bdeath,card_id);
	
	}
	
	/**
	 * 解绑储蓄卡
	 * @param card_id
	 * @throws SQLException
	 */
	public void del_card(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("delete from bankcard where card_id = ?",card_id);
	}
	
	/**
	 *添加储蓄卡信息 
	 */
	public void addBankCard(String card_serial, String uid, String bclient_id,
			String card_id, String int_serial, String card_ps,
			String card_balance, String current, String death)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

	public User findUserByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 更新账户状态
	 * 限制交易功能
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status2(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userstatus = ? where username = ? ",3,username);
	
	}
	
	/**
	 * 更新账户状态
	 * 解除限制交易功能
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status3(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userstatus = ? where username = ? ",1,username);
	
	}
	public void updateAccount(String FromName, String ToName, double userbalance)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}


	}

	


	
	
	
	
	
	


