package com.shao.DAO;

import java.sql.SQLException;
/**
 * @author HGX
 *数据访问层 接口
 *取款记录表 
 *
 */
public interface WithdrawalDao {

	/**
	 * 添加取款记录
	 * @param card_serial
	 * @param withmoney
	 * @param interest
	 * @param totalmoney
	 * @param temp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addinsert_WITHDRAWAL(String card_serial, String withmoney,
			String interest, String totalmoney, double temp) throws ClassNotFoundException, SQLException;
	
	
}
