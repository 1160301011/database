package com.shao.DAO.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.DAO.PayeeDao;
import com.shao.model.Payee;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * �տ��˱�
 * @author Administrator
 *
 */
public class PayeeDaoImpl implements PayeeDao {

	
	/**
	 * @param payee_card
	 * @throws SQLException 
	 * @return
	 * ��ѯ�տ��˱���Ϣ
	 */
	public Payee pye_findByPayee_info(String payee_card) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from payee where payee_card = ?",new BeanHandler<Payee>(Payee.class), payee_card);
	}
	
	/**
	 * @param payee_uid 
	 * @throws SQLException 
	 * 
	 * ����տ��˱���Ϣ
	 */
	public void addpayinfo(String payee_uid,String payee_name, String payee_card, String belong_bank) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into payee (uid,payee_name,payee_card,belong_bank) values (?,?,?,?)",
				payee_uid,payee_name,payee_card,belong_bank);
	}
	
	
}
