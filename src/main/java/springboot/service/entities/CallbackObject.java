package springboot.service.entities;

public class CallbackObject {

	public CallbackObject(String finalStatus, String message, String cardAmount, String requestId) {
		super();
		this.finalStatus = finalStatus;
		this.message = message;
		this.cardAmount = cardAmount;
		this.requestId = requestId;
	}

	String finalStatus;
	String message;
	String cardAmount;
	String requestId;

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(String cardAmount) {
		this.cardAmount = cardAmount;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
