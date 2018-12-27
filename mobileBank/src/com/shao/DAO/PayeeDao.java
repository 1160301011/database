package com.shao.DAO;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.model.Payee;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *�տ��˱� 
 *
 */
public interface PayeeDao {

	

	/**
	 * @param payee_card
	 * @throws SQLException 
	 * @return
	 * ��ѯ�տ��˱���Ϣ
	 */
	public Payee pye_findByPayee_info(String payee_card) throws SQLException;
	
	/**
	 * @param payee_uid 
	 * @throws SQLException 
	 * 
	 * ����տ��˱���Ϣ
	 */
	public void addpayinfo(String payee_uid,String payee_name, String payee_card, String belong_bank) throws SQLException;
}
