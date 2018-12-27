package com.shao.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.shao.model.Bankcard;
import com.shao.model.Creditcard;
import com.shao.model.User;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *数据访问层 接口
 *信用卡表 
 *
 */
public interface CreditcardDao {

	public Creditcard cre_card_query(String descard);

	// public Creditcard query_crd_serial(String paycard);
	//
	// public void addcredit_card(String uid, String client_id, String cre_pwd,
	// String return_card, String cre_closepay, String cre_auto,
	// String cre_balance) throws ClassNotFoundException;
	//
	// public Creditcard query_creditcard_info(String cre_id);
	//
	// public void add_cre_billrecord(String cre_id, double summoeny,
	// String cre_serial) throws ClassNotFoundException;
	//
	// public Creditcard query_cre_card(String cre_id);
	//
	// public void upcredit_card_available(double cre_balance, String cre_id);

	/**
	 * 查询信用卡信息
	 * 
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard findcrd_Descard(String cre_id) throws SQLException;
	
	/**
	 * 更新信用卡可用余额
	 * 
	 * @param cre_serial
	 * @param cre_available
	 * @throws SQLException
	 */
	public void upd_paycard_cre_available(String cre_serial,
			double cre_available) throws SQLException;
	
	/**
	 * 信用卡信息查询
	 * 
	 * @param paycard
	 * @return
	 * @throws SQLException
	 */
	public Creditcard findCredit_card_serial(String paycard)
			throws SQLException;
	
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
			String cre_balance) throws SQLException, ClassNotFoundException;

	/**
	 * 信用卡信息查询
	 * 
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard find_crecard_info(String cre_id) throws SQLException;

	/**
	 * 更新信用卡可用余额
	 * 
	 * @param cre_id
	 * @param temp
	 * @throws SQLException
	 */
	public void update_cre_available(String cre_id, double temp)
			throws SQLException;

	/**
	 * 更新信用卡消费账单
	 * 
	 * @param cre_id
	 * @param totalmoney
	 * @throws SQLException
	 */
	public void update_cre_bill(String cre_id, double totalmoney)
			throws SQLException;

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
			String cre_serial) throws ClassNotFoundException, SQLException;
	/**
	 * 信用卡信息查询
	 * 
	 * @param cre_id
	 * @return
	 * @throws SQLException
	 */
	public Creditcard query_cre_card(String cre_id) throws SQLException;

	/***
	 * 更新信用卡可用余额
	 * 
	 * @param cre_balance
	 * @param cre_id
	 * @throws SQLException
	 */
	public void upcredit_card_available(double cre_balance, String cre_id)
			throws SQLException;

	
	
	
	

}
