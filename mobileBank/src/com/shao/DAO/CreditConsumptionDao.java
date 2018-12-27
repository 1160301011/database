package com.shao.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * @author HGX
 *数据访问层 接口
 *信用卡消费表 
 *
 */
public interface CreditConsumptionDao {
	
	/**
	 * 添加信用卡消费记录
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add_cre_billrecord(String cre_id, double summoeny,String cre_serial) throws ClassNotFoundException, SQLException;
}
