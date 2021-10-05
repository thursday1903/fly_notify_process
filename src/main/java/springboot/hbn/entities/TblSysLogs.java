package springboot.hbn.entities;

// default package
// Generated Oct 9, 2019 3:01:44 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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

/**
 * TblSysLogs generated by hbm2java
 */
@Entity
@Table(name = "TBL_SYS_LOGS")
public class TblSysLogs implements java.io.Serializable {

	private BigDecimal logId;
	private BigDecimal logAction;
	private Date logDate;
	private String logReqData;
	private String logReqRp;
	private Date logRpStatus;

	public TblSysLogs() {
	}

	public TblSysLogs(BigDecimal logId, BigDecimal logAction) {
		this.logId = logId;
		this.logAction = logAction;
	}

	public TblSysLogs(BigDecimal logId, BigDecimal logAction, Date logDate, String logReqData, String logReqRp,
			Date logRpStatus) {
		this.logId = logId;
		this.logAction = logAction;
		this.logDate = logDate;
		this.logReqData = logReqData;
		this.logReqRp = logReqRp;
		this.logRpStatus = logRpStatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "retal_seq")
	@SequenceGenerator(name = "retal_seq", sequenceName = "SEQ_SYS_LOG")
	@Column(name = "LOG_ID", unique = true, nullable = false, precision = 0)
	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	@Column(name = "LOG_ACTION", nullable = false, precision = 0)
	public BigDecimal getLogAction() {
		return this.logAction;
	}

	public void setLogAction(BigDecimal logAction) {
		this.logAction = logAction;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_DATE", length = 7)
	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@Column(name = "LOG_REQ_DATA", length = 500)
	public String getLogReqData() {
		return this.logReqData;
	}

	public void setLogReqData(String logReqData) {
		this.logReqData = logReqData;
	}

	@Column(name = "LOG_REQ_RP", length = 200)
	public String getLogReqRp() {
		return this.logReqRp;
	}

	public void setLogReqRp(String logReqRp) {
		this.logReqRp = logReqRp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_RP_STATUS", length = 7)
	public Date getLogRpStatus() {
		return this.logRpStatus;
	}

	public void setLogRpStatus(Date logRpStatus) {
		this.logRpStatus = logRpStatus;
	}

}
