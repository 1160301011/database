package com.shao.DAO.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.DAO.BankuserDao;
import com.shao.model.Bankcard;
import com.shao.model.Bankuser;
import com.shao.model.User;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;


/**
 * 
 * @author hgx
 *用户表
 *数据层接口实例
 */
public class BankuserDaoImpl implements BankuserDao {

	public void addUser(String name, String password)throws SQLException  {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//		String sql ="insert into bank_user (username,userpwd,userbalance) values (?,?,?)";
//		Object[] params = {name,password,CONCAT('6',ceiling(rand()*10+100))};
//			int row = qr.update(sql,params);
		try {
			qr.update("insert into bank_user (username,userpwd) values (?,?)",name,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	

	/*储蓄卡信息查询
	 * 转账用户查询
	 * 多表查询  bankcard & bank_user
	 */
	public Bankcard findbkd_Descard(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bank_user bu join bankcard bkd on bu.uid=bkd.uid where bkd.card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
	}
	

	/*
	 * 更新账户余额
	 */
	public void updatebankuser_balance(String username, double temp2) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bank_user set userbalance = ? where username = ? ",temp2,username);
	
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
	

}
