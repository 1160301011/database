package com.shao.DAO.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.shao.DAO.WithdrawalDao;


/**
 * 
 * @author hgx
 *取款表
 *数据层接口实例
 */
public class WithdrawalDaoImpl implements WithdrawalDao {
	/**
	 * 添加取款记录
	 * 
	 * @param card_serial
	 * @param withmoney
	 * @param interest
	 * @param totalmoney
	 * @param temp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addinsert_WITHDRAWAL(String card_serial, String withmoney,
			String interest, String totalmoney, double temp)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,
				username, password);

		Statement stat = (Statement) con.createStatement();
		String insert_wit = "insert into WITHDRAWAL (wid_num,card_serial,wid_pre_money,wid_interest,wid_get_money,card_balance)"
				+ "values(CONCAT('w001',ceiling(rand()*10000+10000)),"
				+ card_serial
				+ ","
				+ withmoney
				+ ","
				+ interest
				+ ","
				+ totalmoney + "," + temp + ")";
		stat.executeUpdate(insert_wit);

		System.out.println("取款记录表插入成功");

		stat.close();
		con.close();

	}

}
