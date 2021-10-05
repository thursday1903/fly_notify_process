package springboot.service.entities;

public class FirmResponse {

	private int ResponseCode;
	private String ResponseMessage;

	private String BankNo;
	private String AccNo;
	private String AccType;
	private String AccName;
	private String Signature;

	public String getResponseMessage() {
		return ResponseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		ResponseMessage = responseMessage;
	}

	public String getBankNo() {
		return BankNo;
	}

	public void setBankNo(String bankNo) {
		BankNo = bankNo;
	}

	public String getAccNo() {
		return AccNo;
	}

	public void setAccNo(String accNo) {
		AccNo = accNo;
	}

	public String getAccType() {
		return AccType;
	}

	public void setAccType(String accType) {
		AccType = accType;
	}

	public String getAccName() {
		return AccName;
	}

	public void setAccName(String accName) {
		AccName = accName;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public int getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(int responseCode) {
		ResponseCode = responseCode;
	}

	private String RequestId;// Mã giao dịch
	private String Qfrom;
	private String Qto;
	private String Data;
	private String Balance;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getQfrom() {
		return Qfrom;
	}

	public void setQfrom(String qfrom) {
		Qfrom = qfrom;
	}

	public String getQto() {
		return Qto;
	}

	public void setQto(String qto) {
		Qto = qto;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}
}
