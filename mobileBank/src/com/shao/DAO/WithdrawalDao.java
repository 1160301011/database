package com.shao.DAO;

import java.sql.SQLException;
/**
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *ȡ���¼�� 
 *
 */
public interface WithdrawalDao {

	/**
	 * ���ȡ���¼
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
