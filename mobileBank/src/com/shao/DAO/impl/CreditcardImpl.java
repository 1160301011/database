package com.shao.DAO.impl;

import com.shao.DAO.CreditcardDao;


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
 *信用卡表
 *数据层接口实例
 */

public class CreditcardImpl implements CreditcardDao {

	/**
	 * 查询信用卡信息
	 * 
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard findcrd_Descard(String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr
				.query(ManagerThreadLocal.getConnection(),
						"select * from bank_user bu join CREDIT_CARD crd on bu.uid=crd.uid where crd.cre_id = ?",
						new BeanHandler<Creditcard>(Creditcard.class), cre_id);
	}

	/**
	 * 更新信用卡可用余额
	 * 
	 * @param cre_serial
	 * @param cre_available
	 * @throws SQLException
	 */
	public void upd_paycard_cre_available(String cre_serial,
			double cre_available) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(
				ManagerThreadLocal.getConnection(),
				"update credit_card set cre_available = ? where cre_serial = ? ",
				cre_available, cre_serial);
	}

	/**
	 * 信用卡信息查询
	 * 
	 * @param paycard
	 * @return
	 * @throws SQLException
	 */
	public Creditcard findCredit_card_serial(String paycard)
			throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),
				"select cre_serial from credit_card where cre_id = ? ",
				new BeanHandler<Creditcard>(Creditcard.class), paycard);
	}

	/**
	 * 开通信用卡
	 * 
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
	public void addCredit_Card(String uid, String client_id, String cre_pwd,
			String return_card, String cre_closepay, String cre_auto,
			String cre_balance) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,
				username, password);

		Statement stat = (Statement) con.createStatement();
		String insert_Cre = "insert into CREDIT_CARD (cre_serial,client_id,uid,cre_id,cre_pwd,cre_balance,"
				+ "cre_available,return_card,cre_auto,cre_closepay)values"
				+ "(CONCAT('0002',ceiling(rand()*100000000000+100000000000)),"
				+ client_id
				+ ","
				+ uid
				+ ",CONCAT('7521',ceiling(rand()*100000000000+100000000000)),"
				+ cre_pwd
				+ ","
				+ cre_balance
				+ ","
				+ cre_balance
				+ ","
				+ return_card + "," + cre_auto + "," + cre_closepay + ")";
		stat.executeUpdate(insert_Cre);

		System.out.println("成功开通信用卡");

		stat.close();
		con.close();

	}

	/**
	 * 信用卡信息查询
	 * 
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard find_crecard_info(String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),
				"select * from credit_card where cre_id = ?",
				new BeanHandler<Creditcard>(Creditcard.class), cre_id);
	}

	/**
	 * 更新信用卡可用余额
	 * 
	 * @param cre_id
	 * @param temp
	 * @throws SQLException
	 */
	public void update_cre_available(String cre_id, double temp)
			throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),
				"update credit_card set cre_available = ? where cre_id = ? ",
				temp, cre_id);
	}

	/**
	 * 更新信用卡消费账单
	 * 
	 * @param cre_id
	 * @param totalmoney
	 * @throws SQLException
	 */
	public void update_cre_bill(String cre_id, double totalmoney)
			throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(
				ManagerThreadLocal.getConnection(),
				"update credit_card set this_month_money = this_month_money + ? ,his_month_money = his_month_money + ? where cre_id = ? ",
				totalmoney, totalmoney, cre_id);

	}

	/**
	 * 添加信用卡消费记录
	 * 
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add_cre_billrecord(String cre_id, double summoeny,
			String cre_serial) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,
				username, password);
		// String cre = cre_id.toString();
		// System.out.println(cre);

		Statement stat = (Statement) con.createStatement();
		String Cre_consumption = "insert into CREDIT_CONSUMPTION (crecon_serial,crecon_money,cre_id) values(CONCAT('c101',ceiling(rand()*100000000+100000000)),"
				+ summoeny + "," + cre_id + ")";
		stat.executeUpdate(Cre_consumption);

		// String
		// upCre_consumption="update CREDIT_CONSUMPTION set cre_serial = "+cre_serial+" where cre_id ="+cre_id+"";
		// stat.executeUpdate(upCre_consumption);
		System.out.println("成功插入信用卡消费记录");
		stat.close();
		con.close();
	}

	/**
	 * 信用卡信息查询
	 * 
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard query_cre_card(String cre_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),
				"select * from credit_card where cre_id=?",
				new BeanHandler<Creditcard>(Creditcard.class), cre_id);
	}

	/***
	 * 更新信用卡可用余额
	 * 
	 * @param cre_balance
	 * @param cre_id
	 * @throws SQLException
	 */
	public void upcredit_card_available(double cre_balance, String cre_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),
				"update credit_card set cre_available = ? where cre_id = ? ",
				cre_balance, cre_id);

	}

	public Creditcard cre_card_query(String descard) {
		// TODO Auto-generated method stub
		return null;
	}

}
