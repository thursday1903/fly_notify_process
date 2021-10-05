package springboot.service.entities;

import springboot.hbn.entities.TblBalChangeLog;

/**
 * @author vietanh Doi tuong luu tru message request, response ve web
 */
public class WebClientRequestRspObject extends TblBalChangeLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String requestId;
	String retStringData;
	int updateStatus;

	public int getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(int updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRetStringData() {
		return retStringData;
	}

	public void setRetStringData(String retStringData) {
		this.retStringData = retStringData;
	}
}
