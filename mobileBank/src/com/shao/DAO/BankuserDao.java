package com.shao.DAO;

import java.sql.SQLException;

import com.shao.model.Bankcard;
import com.shao.model.Bankuser;
import com.shao.model.User;
/**
 * @author HGX
 *数据访问层 接口
 *用户表 
 *
 */
public interface BankuserDao {

	
	
//	public Bankuser bankuser_check(String username, String userpwd);
//	
//	public User query(String name);
//	
//	public Bankuser bu_query(String username) throws SQLException;
//	
//	public Bankuser bu_queryuid(String uid) throws SQLException;
//	
//	
//	public void update(String username, double userbalance);
//	
//	
//	public User check(String name, String password);
//	
//	public void addbank_User(String username, String userpwd);
//	
//	
//	public void updateUser(String bank_uname, String newpwdField)
//			throws SQLException;
//	
//	
//	public void update_bankuser_balance(String username, double temp2);
//	
//	
//	public void upuser_status(String username);
//	
//	
//	public void Foun_upuser_sta(String username);
//	
//	
//	public void upuser_status2(String username);
//	
//	public void upuser_status3(String username);
	

  public void addUser(String name, String password) throws SQLException;
  
  
  /*
	 * @hgx
	 * 更新账户余额
	 */
	public void updateAccount(String username,double userbalance) throws SQLException;
	
	/**
	 * @param username...userpwd
	 * @throws SQLException 
	 * @return
	 * 账户信息查询
	 */
	public Bankuser findbank_userquery(String username,String userpwd) throws SQLException;
	
	/**
	 * @param username
	 * @throws SQLException 
	 * @return
	 * 查询账户信息
	 */
	public Bankuser findbank_user_Name(String username) throws SQLException;
	
	/**
	 * 用户信息查询
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Bankuser findbank_user_uid(String uid) throws SQLException;
	
	/**
	 * 添加用户信息
	 */
	public void addbank_User(String username, String userpwd) throws SQLException;
	

	/**
	 * 更新用户密码
	 * @param bank_uname
	 * @param newpwdField
	 * @throws SQLException
	 */
	public void updatebank_User(String bank_uname, String newpwdField) throws SQLException;
	
	/*储蓄卡信息查询
	 * 转账用户查询
	 * 多表查询  bankcard & bank_user
	 */
	public Bankcard findbkd_Descard(String card_id) throws SQLException;
	
	/*
	 * 更新账户余额
	 */
	public void updatebankuser_balance(String username, double temp2) throws SQLException;
	
	
	/**
	 * 更新用户状态
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status(String username) throws SQLException;
	
	/**
	 * 忘记密码挂失找回
	 * 更新账户状态
	 * @param username
	 * @throws SQLException
	 */
	public void Foun_upuser_sta(String username) throws SQLException;
	
	/**
	 * 更新账户状态
	 * 限制交易功能
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status2(String username) throws SQLException;
	
	/**
	 * 更新账户状态
	 * 解除限制交易功能
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status3(String username) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  


}
