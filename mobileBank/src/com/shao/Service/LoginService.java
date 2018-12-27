package com.shao.Service;

import java.sql.SQLException;

import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
/**
 * @author HGX
 *业务逻辑接口
 *登录操作服务 
 *
 */
public interface LoginService {

	
	public Clientinfo cfo_check(String username) throws SQLException;
	
	

	/**
	 * 查询用户账号密码
	 */
	public Bankuser bankuser_check(String username, String userpwd);
	
	

	/**
	 * 用户信息查询
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public Bankuser bu_query(String username) throws SQLException;
	
	
	/*
	 * 账号挂失设置
	 */
	public void upuser_status(String username);
	
	
	
	
}
