package com.shao.Service.impl;

import java.sql.SQLException;

import com.shao.DAO.impl.AccountDaoImpl;
import com.shao.DAO.impl.BankuserDaoImpl;
import com.shao.DAO.impl.TransferDaoImpl;
import com.shao.Service.TransferService;
import com.shao.model.Bankuser;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *ҵ���߼��ӿ�ʵ����
 *ת�˲������� 
 *
 */
public class TransferServiceImpl implements TransferService {
	
	TransferDaoImpl transfer = new TransferDaoImpl();
	BankuserDaoImpl bankuser = new BankuserDaoImpl();
	AccountDaoImpl account = new AccountDaoImpl();
	/*
	 * ��ʼת�˷���  ֻ�漰�� bank_user ��
	 * (non-Javadoc)
	 * @see com.shao.Service.AccountService#transfer(java.lang.String, java.lang.String, double)
	 */
		public void transfer(String FromName, String ToName, double userbalance) {
			try {
				ManagerThreadLocal.startTransaction();
				Bankuser bankuer_from = bankuser.findbank_user_Name(FromName);
				Bankuser bankuer_to = bankuser.findbank_user_Name(ToName);

				bankuer_from.setUserbalance(bankuer_from.getUserbalance()
						- userbalance);
				bankuer_to.setUserbalance(bankuer_to.getUserbalance() + userbalance);

				bankuser.updateAccount(FromName, bankuer_from.getUserbalance());
				bankuser.updateAccount(ToName, bankuer_to.getUserbalance());
				ManagerThreadLocal.commit();
			} catch (SQLException e) {
				ManagerThreadLocal.rollback();
			} finally {
				
				ManagerThreadLocal.close();
			}
		}
		
		
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
				String des_card, String tra_money) throws SQLException, ClassNotFoundException{
			try {
				ManagerThreadLocal.startTransaction();
				transfer.addtra_record(card_serial,client_id, des_card, tra_money);
				System.out.println("ת�˲���1");
				ManagerThreadLocal.commit();
			} catch (SQLException e) {
				ManagerThreadLocal.rollback();
			} finally {
				ManagerThreadLocal.close();
			}
			
		}
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
				String tocard, String formatmoney,double fee) throws ClassNotFoundException {
			try {
				ManagerThreadLocal.startTransaction();
				transfer.addtra_out_record(card_serial,client_id, tocard, formatmoney,fee);
				ManagerThreadLocal.commit();
			} catch (SQLException e) {
				ManagerThreadLocal.rollback();
			} finally {
				ManagerThreadLocal.close();
			}
		}
}
