package springboot.service.entities;

public class SSOHistoryRes {

	private String request_id;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Long getRequest_amount() {
		return request_amount;
	}

	public void setRequest_amount(Long request_amount) {
		this.request_amount = request_amount;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public SSOCardInfo[] getCard_data() {
		return card_data;
	}

	public void setCard_data(SSOCardInfo[] card_data) {
		this.card_data = card_data;
	}

	private String target;
	private Long request_amount;
	private int payment_status;
	private int payment_type;
	private String payment_date;
	private SSOCardInfo[] card_data;
}
