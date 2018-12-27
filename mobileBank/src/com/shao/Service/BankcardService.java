package com.shao.Service;
import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.model.Bankcard;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
import com.shao.model.User;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *业务逻辑接口
 *银行卡操作服务 
 *
 */

public interface BankcardService {

	public Bankcard bcd_query(String card_serial) throws SQLException;
	
	public void addBankCard(String card_serial, String uid, String bclient_id,
			String card_id, String int_serial, String card_ps, String current,
			String death) throws SQLException;
	
	public Bankcard tra_bkd_query(String descard);
	
	
	public void bkdtransfer(String outcard, String descard, double tra_money);
	
	
	public Bankcard query_card_serial(String card_id);
	
	
	public Bankcard query_paycard_serial(String card_id);
	
	
	public Bankcard card_query(String card_id);
	
	
	public Bankcard query_bankcard(String card_id);
	
	public void upbankcard_balance(String card_id, double temp);
	
	
	public Bankcard bkd_cif_query(String tocard_id);
	
	
	public void bkdtransfer_out(String fromcard, String tocard, double firmoney,
			double scdmoney);
}
