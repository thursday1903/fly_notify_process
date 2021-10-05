package springboot.service.entities;

import springboot.hbn.entities.TblTransactions;

public class TblTransactionExt extends TblTransactions {

	@Override
	public String toString() {
		return "TblTransactionExt [simEZ=" + simEZ + ", balBefore=" + balBefore + ", balAfter=" + balAfter
				+ ", response=" + response + ", getRequestId()=" + getRequestId() + ", getTransactionType()="
				+ getTransactionType() + ", getRequestAmt()=" + getRequestAmt() + ", getCreatedDate()="
				+ getCreatedDate() + ", getUpdatedDate()=" + getUpdatedDate() + ", getFinalStatus()=" + getFinalStatus()
				+ ", getRemark()=" + getRemark() + ", getTrnFee()=" + getTrnFee() + ", getBillId()=" + getBillId()
				+ ", getTargetNumber()=" + getTargetNumber() + ", getAccId()=" + getAccId() + ", getAccUserName()="
				+ getAccUserName() + ", getAgentNumber()=" + getAgentNumber() + ", getAgentRpMessage()="
				+ getAgentRpMessage() + ", getAgentBalBef()=" + getAgentBalBef() + ", getAgentBalAf()="
				+ getAgentBalAf() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String simEZ;
	private String balBefore="0";
	private String balAfter="0";
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getSimEZ() {
		return simEZ;
	}

	public void setSimEZ(String simEZ) {
		this.simEZ = simEZ;
	}

	public String getBalBefore() {
		return balBefore;
	}

	public void setBalBefore(String balBefore) {
		this.balBefore = balBefore;
	}

	public String getBalAfter() {
		return balAfter;
	}

	public void setBalAfter(String balAfter) {
		this.balAfter = balAfter;
	}

}
