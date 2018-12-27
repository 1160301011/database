package com.shao.Service.impl;

import com.shao.Service.ClientService;

import java.sql.SQLException;

import javax.swing.JTextField;

import com.shao.DAO.impl.AccountDaoImpl;
import com.shao.DAO.impl.ClientDaoImpl;
import com.shao.Service.AccountService;
import com.shao.model.User;
import com.shao.util.ManagerThreadLocal;
import com.shao.model.Bankinfo;
import com.shao.model.Bankcard;
import com.shao.model.Clientinfo;
import com.shao.model.Bankuser;
import com.shao.model.Creditcard;
import com.shao.model.Payee;
import com.shao.model.T_productchosen;
/**
 * @author HGX
 *业务逻辑接口实现类
 *客户信息操作服务 
 *
 */

public class ClientServiceImpl implements ClientService {
	AccountDaoImpl account = new AccountDaoImpl();
	ClientDaoImpl client = new ClientDaoImpl();
	
	public Clientinfo cquery(String client_id) {
		Clientinfo c = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			c = client.findClientID(client_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return c;
	}
	
	public void updateClientinfo(String client_id,String uid, String client_name, String client_idcard,
			String client_addr, String client_phone) {
		try {
			ManagerThreadLocal.startTransaction();
			account.updateClientinfo(client_id,uid, client_name, client_idcard,
					client_phone,client_addr);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	public Clientinfo cfo_check(String username) throws SQLException {
		Clientinfo c = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			c = account.cfo_query(username);
			ManagerThreadLocal.commit();
		} finally {
			ManagerThreadLocal.close();
		}
		return c;
	}
	

	public void addClientinfo2(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addClientinfo2(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	
	public Clientinfo cinfo_query(String uid) {
		Clientinfo cif = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			cif = account.cinfo_query(uid);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return cif;
		}
	
	
}
