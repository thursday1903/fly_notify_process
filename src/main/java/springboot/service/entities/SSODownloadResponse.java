package springboot.service.entities;

public class SSODownloadResponse extends SSODownloadRequest {
	private String transaction_id;

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getDownload_status() {
		return download_status;
	}

	public void setDownload_status(int download_status) {
		this.download_status = download_status;
	}

	private String serial;
	private String pin;
	private int download_status;// trang thai download cua san pham nay de
								// client hien thi
}
