package com.shao.Service.impl;

import java.sql.SQLException;

import com.shao.DAO.impl.BankuserDaoImpl;
import com.shao.DAO.impl.ClientDaoImpl;
import com.shao.Service.LoginService;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *ҵ���߼��ӿ�ʵ����
 *��¼�������� 
 *
 */
public class LoginServiceImpl implements LoginService {
	ClientDaoImpl client = new ClientDaoImpl();
	BankuserDaoImpl bankuser = new BankuserDaoImpl();

	public Clientinfo cfo_check(String username) throws SQLException {
		Clientinfo c = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			c = client.cfo_query(username);
			ManagerThreadLocal.commit();
		} finally {
			ManagerThreadLocal.close();
		}
		return c;
	}
	
	/**
	 * ��ѯ�û��˺�����
	 */
	public Bankuser bankuser_check(String username, String userpwd) {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = bankuser.findbank_userquery(username, userpwd);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bu;
	}
	
	/**
	 * �û���Ϣ��ѯ
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public Bankuser bu_query(String username) throws SQLException {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = bankuser.findbank_user_Name(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bu;
	}
	
	/*
	 * �˺Ź�ʧ����
	 */
	public void upuser_status(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			bankuser.upuser_status(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
}
