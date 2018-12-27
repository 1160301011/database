package com.shao.DAO;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.model.Bankcard;
import com.shao.model.User;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *数据访问层 接口
 *账户表 
 *
 */

public interface AccountDao {
	/**
	 * 登录检验
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public User query(String name,String password) throws SQLException;
	
	/**
	 * 添加账户
	 * @throws SQLException 
	 */
	public void addbank_User(String username, String userpwd) throws SQLException;
	
	
	public void addUser(String name,String password) throws SQLException, ClassNotFoundException;
	
	/**
	 * 更新用户账户信息
	 * @throws SQLException 
	 */
	public void updateAccount(String FromName,String ToName,double userbalance) throws SQLException;

	
	/**
	 * 查询账户
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
   public User findUserByName(String name) throws SQLException;
   
   public Bankuser findbank_user_Name(String username) throws SQLException;
	/**
	 * 添加银行信息
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
   public Clientinfo findClientID(String client_id) throws SQLException;
	/**
	 * 添加银行信息
	 * @param uid...
	 * @return
	 * @throws SQLException 
	 */
   public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps, String card_balance,
			String current, String death) throws SQLException;
}
