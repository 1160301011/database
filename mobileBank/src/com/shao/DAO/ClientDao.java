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
 * �ͻ���ϢDao�ӿ�
 */
public interface ClientDao {

	/**
	 * @param client_id...uid
	 * @throws SQLException 
	 * ���¿ͻ���Ϣ
	 */
	public void updateClientinfo(String client_id,String uid, String client_name, String client_idcard,
			String client_addr, String client_phone) throws SQLException;
	
	/*
	 * @hgx
	 * ����ͻ�ID���ҿͻ���Ϣ
	 */
		//����ͬ���ͻ�ID �޷����
	public Clientinfo findClientID(String client_id) throws SQLException;
	
	
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * �ͻ���Ϣ��ѯ
	 */
	public Clientinfo cfo_query(String username) throws SQLException;
	
	
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * ��ӿͻ���Ϣ
	 */
	public void addClientinfo2(String username) throws SQLException;
	
	
	/**
	 * �ͻ���Ϣ��ѯ
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Clientinfo cinfo_query(String uid) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
