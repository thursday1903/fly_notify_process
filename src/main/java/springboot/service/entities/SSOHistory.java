package springboot.service.entities;

public class SSOHistory extends SSOUserData {
	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	String user_name;
	String request_id;
	String from_date;
	String to_date;
	int transaction_status = 200;
	public int getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(int transaction_status) {
		this.transaction_status = transaction_status;
	}

	int payment_type;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}
}
