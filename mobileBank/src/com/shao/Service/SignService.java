package com.shao.Service;

import java.sql.SQLException;

import com.shao.model.Bankuser;

/**
 * @author HGX
 *ҵ���߼��ӿ�
 *ע���˻��������� 
 *
 */
public interface SignService {

	public Bankuser bu_query(String username) throws SQLException;
	
	
	public void addUser(String name, String password);
	
	
	public void addClientinfo2(String username);
	
	
	
	
	
	
}
