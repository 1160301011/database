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
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *�˻��� 
 *
 */

public interface AccountDao {
	/**
	 * ��¼����
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public User query(String name,String password) throws SQLException;
	
	/**
	 * ����˻�
	 * @throws SQLException 
	 */
	public void addbank_User(String username, String userpwd) throws SQLException;
	
	
	public void addUser(String name,String password) throws SQLException, ClassNotFoundException;
	
	/**
	 * �����û��˻���Ϣ
	 * @throws SQLException 
	 */
	public void updateAccount(String FromName,String ToName,double userbalance) throws SQLException;

	
	/**
	 * ��ѯ�˻�
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
   public User findUserByName(String name) throws SQLException;
   
   public Bankuser findbank_user_Name(String username) throws SQLException;
	/**
	 * ���������Ϣ
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
   public Clientinfo findClientID(String client_id) throws SQLException;
	/**
	 * ���������Ϣ
	 * @param uid...
	 * @return
	 * @throws SQLException 
	 */
   public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps, String card_balance,
			String current, String death) throws SQLException;
}
