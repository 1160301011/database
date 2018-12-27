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
 * @author HGX 业务逻辑接口 
 * 客户信息管理服务
 * 
 */

public interface ClientService {

	public Clientinfo cquery(String client_id);

	public Clientinfo cinfo_query(String uid);

	public void addClientinfo2(String username);

	public Clientinfo cfo_check(String username) throws SQLException;

	public void updateClientinfo(String client_id, String uid,
			String client_name, String client_idcard, String client_addr,
			String client_phone);

}
