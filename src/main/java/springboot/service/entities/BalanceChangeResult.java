package springboot.service.entities;

public class BalanceChangeResult {

	Integer balChangeResult;
	Long balBefore;
	Long balAfter;
	Long holdingBalBefore;
	Long holdingBalAfter;

	public Integer getBalChangeResult() {
		return balChangeResult;
	}

	public void setBalChangeResult(Integer balChangeResult) {
		this.balChangeResult = balChangeResult;
	}

	public Long getBalBefore() {
		return balBefore;
	}

	public void setBalBefore(Long balBefore) {
		this.balBefore = balBefore;
	}

	public Long getBalAfter() {
		return balAfter;
	}

	public void setBalAfter(Long balAfter) {
		this.balAfter = balAfter;
	}

	public Long getHoldingBalBefore() {
		return holdingBalBefore;
	}

	public void setHoldingBalBefore(Long holdingBalBefore) {
		this.holdingBalBefore = holdingBalBefore;
	}

	public Long getHoldingBalAfter() {
		return holdingBalAfter;
	}

	public void setHoldingBalAfter(Long holdingBalAfter) {
		this.holdingBalAfter = holdingBalAfter;
	}
}
