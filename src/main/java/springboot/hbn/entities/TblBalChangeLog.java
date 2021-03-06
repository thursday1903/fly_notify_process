package springboot.hbn.entities;

import java.beans.Transient;

// default package
// Generated Nov 4, 2019 5:30:30 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.google.gson.reflect.TypeToken;

import springboot.utils.GsonUltilities;

/**
 * TblBalChangeLog generated by hbm2java
 */
@Entity
@Table(name = "TBL_BAL_CHANGE_LOG")
@DynamicInsert
@DynamicUpdate
public class TblBalChangeLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "TblBalChangeLog [balanceLogId=" + balanceLogId + ", balChangeType=" + balChangeType
				+ ", balChangeStatus=" + balChangeStatus + ", balChangeAmount=" + balChangeAmount + ", balBef=" + balBef
				+ ", balAf=" + balAf + ", changeDate=" + changeDate + ", latestChange=" + latestChange + ", acceptBy="
				+ acceptBy + ", createdBy=" + createdBy + ", requestFromIp=" + requestFromIp + ", remark=" + remark
				+ ", pwd=" + pwd + ", listBillType=" + listBillType + ", expireDate=" + expireDate + ", billFilename="
				+ billFilename + ", changeDataDetail=" + changeDataDetail + ", targetAccount=" + targetAccount + "]";
	}

	private Long balanceLogId;
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
	private String pwd;
	private Integer listBillType;// 1:Danh s??ch thanh to??n;2:danh s??ch d??? li???u
	private String expireDate;// yyyyMMdd
	private String billFilename;
	private String changeDataDetail;
	private String targetAccount;
	private Integer previousStt;
	private Date authAt;
	private Long accId;
	private String requestIdentity;
	private String clientReqId;
	private Integer serviceId;
//	CLIENT_REQ_ID

	@Column(name = "SERVICE_ID", precision = 8, scale = 0)
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name = "ACC_ID", precision = 20, scale = 0)
	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getBillFilename() {
		return billFilename;
	}

	public void setBillFilename(String billFilename) {
		this.billFilename = billFilename;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Transient
	public String getChangeDataDetail() {
		return changeDataDetail;
	}

	public void setChangeDataDetail(String changeDataDetail) {
		this.changeDataDetail = changeDataDetail;
	}

	public void setBalanceLogId(Long balanceLogId) {
		this.balanceLogId = balanceLogId;
	}

	@Transient
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public TblBalChangeLog() {
	}

	public TblBalChangeLog(Long balanceLogId, int balChangeType, int balChangeStatus, Date changeDate) {
		this.balanceLogId = balanceLogId;
		this.balChangeType = balChangeType;
		this.balChangeStatus = balChangeStatus;
		this.changeDate = changeDate;
	}

	public TblBalChangeLog(Long balanceLogId, int balChangeType, int balChangeStatus, Integer balChangeAmount,
			BigDecimal balBef, BigDecimal balAf, Date changeDate, Date latestChange, String acceptBy, String createdBy,
			String requestFromIp) {
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
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "retal_seq")
	@SequenceGenerator(name = "retal_seq", sequenceName = "SEQ_BAL_CHANGE_LOG")
	@Column(name = "BALANCE_LOG_ID", unique = true, nullable = false, precision = 0)
	public Long getBalanceLogId() {
		return this.balanceLogId;
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

	@Column(name = "REQUEST_IDENTITY", length = 50)
	public String getRequestIdentity() {
		return this.requestIdentity;
	}

	public void setRequestIdentity(String requestIdentity) {
		this.requestIdentity = requestIdentity;
	}

	@Column(name = "CLIENT_REQ_ID", length = 50)
	public String getClientReqId() {
		return clientReqId;
	}

	public void setClientReqId(String clientReqId) {
		this.clientReqId = clientReqId;
	}

	public static void main(String[] args) {
		TblBalChangeLog tblBalChangeLog = new TblBalChangeLog();
		tblBalChangeLog.setListBillType(1);
		tblBalChangeLog.setBillFilename("THANG 11-2019.xls");
		tblBalChangeLog.setExpireDate("20191115");
		tblBalChangeLog.setPwd("AAA");
		tblBalChangeLog.setCreatedBy("Admin1");
		tblBalChangeLog.setRemark("IMPORT FILE THANH TOAN");

		TblBills[] tblBills = new TblBills[2];
		TblBills tbBills1 = new TblBills();
		tbBills1.setBillAmount(100000);
		tbBills1.setTargetNumber("0934567890");
		tbBills1.setBillTargetName("NGUYEN A CHAU");

		TblBills tbBills2 = new TblBills();
		tbBills2.setBillAmount(100000);
		tbBills2.setTargetNumber("0934567860");
		tbBills2.setBillTargetName("NGUYEN A PHI");
		tblBills[0] = tbBills1;
		tblBills[1] = tbBills2;

		String jssnArrBill = GsonUltilities.toJson(tblBills);

		tblBalChangeLog.setChangeDataDetail(jssnArrBill);

		String test = "\"[{\"targetNumber\":\"0934567890\",\"billAmount\":100000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG KIEN\",\"billType\":1},{\"targetNumber\":\"0934567891\",\"billAmount\":100000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG TRUC\",\"billType\":1},{\"targetNumber\":\"0934567892\",\"billAmount\":500000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG LO\",\"billType\":1},{\"targetNumber\":\"0934567893\",\"billAmount\":100000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG A\",\"billType\":1},{\"targetNumber\":\"0934567894\",\"billAmount\":100000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG B\",\"billType\":1},{\"targetNumber\":\"0934567895\",\"billAmount\":1000000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG Z\",\"billType\":1},{\"targetNumber\":\"0934567896\",\"billAmount\":100000,\"billMonth\":0,\"billTargetName\":\"NGUYEN TRUNG THU\",\"billType\":1}]\"";

		String jSonArrBill = tblBalChangeLog.getChangeDataDetail();
		ArrayList<Object> arrayList = GsonUltilities.fromJson(test, new TypeToken<ArrayList<TblBills>>() {
		}.getType());

		// System.out.println(GsonUltilities.toJson(tblBalChangeLog));
	}
}
