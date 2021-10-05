package springboot.service.entities;

import springboot.hbn.entities.TblBalChangeLog;

/**
 * Object input card de Sim agent dequeue xu ly
 * 
 * @author vietanh
 *
 */
public class SystemPaymentData extends TblBalChangeLog {

	public String getClient_request_id() {
		return client_request_id;
	}

	public void setClient_request_id(String client_request_id) {
		this.client_request_id = client_request_id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String client_request_id;// Mã gd từ client gửi lên
	String session_key;
	String customer_name;
	String request_id;
	String va_number;
	String va_display_name;
	int money_active;// So tien user được dịch vụ cộng hạn mức đẻ có thể thanh toán vượt số dư
	private String client_identity_str;

	public String getClient_identity_str() {
		return client_identity_str;
	}

	public void setClient_identity_str(String client_identity_str) {
		this.client_identity_str = client_identity_str;
	}

	public int getMoney_active() {
		return money_active;
	}

	public void setMoney_active(int money_active) {
		this.money_active = money_active;
	}

	public String getVa_display_name() {
		return va_display_name;
	}

	public void setVa_display_name(String va_display_name) {
		this.va_display_name = va_display_name;
	}

	public String getVa_number() {
		return va_number;
	}

	public void setVa_number(String va_number) {
		this.va_number = va_number;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

}
