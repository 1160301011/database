package com.shao.model;
/**
 * @author HGX
 *实体类
 *客户信息表 
 *
 */
public class Clientinfo {

		private String client_id;
	    private String uid;
	    private String client_name;
	    private String client_idcard;
	    private String client_phone;
	    private String addr; 
	   
		public Clientinfo() {
			super();
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getClient_name() {
			return client_name;
		}

		public void setClient_name(String client_name) {
			this.client_name = client_name;
		}

		public String getClient_phone() {
			return client_phone;
		}

		public void setClient_phone(String client_phone) {
			this.client_phone = client_phone;
		}

		public String getClient_id() {
			return client_id;
		}

		public void setClient_id(String client_id) {
			this.client_id = client_id;
		}

		public String getClient_idcard() {
			return client_idcard;
		}

		public void setClient_idcard(String client_idcard) {
			this.client_idcard = client_idcard;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

}
