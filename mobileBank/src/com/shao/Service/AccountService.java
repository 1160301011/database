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
 * @author HGX
 *ҵ���߼��ӿ�
 *�˻��������� 
 *
 */
public interface AccountService {

	/**
	 * ע���˻�
	 */
	public void addbank_User(String username, String userpwd);

	/**
	 * ע���û�
	 */
	public void addUser(String name, String passsword);

	/**
	 * ��¼��֤����ѯ
	 */
	public User check(String name, String password);

	/**
	 * ת��
	 */
	public void transfer(String FromName, String ToName, double balance);

	/**
	 * ���
	 */
	public void update(String name, double balance);

	/**
	 * ��ѯ�û���Ϣ
	 */

	public User query(String name);

	/**
	 * ��ѯ�ͻ�ID
	 */
	public Clientinfo cquery(String client_id);

	/**
	 * ��Ӱ����п���Ϣ
	 * @throws SQLException 
	 */
	public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps,String current, String death) throws SQLException;

}
