package com.shao.DAO;

import java.sql.SQLException;

import com.shao.model.Bankcard;
import com.shao.model.Bankuser;
import com.shao.model.User;
/**
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *�û��� 
 *
 */
public interface BankuserDao {

	
	
//	public Bankuser bankuser_check(String username, String userpwd);
//	
//	public User query(String name);
//	
//	public Bankuser bu_query(String username) throws SQLException;
//	
//	public Bankuser bu_queryuid(String uid) throws SQLException;
//	
//	
//	public void update(String username, double userbalance);
//	
//	
//	public User check(String name, String password);
//	
//	public void addbank_User(String username, String userpwd);
//	
//	
//	public void updateUser(String bank_uname, String newpwdField)
//			throws SQLException;
//	
//	
//	public void update_bankuser_balance(String username, double temp2);
//	
//	
//	public void upuser_status(String username);
//	
//	
//	public void Foun_upuser_sta(String username);
//	
//	
//	public void upuser_status2(String username);
//	
//	public void upuser_status3(String username);
	

  public void addUser(String name, String password) throws SQLException;
  
  
  /*
	 * @hgx
	 * �����˻����
	 */
	public void updateAccount(String username,double userbalance) throws SQLException;
	
	/**
	 * @param username...userpwd
	 * @throws SQLException 
	 * @return
	 * �˻���Ϣ��ѯ
	 */
	public Bankuser findbank_userquery(String username,String userpwd) throws SQLException;
	
	/**
	 * @param username
	 * @throws SQLException 
	 * @return
	 * ��ѯ�˻���Ϣ
	 */
	public Bankuser findbank_user_Name(String username) throws SQLException;
	
	/**
	 * �û���Ϣ��ѯ
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Bankuser findbank_user_uid(String uid) throws SQLException;
	
	/**
	 * ����û���Ϣ
	 */
	public void addbank_User(String username, String userpwd) throws SQLException;
	

	/**
	 * �����û�����
	 * @param bank_uname
	 * @param newpwdField
	 * @throws SQLException
	 */
	public void updatebank_User(String bank_uname, String newpwdField) throws SQLException;
	
	/*�����Ϣ��ѯ
	 * ת���û���ѯ
	 * ����ѯ  bankcard & bank_user
	 */
	public Bankcard findbkd_Descard(String card_id) throws SQLException;
	
	/*
	 * �����˻����
	 */
	public void updatebankuser_balance(String username, double temp2) throws SQLException;
	
	
	/**
	 * �����û�״̬
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status(String username) throws SQLException;
	
	/**
	 * ���������ʧ�һ�
	 * �����˻�״̬
	 * @param username
	 * @throws SQLException
	 */
	public void Foun_upuser_sta(String username) throws SQLException;
	
	/**
	 * �����˻�״̬
	 * ���ƽ��׹���
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status2(String username) throws SQLException;
	
	/**
	 * �����˻�״̬
	 * ������ƽ��׹���
	 * @param username
	 * @throws SQLException
	 */
	public void upuser_status3(String username) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  


}
