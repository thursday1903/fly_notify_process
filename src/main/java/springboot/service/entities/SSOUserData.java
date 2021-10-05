package springboot.service.entities;

public class SSOUserData {

	private String requestId;
	private String username;
	private String password;
	private String newpassword;
	private Long innit_balance;
	private Long remain_balance;
	private Long holding_balance;
	private Long bonus_bal;
	private Integer require_change_pass;
	private String other_system_auth_user_id;
	private String display_name;
	private Integer login_from;// 0:login pass;1:facebook,2:google
	private String client_identity_str;
	private String email;
	private String phone;
	private String address;
	private String id_number;
	private String session_key;
	private String id_full_name;
	private String birthday;
	private String otp_code;

	long account_epurse_id;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getInnit_balance() {
		return innit_balance;
	}

	public void setInnit_balance(Long innit_balance) {
		this.innit_balance = innit_balance;
	}

	public long getAccount_epurse_id() {
		return account_epurse_id;
	}

	public void setAccount_epurse_id(long account_epurse_id) {
		this.account_epurse_id = account_epurse_id;
	}

	public String getClient_identity_str() {
		return client_identity_str;
	}

	public void setClient_identity_str(String client_identity_str) {
		this.client_identity_str = client_identity_str;
	}

	public int getLogin_from() {
		return login_from;
	}

	public void setLogin_from(int login_from) {
		this.login_from = login_from;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getOther_system_auth_user_id() {
		return other_system_auth_user_id;
	}

	public void setOther_system_auth_user_id(String other_system_auth_user_id) {
		this.other_system_auth_user_id = other_system_auth_user_id;
	}

	public int getRequire_change_pass() {
		return require_change_pass;
	}

	public void setRequire_change_pass(int require_change_pass) {
		this.require_change_pass = require_change_pass;
	}

	public Long getRemain_balance() {
		return remain_balance;
	}

	public void setRemain_balance(Long remain_balance) {
		this.remain_balance = remain_balance;
	}

	public Long getHolding_balance() {
		return holding_balance;
	}

	public void setHolding_balance(Long holding_balance) {
		this.holding_balance = holding_balance;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getOtp_code() {
		return otp_code;
	}

	public void setOtp_code(String otp_code) {
		this.otp_code = otp_code;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getId_full_name() {
		return id_full_name;
	}

	public void setId_full_name(String id_full_name) {
		this.id_full_name = id_full_name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getBonus_bal() {
		return bonus_bal;
	}

	public void setBonus_bal(Long bonus_bal) {
		this.bonus_bal = bonus_bal;
	}

	public void setRequire_change_pass(Integer require_change_pass) {
		this.require_change_pass = require_change_pass;
	}

	public void setLogin_from(Integer login_from) {
		this.login_from = login_from;
	}

}
