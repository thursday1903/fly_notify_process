package springboot.service.entities;

public class CardObject {

	private String cardpin;
	private String serial;
	private String prefix;
	private int cardAmount;
	private boolean indentifyAmount;

	public String getCardpin() {
		return cardpin;
	}

	public void setCardpin(String cardpin) {
		this.cardpin = cardpin;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(int cardAmount) {
		this.cardAmount = cardAmount;
	}

	public boolean isIndentifyAmount() {
		return indentifyAmount;
	}

	public void setIndentifyAmount(boolean indentifyAmount) {
		this.indentifyAmount = indentifyAmount;
	}

}
