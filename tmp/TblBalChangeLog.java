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
 * TblBalChangeLog generated by hbm2java
 */
@Entity
@Table(name = "TBL_BAL_CHANGE_LOG")
public class TblBalChangeLog implements java.io.Serializable {

	private BigDecimal balanceLogId;
	private int balChangeType;
	private int balChangeStatus;
	private Integer balChangeAmount;
	private BigDecimal balBef;
	private BigDecimal balAf;
	private Date changeDate;
	private Date latestChange;
	private String acceptBy;
	private String createdBy;
	private String requestFromIp;
	private String remark;
	private String targetAccount;
	private Integer listBillType;
	private Integer previousStt;
	private Date authAt;
	private BigDecimal accId;
	private String requestIdentity;
	private String clientReqId;
	private BigDecimal serviceId;

	public TblBalChangeLog() {
	}

	public TblBalChangeLog(BigDecimal balanceLogId, int balChangeType, int balChangeStatus, Date changeDate) {
		this.balanceLogId = balanceLogId;
		this.balChangeType = balChangeType;
		this.balChangeStatus = balChangeStatus;
		this.changeDate = changeDate;
	}

	public TblBalChangeLog(BigDecimal balanceLogId, int balChangeType, int balChangeStatus, Integer balChangeAmount,
			BigDecimal balBef, BigDecimal balAf, Date changeDate, Date latestChange, String acceptBy, String createdBy,
			String requestFromIp, String remark, String targetAccount, Integer listBillType, Integer previousStt,
			Date authAt, BigDecimal accId, String requestIdentity, String clientReqId, BigDecimal serviceId) {
		this.balanceLogId = balanceLogId;
		this.balChangeType = balChangeType;
		this.balChangeStatus = balChangeStatus;
		this.balChangeAmount = balChangeAmount;
		this.balBef = balBef;
		this.balAf = balAf;
		this.changeDate = changeDate;
		this.latestChange = latestChange;
		this.acceptBy = acceptBy;
		this.createdBy = createdBy;
		this.requestFromIp = requestFromIp;
		this.remark = remark;
		this.targetAccount = targetAccount;
		this.listBillType = listBillType;
		this.previousStt = previousStt;
		this.authAt = authAt;
		this.accId = accId;
		this.requestIdentity = requestIdentity;
		this.clientReqId = clientReqId;
		this.serviceId = serviceId;
	}

	@Id

	@Column(name = "BALANCE_LOG_ID", unique = true, nullable = false, precision = 0)
	public BigDecimal getBalanceLogId() {
		return this.balanceLogId;
	}

	public void setBalanceLogId(BigDecimal balanceLogId) {
		this.balanceLogId = balanceLogId;
	}

	@Column(name = "BAL_CHANGE_TYPE", nullable = false, precision = 8, scale = 0)
	public int getBalChangeType() {
		return this.balChangeType;
	}

	public void setBalChangeType(int balChangeType) {
		this.balChangeType = balChangeType;
	}

	@Column(name = "BAL_CHANGE_STATUS", nullable = false, precision = 8, scale = 0)
	public int getBalChangeStatus() {
		return this.balChangeStatus;
	}

	public void setBalChangeStatus(int balChangeStatus) {
		this.balChangeStatus = balChangeStatus;
	}

	@Column(name = "BAL_CHANGE_AMOUNT", precision = 8, scale = 0)
	public Integer getBalChangeAmount() {
		return this.balChangeAmount;
	}

	public void setBalChangeAmount(Integer balChangeAmount) {
		this.balChangeAmount = balChangeAmount;
	}

	@Column(name = "BAL_BEF", precision = 0)
	public BigDecimal getBalBef() {
		return this.balBef;
	}

	public void setBalBef(BigDecimal balBef) {
		this.balBef = balBef;
	}

	@Column(name = "BAL_AF", precision = 0)
	public BigDecimal getBalAf() {
		return this.balAf;
	}

	public void setBalAf(BigDecimal balAf) {
		this.balAf = balAf;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHANGE_DATE", nullable = false, length = 7)
	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LATEST_CHANGE", length = 7)
	public Date getLatestChange() {
		return this.latestChange;
	}

	public void setLatestChange(Date latestChange) {
		this.latestChange = latestChange;
	}

	@Column(name = "ACCEPT_BY", length = 100)
	public String getAcceptBy() {
		return this.acceptBy;
	}

	public void setAcceptBy(String acceptBy) {
		this.acceptBy = acceptBy;
	}

	@Column(name = "CREATED_BY", length = 100)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "REQUEST_FROM_IP", length = 50)
	public String getRequestFromIp() {
		return this.requestFromIp;
	}

	public void setRequestFromIp(String requestFromIp) {
		this.requestFromIp = requestFromIp;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TARGET_ACCOUNT", length = 50)
	public String getTargetAccount() {
		return this.targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	@Column(name = "LIST_BILL_TYPE", precision = 8, scale = 0)
	public Integer getListBillType() {
		return this.listBillType;
	}

	public void setListBillType(Integer listBillType) {
		this.listBillType = listBillType;
	}

	@Column(name = "PREVIOUS_STT", precision = 8, scale = 0)
	public Integer getPreviousStt() {
		return this.previousStt;
	}

	public void setPreviousStt(Integer previousStt) {
		this.previousStt = previousStt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTH_AT", length = 7)
	public Date getAuthAt() {
		return this.authAt;
	}

	public void setAuthAt(Date authAt) {
		this.authAt = authAt;
	}

	@Column(name = "ACC_ID", precision = 0)
	public BigDecimal getAccId() {
		return this.accId;
	}

	public void setAccId(BigDecimal accId) {
		this.accId = accId;
	}

	@Column(name = "REQUEST_IDENTITY", length = 50)
	public String getRequestIdentity() {
		return this.requestIdentity;
	}

	public void setRequestIdentity(String requestIdentity) {
		this.requestIdentity = requestIdentity;
	}

	@Column(name = "CLIENT_REQ_ID", length = 50)
	public String getClientReqId() {
		return this.clientReqId;
	}

	public void setClientReqId(String clientReqId) {
		this.clientReqId = clientReqId;
	}

	@Column(name = "SERVICE_ID", precision = 0)
	public BigDecimal getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(BigDecimal serviceId) {
		this.serviceId = serviceId;
	}

}
