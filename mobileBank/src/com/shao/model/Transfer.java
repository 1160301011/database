package com.shao.model;
/**
 * @author HGX
 *实体类
 *转账记录表 
 *
 */
public class Transfer {
	
	private String trade_id;
    private String card_serial;
    private String client_id;
    private String int_out;
    private String to_cardid;
    private String trade_time;
    public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	public String getCard_serial() {
		return card_serial;
	}
	public void setCard_serial(String card_serial) {
		this.card_serial = card_serial;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getInt_out() {
		return int_out;
	}
	public void setInt_out(String int_out) {
		this.int_out = int_out;
	}
	public String getTo_cardid() {
		return to_cardid;
	}
	public void setTo_cardid(String to_cardid) {
		this.to_cardid = to_cardid;
	}
	public String getTrade_time() {
		return trade_time;
	}
	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}
	public String getTrade_money() {
		return trade_money;
	}
	public void setTrade_money(String trade_money) {
		this.trade_money = trade_money;
	}
	public double getTrade_fee() {
		return trade_fee;
	}
	public void setTrade_fee(double trade_fee) {
		this.trade_fee = trade_fee;
	}
	public String getTrade_remark() {
		return trade_remark;
	}
	public void setTrade_remark(String trade_remark) {
		this.trade_remark = trade_remark;
	}
	private String trade_money;
    private double trade_fee;
    private String trade_remark;
    
}
