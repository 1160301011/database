package com.shao.Service;

import java.sql.SQLException;

import com.shao.model.Bankuser;
import com.shao.model.User;

/**
 * @author HGX 业务逻辑接口 
 * 账户管理操作服务
 * 
 */
public interface BankuserService {
	public void addUser(String name, String password);

	public Bankuser bankuser_check(String username, String userpwd);

	public User query(String name);

	public Bankuser bu_query(String username) throws SQLException;

	public Bankuser bu_queryuid(String uid) throws SQLException;

	public void update(String username, double userbalance);

	public User check(String name, String password);

	public void addbank_User(String username, String userpwd);

	public void updateUser(String bank_uname, String newpwdField)
			throws SQLException;

	public void update_bankuser_balance(String username, double temp2);

	public void upuser_status(String username);

	public void Foun_upuser_sta(String username);

	public void upuser_status2(String username);

	public void upuser_status3(String username);

}
