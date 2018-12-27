package com.shao.Service.impl;

import java.sql.SQLException;

import com.shao.DAO.impl.AccountDaoImpl;
import com.shao.Service.BankuserService;
import com.shao.model.Bankuser;
import com.shao.model.User;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *ҵ���߼��ӿ�ʵ����
 *�û�������� 
 *
 */
public class BankuserServiceImpl implements BankuserService {
	AccountDaoImpl account = new AccountDaoImpl();
	
	/**
	 * ����û���Ϣ
	 */
	public void addUser(String name, String password) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addUser(name, password);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * ��ѯ�û��˺�����
	 */
	public Bankuser bankuser_check(String username, String userpwd) {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = account.findbank_userquery(username, userpwd);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bu;
	}
	
	/**
	 * �û���ѯ
	 */
	public User query(String name) {
		User u = new User();
		try {
			ManagerThreadLocal.startTransaction();
			u = account.findUserByName(name);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return u;
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
			bu = account.findbank_user_Name(username);
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
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Bankuser bu_queryuid(String uid) throws SQLException {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = account.findbank_user_uid(uid);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}

		return bu;
	}
	
	/**
	 * �����˻����
	 */
	public void update(String username, double userbalance) {
		// user u = new user();
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			account.updateAccount(username, userbalance);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * �û���Ϣ��ѯ
	 */
	public User check(String name, String password) {
		User u = new User();
		try {
			ManagerThreadLocal.startTransaction();
			u = account.query(name, password);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return u;
	}
	
	/**
	 * ע���˻�
	 */
	public void addbank_User(String username, String userpwd) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addbank_User(username, userpwd);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * �����˻���Ϣ
	 * @param bank_uname
	 * @param newpwdField
	 * @throws SQLException
	 */
	public void updateUser(String bank_uname, String newpwdField)
			throws SQLException {
		Bankuser bu = new Bankuser();
		User user = new User();
		try {
			ManagerThreadLocal.startTransaction();
			account.updatebank_User(bank_uname, newpwdField);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/*
	 * ȡ��� �˻������� 
	 */
	public void update_bankuser_balance(String username, double temp2) {
		try {
			ManagerThreadLocal.startTransaction();
			account.updatebankuser_balance(username, temp2);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/*
	 * �˺Ź�ʧ����
	 */
	public void upuser_status(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upuser_status(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * �һ����� ״̬����
	 * @param username
	 */
	public void Foun_upuser_sta(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.Foun_upuser_sta(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * @author hgx
	 * �����˻�������Ϊ
	 */
	public void upuser_status2(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upuser_status2(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * @author hgx
	 * ��������˻�������Ϊ
	 */
	public void upuser_status3(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upuser_status3(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	
}
