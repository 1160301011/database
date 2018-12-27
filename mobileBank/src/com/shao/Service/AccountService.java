package com.shao.Service;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
import com.shao.model.User;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *业务逻辑接口
 *账户操作服务 
 *
 */
public interface AccountService {

	/**
	 * 注册账户
	 */
	public void addbank_User(String username, String userpwd);

	/**
	 * 注册用户
	 */
	public void addUser(String name, String passsword);

	/**
	 * 登录验证，查询
	 */
	public User check(String name, String password);

	/**
	 * 转账
	 */
	public void transfer(String FromName, String ToName, double balance);

	/**
	 * 存款
	 */
	public void update(String name, double balance);

	/**
	 * 查询用户信息
	 */

	public User query(String name);

	/**
	 * 查询客户ID
	 */
	public Clientinfo cquery(String client_id);

	/**
	 * 添加绑定银行卡信息
	 * @throws SQLException 
	 */
	public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps,String current, String death) throws SQLException;

}
