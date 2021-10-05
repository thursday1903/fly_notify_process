package springboot.service.entities;

public class OrderDetail {
	String targetAccount;
	int amount;
	String provider;
	int accountType;
	String orderExpire;
	int orderIsPriotired;
	int chargeType;

	public int getChargeType() {
		return chargeType;
	}

	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}

	public String getOrderExpire() {
		return orderExpire;
	}

	public void setOrderExpire(String orderExpire) {
		this.orderExpire = orderExpire;
	}

	public int getOrderIsPriotired() {
		return orderIsPriotired;
	}

	public void setOrderIsPriotired(int orderIsPriotired) {
		this.orderIsPriotired = orderIsPriotired;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
}
