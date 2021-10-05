package springboot.service.entities;



public class VARegisterSucessObj {

	Integer responseCode;
	String responseMessage;
	VARegisterInnerObj[] data;
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public VARegisterInnerObj[] getData() {
		return data;
	}
	public void setData(VARegisterInnerObj[] data) {
		this.data = data;
	}
}


