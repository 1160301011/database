package com.shao.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *���ÿ����ѱ� 
 *
 */
public interface CreditConsumptionDao {
	
	/**
	 * ������ÿ����Ѽ�¼
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add_cre_billrecord(String cre_id, double summoeny,String cre_serial) throws ClassNotFoundException, SQLException;
}
