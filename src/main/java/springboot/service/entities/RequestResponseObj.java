package springboot.service.entities;

public class RequestResponseObj {

	public RequestResponseObj() {
		// TODO Auto-generated constructor stub
	}

	private int partnerId;
	private String partner_username;

	public String getPartner_username() {
		return partner_username;
	}

	public void setPartner_username(String partner_username) {
		this.partner_username = partner_username;
	}

	private String cardPin;
	private String cardSerial;
	private String requestId;
	private String receiveDate;
	private String remark;
	private String providerCode;

	private String status;
	private String cardAmount;
	private int cardPrintAmount;// Menh gia the duoc in tren the cứng.
	private String reqIp;
	private String subPartner_username;
	private String subPartnerId;

	public String getSubPartner_username() {
		return subPartner_username;
	}

	public void setSubPartner_username(String subPartner_username) {
		this.subPartner_username = subPartner_username;
	}

	public String getSubPartnerId() {
		return subPartnerId;
	}

	public void setSubPartnerId(String subPartnerId) {
		this.subPartnerId = subPartnerId;
	}

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	public int getCardPrintAmount() {
		return cardPrintAmount;
	}

	public void setCardPrintAmount(int cardPrintAmount) {
		this.cardPrintAmount = cardPrintAmount;
	}

	private QueryResult queryResult;

	public QueryResult getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}

	public String getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(String cardAmount) {
		this.cardAmount = cardAmount;
	}

	private String message;

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getCardPin() {
		return cardPin;
	}

	public void setCardPin(String cardPin) {
		this.cardPin = cardPin;
	}

	public String getCardSerial() {
		return cardSerial;
	}

	public void setCardSerial(String cardSerial) {
		this.cardSerial = cardSerial;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
