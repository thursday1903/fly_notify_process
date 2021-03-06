package springboot.hbn.entities;

// default package
// Generated Oct 9, 2019 3:01:44 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblAlertQueue generated by hbm2java
 */
@Entity
@Table(name = "TBL_ALERT_QUEUE")
public class TblAlertQueue implements java.io.Serializable {

	private BigDecimal queueId;
	private String alertType;
	private String alertContent;
	private String alertSubject;
	private String alertToEmail;
	private String alertCcEmail;
	private Date alertAt;
	private Byte alertStatus;
	private Byte alertSendStatus;

	public TblAlertQueue() {
	}

	public TblAlertQueue(BigDecimal queueId) {
		this.queueId = queueId;
	}

	public TblAlertQueue(BigDecimal queueId, String alertType, String alertContent, String alertSubject,
			String alertToEmail, String alertCcEmail, Date alertAt, Byte alertStatus, Byte alertSendStatus) {
		this.queueId = queueId;
		this.alertType = alertType;
		this.alertContent = alertContent;
		this.alertSubject = alertSubject;
		this.alertToEmail = alertToEmail;
		this.alertCcEmail = alertCcEmail;
		this.alertAt = alertAt;
		this.alertStatus = alertStatus;
		this.alertSendStatus = alertSendStatus;
	}

	@Id

	@Column(name = "QUEUE_ID", unique = true, nullable = false, precision = 0)
	public BigDecimal getQueueId() {
		return this.queueId;
	}

	public void setQueueId(BigDecimal queueId) {
		this.queueId = queueId;
	}

	@Column(name = "ALERT_TYPE", length = 2)
	public String getAlertType() {
		return this.alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	@Column(name = "ALERT_CONTENT", length = 200)
	public String getAlertContent() {
		return this.alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}

	@Column(name = "ALERT_SUBJECT", length = 1000)
	public String getAlertSubject() {
		return this.alertSubject;
	}

	public void setAlertSubject(String alertSubject) {
		this.alertSubject = alertSubject;
	}

	@Column(name = "ALERT_TO_EMAIL", length = 1000)
	public String getAlertToEmail() {
		return this.alertToEmail;
	}

	public void setAlertToEmail(String alertToEmail) {
		this.alertToEmail = alertToEmail;
	}

	@Column(name = "ALERT_CC_EMAIL", length = 1000)
	public String getAlertCcEmail() {
		return this.alertCcEmail;
	}

	public void setAlertCcEmail(String alertCcEmail) {
		this.alertCcEmail = alertCcEmail;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALERT_AT", length = 7)
	public Date getAlertAt() {
		return this.alertAt;
	}

	public void setAlertAt(Date alertAt) {
		this.alertAt = alertAt;
	}

	@Column(name = "ALERT_STATUS", precision = 2, scale = 0)
	public Byte getAlertStatus() {
		return this.alertStatus;
	}

	public void setAlertStatus(Byte alertStatus) {
		this.alertStatus = alertStatus;
	}

	@Column(name = "ALERT_SEND_STATUS", precision = 2, scale = 0)
	public Byte getAlertSendStatus() {
		return this.alertSendStatus;
	}

	public void setAlertSendStatus(Byte alertSendStatus) {
		this.alertSendStatus = alertSendStatus;
	}

}
