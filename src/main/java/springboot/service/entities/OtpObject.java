package springboot.service.entities;

public class OtpObject {

	public OtpObject(String code, String username, String expire_on) {
		super();
		this.code = code;
		this.username = username;
		this.expire_on = expire_on;
	}
	String code;
	String username;
	String expire_on;//YYYY/MM/DD
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getExpire_on() {
		return expire_on;
	}
	public void setExpire_on(String expire_on) {
		this.expire_on = expire_on;
	}
}
