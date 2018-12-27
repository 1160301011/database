package com.shao.Service;

import java.sql.SQLException;
/**
 * @author HGX
 *ҵ���߼��ӿ�
 *ת�˲������� 
 *
 */

public interface TransferService {
	
	
	
	/**
	 * ���ת����Ϣ��
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
	 * ���ת�˽��ױ���Ϣ
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
	 * ��ʼת�˷���  ֻ�漰�� bank_user ��
	 * (non-Javadoc)
	 * @see com.shao.Service.AccountService#transfer(java.lang.String, java.lang.String, double)
	 */
		public void transfer(String FromName, String ToName, double userbalance);
}
