package springboot.service.entities;

public class GatewayRequest {
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getInputAmount() {
		return inputAmount;
	}
	public void setInputAmount(String inputAmount) {
		this.inputAmount = inputAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTargetPhone() {
		return targetPhone;
	}
	public void setTargetPhone(String targetPhone) {
		this.targetPhone = targetPhone;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getTelcoMessage() {
		return telcoMessage;
	}
	public void setTelcoMessage(String telcoMessage) {
		this.telcoMessage = telcoMessage;
	}
	public String getTelcoStatus() {
		return telcoStatus;
	}
	public void setTelcoStatus(String telcoStatus) {
		this.telcoStatus = telcoStatus;
	}
	public GatewayRequest(String transId, String serial, String pin, String inputAmount, String amount,
			String targetPhone, String result, String finalAmount, String provider, String telcoMessage,
			String telcoStatus) {
		super();
		this.transId = transId;
		this.serial = serial;
		this.pin = pin;
		this.inputAmount = inputAmount;
		this.amount = amount;
		this.targetPhone = targetPhone;
		this.result = result;
		this.finalAmount = finalAmount;
		this.provider = provider;
		this.telcoMessage = telcoMessage;
		this.telcoStatus = telcoStatus;
	}

	String transId;//M?? giao d???ch
	String serial;//Serial th???
	String pin;//M?? th???
	String inputAmount;// So tien nguoi dung nhap vao tr??n client
	String amount;// So tien can nap
	String targetPhone;// So dt can nap tien
	String result;// Trang thai giao dich
	String finalAmount;// Menh gia thuc cua the, n???u charge th??nh c??ng s??? tr??? v??? con case kh??c th?? ?????u b???ng 0
	String provider;//M?? nh?? cung c???p
	String telcoMessage;//Message telco tr??? v???: ex: n???p th??? th??nh c??ng, n???p th??? th???t b???i, th??? ???? s??? d???ng, bla
	String telcoStatus;//Tr???ng th??i telco tr??? v???, n???u c??.
}
