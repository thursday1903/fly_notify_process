package springboot.service.entities;

import springboot.utils.GsonUltilities;

/**
 * @author vietanh Message truyền nhận trong hệ thông SSO
 */
public class RequestResponseMsg {
	@Override
	public String toString() {
		return "RequestResponseMsg [p_code=" + p_code + ", data=" + data + ", response_data=" + response_data
				+ ", response_identify=" + response_identify + ", signature=" + signature + ", service_code="
				+ service_code + ", client_type=" + client_type + ", client_version=" + client_version + ", status="
				+ status + ", response_msg=" + response_msg + ", mac_address=" + mac_address + ", total_amount="
				+ total_amount + ", total_quantity=" + total_quantity + ", balBefore=" + balBefore + ", balAfter="
				+ balAfter + ", holdingBalBefore=" + holdingBalBefore + ", holdingBalAfter=" + holdingBalAfter + "]";
	}

	String client_request_id;// Mã gd từ client gửi lên
	int p_code;
	String data;
	String response_data;
	String response_identify;
	public String getClient_request_id() {
		return client_request_id;
	}

	public void setClient_request_id(String client_request_id) {
		this.client_request_id = client_request_id;
	}

	String signature;
	String service_code;// : dạng guid
	int client_type;
	// quy định client thuộc loại gì:
	// 1: client master(dịch vụ chủ)
	// 2: client của dịch vụ master dạng: web site
	// 3: client của dịch vụ master dạng: android app
	// 4: client của dịch vụ master dạng: ios app
	// 5: client của dịch vụ master dạng: winfone
	String client_version = "";// version của client.
	int status;
	String response_msg = "";
	String mac_address;
	String total_amount;
	String total_quantity;
	long balBefore;
	long balAfter;
	long holdingBalBefore;
	long holdingBalAfter;


	public long getBalBefore() {
		return balBefore;
	}

	public void setBalBefore(long balBefore) {
		this.balBefore = balBefore;
	}

	public long getBalAfter() {
		return balAfter;
	}

	public void setBalAfter(long balAfter) {
		this.balAfter = balAfter;
	}

	public long getHoldingBalBefore() {
		return holdingBalBefore;
	}

	public void setHoldingBalBefore(long holdingBalBefore) {
		this.holdingBalBefore = holdingBalBefore;
	}

	public long getHoldingBalAfter() {
		return holdingBalAfter;
	}

	public void setHoldingBalAfter(long holdingBalAfter) {
		this.holdingBalAfter = holdingBalAfter;
	}

	public String getResponse_identify() {
		return response_identify;
	}

	public void setResponse_identify(String response_identify) {
		this.response_identify = response_identify;
	}

	public String getResponse_data() {
		return response_data;
	}

	public void setResponse_data(String response_data) {
		this.response_data = response_data;
	}

	// = hexString = tripleDes(data)

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(String total_quantity) {
		this.total_quantity = total_quantity;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public int getP_code() {
		return p_code;
	}

	public void setP_code(int p_code) {
		this.p_code = p_code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getClient_version() {
		return client_version;
	}

	public int getClient_type() {
		return client_type;
	}

	public void setClient_type(int client_type) {
		this.client_type = client_type;
	}

	public void setClient_version(String client_version) {
		this.client_version = client_version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResponse_msg() {
		return response_msg;
	}

	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}

	public static void main(String[] args) {
		RequestResponseMsg requestResponseMsg = new RequestResponseMsg(1000, "dataHexhere", "signature", "123k23ui34",
				1, "1.0.1");
		System.out.println(GsonUltilities.toJson(requestResponseMsg));

	}

	public RequestResponseMsg(int p_code, String data, String signature, String service_code, int client_type,
			String client_version) {
		super();
		this.p_code = p_code;
		this.data = data;
		this.signature = signature;
		this.service_code = service_code;
		this.client_type = client_type;
		this.client_version = client_version;
	}
}
