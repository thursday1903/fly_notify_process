// default package
// Generated Sep 8, 2020 6:03:30 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "USERS")
public class Users implements java.io.Serializable {

	private long id;
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String phone;
	private long gender;
	private long partnerId;
	private long userType;
	private BigDecimal userBalance;
	private Date latestUpdateBal;
	private BigDecimal status;
	private int userStatus;
	private Date createdDate;

	public Users() {
	}

	public Users(long id, String username, String password, String email, String firstname, String lastname,
			String phone, long gender, long partnerId, long userType, BigDecimal userBalance, int userStatus) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.gender = gender;
		this.partnerId = partnerId;
		this.userType = userType;
		this.userBalance = userBalance;
		this.userStatus = userStatus;
	}

	public Users(long id, String username, String password, String email, String firstname, String lastname,
			String phone, long gender, long partnerId, long userType, BigDecimal userBalance, Date latestUpdateBal,
			BigDecimal status, int userStatus, Date createdDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.gender = gender;
		this.partnerId = partnerId;
		this.userType = userType;
		this.userBalance = userBalance;
		this.latestUpdateBal = latestUpdateBal;
		this.status = status;
		this.userStatus = userStatus;
		this.createdDate = createdDate;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "USERNAME", nullable = false)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FIRSTNAME", nullable = false)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "LASTNAME", nullable = false)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "PHONE", nullable = false)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "GENDER", nullable = false, precision = 10, scale = 0)
	public long getGender() {
		return this.gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	@Column(name = "PARTNER_ID", nullable = false, precision = 10, scale = 0)
	public long getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	@Column(name = "USER_TYPE", nullable = false, precision = 10, scale = 0)
	public long getUserType() {
		return this.userType;
	}

	public void setUserType(long userType) {
		this.userType = userType;
	}

	@Column(name = "USER_BALANCE", nullable = false, precision = 0)
	public BigDecimal getUserBalance() {
		return this.userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LATEST_UPDATE_BAL", length = 7)
	public Date getLatestUpdateBal() {
		return this.latestUpdateBal;
	}

	public void setLatestUpdateBal(Date latestUpdateBal) {
		this.latestUpdateBal = latestUpdateBal;
	}

	@Column(name = "STATUS", precision = 0)
	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Column(name = "USER_STATUS", nullable = false, precision = 8, scale = 0)
	public int getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
