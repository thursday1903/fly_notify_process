package springboot.service.entities;

public class ExcelRowData {

	// Mã đơn vị tài chính Mã đơn gd vị chi Mã giao dịch Số tài khoản Mã giao
	// dịch ngân hàng Tên khách hàng Số tiền yêu cầu chi Lý do yêu cầu cập nhật
	// Trạng thái IMEDIATECH Trạng thái đối tác Trạng thái khách hàng Trạng thái
	// cập nhật Ngân hàng Nội dung chuyển tiền
	String partnerCode;
	String imediaTransId;
	String partnerTransId;
	String targetAccount;
	String bankTransId;
	String accountName;
	Double requestAmount;
	String updateReason;
	int imediaStatus;
	int partnerStatus;
	int updateStatus;
	String bankcode;
	String memo;

	public ExcelRowData(String partnerCode, String imediaTransId, String partnerTransId, String targetAccount,
			String bankTransId, String accountName, Double requestAmount, String updateReason, int imediaStatus,
			int partnerStatus, int updateStatus, String bankcode, String memo) {
		super();
		this.partnerCode = partnerCode;
		this.imediaTransId = imediaTransId;
		this.partnerTransId = partnerTransId;
		this.targetAccount = targetAccount;
		this.bankTransId = bankTransId;
		this.accountName = accountName;
		this.requestAmount = requestAmount;
		this.updateReason = updateReason;
		this.imediaStatus = imediaStatus;
		this.partnerStatus = partnerStatus;
		this.updateStatus = updateStatus;
		this.bankcode = bankcode;
		this.memo = memo;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getImediaTransId() {
		return imediaTransId;
	}

	public void setImediaTransId(String imediaTransId) {
		this.imediaTransId = imediaTransId;
	}

	public String getPartnerTransId() {
		return partnerTransId;
	}

	public void setPartnerTransId(String partnerTransId) {
		this.partnerTransId = partnerTransId;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public String getBankTransId() {
		return bankTransId;
	}

	public void setBankTransId(String bankTransId) {
		this.bankTransId = bankTransId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Double getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(Double requestAmount) {
		this.requestAmount = requestAmount;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public int getImediaStatus() {
		return imediaStatus;
	}

	public void setImediaStatus(int imediaStatus) {
		this.imediaStatus = imediaStatus;
	}

	public int getPartnerStatus() {
		return partnerStatus;
	}

	public void setPartnerStatus(int partnerStatus) {
		this.partnerStatus = partnerStatus;
	}

	public int getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(int updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
