package com.shao.DAO.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.shao.DAO.CreditConsumptionDao;


/**
 * 
 * @author hgx
 *信用卡消费表
 *数据层接口实例
 */
public class CreditConsumptionDaoImpl implements CreditConsumptionDao {


	/**
	 * 添加信用卡消费记录
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add_cre_billrecord(String cre_id, double summoeny,String cre_serial) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "ty199785";
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
//		String cre = cre_id.toString();
//		System.out.println(cre);
		
		Statement stat = (Statement) con.createStatement();
		String Cre_consumption = "insert into CREDIT_CONSUMPTION (crecon_serial,crecon_money,cre_id) values(CONCAT('c101',ceiling(rand()*100000000+100000000)),"+summoeny+","+cre_id+")";
		stat.executeUpdate(Cre_consumption);
		
//		String upCre_consumption="update CREDIT_CONSUMPTION set cre_serial = "+cre_serial+" where cre_id ="+cre_id+"";
//		stat.executeUpdate(upCre_consumption);
		System.out.println("成功插入信用卡消费记录");
		stat.close();
		con.close();
	}
}
