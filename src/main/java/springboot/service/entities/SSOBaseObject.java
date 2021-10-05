package springboot.service.entities;

public class SSOBaseObject {

	long account_epurse_id;
	String acc_identify;
	String display_name;
	String acc_pwd;
	String cell_phone;
	String acc_email;
	int login_from;// 0:login user&pass;1:login fb,2: login google
	String birthday;// 19000101
	String id_card_no;
	String id_card_name;
	int acc_status;// 1: dang hoat dong
	String address;
	int receive_notify;
	String session_key;
	private String client_identity_str;

	public String getClient_identity_str() {
		return client_identity_str;
	}

	public void setClient_identity_str(String client_identity_str) {
		this.client_identity_str = client_identity_str;
	}

	public long getAccount_epurse_id() {
		return account_epurse_id;
	}

	public void setAccount_epurse_id(long account_epurse_id) {
		this.account_epurse_id = account_epurse_id;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public int getReceive_notify() {
		return receive_notify;
	}

	public void setReceive_notify(int receive_notify) {
		this.receive_notify = receive_notify;
	}

	public long getAccount_id() {
		return account_epurse_id;
	}

	public void setAccount_id(long account_id) {
		this.account_epurse_id = account_id;
	}

	public String getAcc_identify() {
		return acc_identify;
	}

	public void setAcc_identify(String acc_identify) {
		this.acc_identify = acc_identify;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getAcc_pwd() {
		return acc_pwd;
	}

	public void setAcc_pwd(String acc_pwd) {
		this.acc_pwd = acc_pwd;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getAcc_email() {
		return acc_email;
	}

	public void setAcc_email(String acc_email) {
		this.acc_email = acc_email;
	}

	public int getLogin_from() {
		return login_from;
	}

	public void setLogin_from(int login_from) {
		this.login_from = login_from;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getId_card_no() {
		return id_card_no;
	}

	public void setId_card_no(String id_card_no) {
		this.id_card_no = id_card_no;
	}

	public String getId_card_name() {
		return id_card_name;
	}

	public void setId_card_name(String id_card_name) {
		this.id_card_name = id_card_name;
	}

	public int getAcc_status() {
		return acc_status;
	}

	public void setAcc_status(int acc_status) {
		this.acc_status = acc_status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
