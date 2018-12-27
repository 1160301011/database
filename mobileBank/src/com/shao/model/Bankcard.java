package com.shao.model;
/**
 * @author HGX
 *实体类
 *储蓄卡表 
 *
 */
public class Bankcard {
	private String card_serial;
	private String uid;
	private String client_id;
	private String bank_id;
    private String int_serial;
    public String getCard_serial() {
		return card_serial;
	}
	public void setCard_serial(String card_serial) {
		this.card_serial = card_serial;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getInt_serial() {
		return int_serial;
	}
	public void setInt_serial(String int_serial) {
		this.int_serial = int_serial;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getCard_ps() {
		return card_ps;
	}
	public void setCard_ps(String card_ps) {
		this.card_ps = card_ps;
	}
	public String getCard_status() {
		return card_status;
	}
	public void setCard_status(String card_status) {
		this.card_status = card_status;
	}
	public double getCard_balance() {
		return card_balance;
	}
	public void setCard_balance(double card_balance) {
		this.card_balance = card_balance;
	}
	public String getCard_createtime() {
		return card_createtime;
	}
	public void setCard_createtime(String card_createtime) {
		this.card_createtime = card_createtime;
	}
	public String getClosepay() {
		return closepay;
	}
	public void setClosepay(String closepay) {
		this.closepay = closepay;
	}
	public double getCurrent() {
		return current;
	}
	public void setCurrent(double current) {
		this.current = current;
	}
	public double getDeath() {
		return death;
	}
	public void setDeath(double death) {
		this.death = death;
	}
	private String card_id;
    private String card_ps;
    private String card_status;
    private double card_balance;
    private String card_createtime;
    private String closepay;
    private double current;
    private double death;
}
