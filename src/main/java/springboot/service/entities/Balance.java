package springboot.service.entities;

import java.util.Date;

public class Balance {
	private String service_code;
	private Long remain_balance;
	private Long remain_balance_before;
	private Long holding_balance;
	private Long holding_balance_before;
	private Long bonus_bal;
	private Long bonus_balance_before;
	private Date latest_update;

	public Long getRemain_balance_before() {
		return remain_balance_before;
	}

	public void setRemain_balance_before(Long remain_balance_before) {
		this.remain_balance_before = remain_balance_before;
	}

	public Long getHolding_balance_before() {
		return holding_balance_before;
	}

	public void setHolding_balance_before(Long holding_balance_before) {
		this.holding_balance_before = holding_balance_before;
	}

	public Long getBonus_balance_before() {
		return bonus_balance_before;
	}

	public void setBonus_balance_before(Long bonus_balance_before) {
		this.bonus_balance_before = bonus_balance_before;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public Long getRemain_balance() {
		return remain_balance;
	}

	public void setRemain_balance(Long remain_balance) {
		this.remain_balance = remain_balance;
	}

	public Long getHolding_balance() {
		return holding_balance;
	}

	public void setHolding_balance(Long holding_balance) {
		this.holding_balance = holding_balance;
	}

	public Long getBonus_bal() {
		return bonus_bal;
	}

	public void setBonus_bal(Long bonus_bal) {
		this.bonus_bal = bonus_bal;
	}

	public Date getLatest_update() {
		return latest_update;
	}

	public void setLatest_update(Date latest_update) {
		this.latest_update = latest_update;
	}
}
