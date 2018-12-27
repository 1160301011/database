package com.shao.model;
/**
 * @author HGX
 *实体类
 *信用卡表 
 *
 */
public class Creditcard {
	private String cre_serial;
	private String client_id;
	private String bank_id;
	private String uid;
    private String cre_pwd;
    private double cre_balance;
	private double cre_available;
	private String return_date;
	public String getCre_serial() {
		return cre_serial;
	}
	public void setCre_serial(String cre_serial) {
		this.cre_serial = cre_serial;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCre_pwd() {
		return cre_pwd;
	}
	public void setCre_pwd(String cre_pwd) {
		this.cre_pwd = cre_pwd;
	}
	public double getCre_balance() {
		return cre_balance;
	}
	public void setCre_balance(double cre_balance) {
		this.cre_balance = cre_balance;
	}
	public double getCre_available() {
		return cre_available;
	}
	public void setCre_available(double cre_available) {
		this.cre_available = cre_available;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getReturn_card() {
		return return_card;
	}
	public void setReturn_card(String return_card) {
		this.return_card = return_card;
	}
	public double getThis_month_money() {
		return this_month_money;
	}
	public void setThis_month_money(double this_month_money) {
		this.this_month_money = this_month_money;
	}
	public double getHis_month_money() {
		return his_month_money;
	}
	public void setHis_month_money(double his_month_money) {
		this.his_month_money = his_month_money;
	}
	public String getCre_status() {
		return cre_status;
	}
	public void setCre_status(String cre_status) {
		this.cre_status = cre_status;
	}
	public String getCre_auto() {
		return cre_auto;
	}
	public void setCre_auto(String cre_auto) {
		this.cre_auto = cre_auto;
	}
	public String getCre_closepay() {
		return cre_closepay;
	}
	public void setCre_closepay(String cre_closepay) {
		this.cre_closepay = cre_closepay;
	}
	public String getCre_id() {
		return cre_id;
	}
	public void setCre_id(String cre_id) {
		this.cre_id = cre_id;
	}
	private String return_card;
    private double this_month_money;
    private double his_month_money;
	private String cre_status;
	private String cre_auto;
    private String cre_closepay;
    private String cre_id;
    
}
