package com.shao.DAO;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.model.Payee;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *数据访问层 接口
 *收款人表 
 *
 */
public interface PayeeDao {

	

	/**
	 * @param payee_card
	 * @throws SQLException 
	 * @return
	 * 查询收款人表信息
	 */
	public Payee pye_findByPayee_info(String payee_card) throws SQLException;
	
	/**
	 * @param payee_uid 
	 * @throws SQLException 
	 * 
	 * 添加收款人表信息
	 */
	public void addpayinfo(String payee_uid,String payee_name, String payee_card, String belong_bank) throws SQLException;
}
