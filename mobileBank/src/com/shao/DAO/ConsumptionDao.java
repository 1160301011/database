package com.shao.DAO;

import java.sql.SQLException;
/**
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *������ѱ� 
 *
 */
public interface ConsumptionDao {

	/*
	 * ������п����Ѽ�¼��(�ӿ�ʵ����)
	 */
	public void addConsumtion_phoenrecord(String card_serial, double trade_money)
			throws SQLException, ClassNotFoundException;
	
	/*
	 * ���뽻ͨ�ɷ����Ѽ�¼
	 */
	public void addConsumtion_trarecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException;
	
	
	/*
	 * ��������ɷ� ���Ѽ�¼
	 */
	public void addConsumtion_liferecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException;
	
	
	
	/*
	 * ����ҽ�����Ѽ�¼
	 */
		public void addConsumtion_medirecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException;
		
		
	

}
