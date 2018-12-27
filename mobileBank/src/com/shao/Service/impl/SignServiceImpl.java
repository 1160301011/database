package com.shao.Service.impl;

import java.sql.SQLException;

import com.shao.DAO.impl.BankuserDaoImpl;
import com.shao.DAO.impl.ClientDaoImpl;
import com.shao.Service.SignService;
import com.shao.model.Bankuser;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *ҵ���߼��ӿ�ʵ����
 *ע��������� 
 *
 */
public class SignServiceImpl implements SignService {
	
		BankuserDaoImpl bankuser = new BankuserDaoImpl();
		ClientDaoImpl clientinfo = new ClientDaoImpl();
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
	
	/**
	 * ����û���Ϣ
	 */
	public void addUser(String name, String password) {
		try {
			ManagerThreadLocal.startTransaction();
			bankuser.addUser(name, password);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * ��ӿͻ���Ϣ
	 * @param username
	 */
	public void addClientinfo2(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			clientinfo.addClientinfo2(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
}
