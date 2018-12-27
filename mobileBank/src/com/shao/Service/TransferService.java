package com.shao.Service;

import java.sql.SQLException;
/**
 * @author HGX
 *业务逻辑接口
 *转账操作服务 
 *
 */

public interface TransferService {
	
	
	
	/**
	 * 添加转账信息表
	 * @param card_serial
	 * @param client_id
	 * @param tocard
	 * @param formatmoney
	 * @param fee
	 * @throws ClassNotFoundException
	 */
	public void addtra_out_record(String card_serial, String client_id,
			String tocard, String formatmoney,double fee) throws ClassNotFoundException;

	/**
	 * 添加转账交易表信息
	 * @param card_serial
	 * @param client_id
	 * @param des_card
	 * @param tra_money
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void addtra_record(String card_serial, String client_id,
			String des_card, String tra_money) throws SQLException, ClassNotFoundException;
	
	/*
	 * 初始转账方法  只涉及到 bank_user 表
	 * (non-Javadoc)
	 * @see com.shao.Service.AccountService#transfer(java.lang.String, java.lang.String, double)
	 */
		public void transfer(String FromName, String ToName, double userbalance);
}
