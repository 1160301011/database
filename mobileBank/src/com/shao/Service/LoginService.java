package com.shao.Service;

import java.sql.SQLException;

import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
/**
 * @author HGX
 *ҵ���߼��ӿ�
 *��¼�������� 
 *
 */
public interface LoginService {

	
	public Clientinfo cfo_check(String username) throws SQLException;
	
	

	/**
	 * ��ѯ�û��˺�����
	 */
	public Bankuser bankuser_check(String username, String userpwd);
	
	

	/**
	 * �û���Ϣ��ѯ
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public Bankuser bu_query(String username) throws SQLException;
	
	
	/*
	 * �˺Ź�ʧ����
	 */
	public void upuser_status(String username);
	
	
	
	
}
