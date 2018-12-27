package com.shao.DAO;

import java.sql.SQLException;
/**
 * @author HGX
 *数据访问层 接口
 *储蓄卡消费表 
 *
 */
public interface ConsumptionDao {

	/*
	 * 添加银行卡消费记录表(接口实例化)
	 */
	public void addConsumtion_phoenrecord(String card_serial, double trade_money)
			throws SQLException, ClassNotFoundException;
	
	/*
	 * 插入交通缴费消费记录
	 */
	public void addConsumtion_trarecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException;
	
	
	/*
	 * 插入生活缴费 消费记录
	 */
	public void addConsumtion_liferecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException;
	
	
	
	/*
	 * 插入医保消费记录
	 */
		public void addConsumtion_medirecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException;
		
		
	

}
