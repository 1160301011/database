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
 * @author Administrator
 * 客户信息Dao接口
 */
public interface ClientDao {

	/**
	 * @param client_id...uid
	 * @throws SQLException 
	 * 更新客户信息
	 */
	public void updateClientinfo(String client_id,String uid, String client_name, String client_idcard,
			String client_addr, String client_phone) throws SQLException;
	
	/*
	 * @hgx
	 * 输入客户ID查找客户信息
	 */
		//检验同名客户ID 无法添加
	public Clientinfo findClientID(String client_id) throws SQLException;
	
	
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * 客户信息查询
	 */
	public Clientinfo cfo_query(String username) throws SQLException;
	
	
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * 添加客户信息
	 */
	public void addClientinfo2(String username) throws SQLException;
	
	
	/**
	 * 客户信息查询
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Clientinfo cinfo_query(String uid) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
