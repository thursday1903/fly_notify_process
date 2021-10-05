package springboot.config;

import java.util.Hashtable;

public class ResponseCode {

	static final Hashtable<String, String> HASH_ERR = new Hashtable<>();
	private static final String ERROR_KEY_PRF = "ERR_";

	public enum RpCode {
		TRANSACTION_SUCCESS(200), INVALID_USER_OR_PASS(100), NO_USER_FOUND(101), NO_SERVICE_FOUND(102),
		DECRYPT_DATA_FAIL(103), DECRYPT_DATA_FAIL_NO_KEY_FOUND(104), NO_SESSION_FOUND(105), FATAL_ERROR(106),
		FOUND_NO_BILL_ID(107), BALANCE_IS_NOT_ENOUGH(108), TRANSACTION_IS_PROCESSING(99), PRODUCT_IS_NOT_ENOUGH(110),
		INVALID_PAYMENT_TYPE(111), NO_BILL_SELECTED_TO_PAY(112), NO_BILL_DATA_TO_PAY(113), NO_TRANSACTION_FOUND(114),
		INVALID_PHONE_NO(115), INVALID_AMOUNT(116), TRANSACTION_FAIL_BAL_EXCEED(117), INVALID_BILL_INFO(118),
		INVALID_POST_PAID_NUMBER(119), TRANSACTION_FAIL_EZ_BUSY(120), APPROVE_REQUEST_BALANCE(121),
		REJECT_REQUEST_BALANCE(122), EXIST_BILL_INFO(123), BILL_TOTAL_AMOUNT_INVALID(124), ADMIN_NAME_INVALID(125),
		CREDIT_BALANCE_ACC_FAIL(126), CAN_NOT_ACCEPT_NON_PENDING_REQ(127), NO_TARGET_USER_FOUND(128),
		FOUND_NO_BILL_TO_STOP(129), REQUEST_ID_EMPTY(130), CANNOT_UPDATE_UNPENDING(131), ACCOUNT_EXISTED(132),
		INVALID_PASS_LEN(133), INVALID_EMAIL_ADDRESS(134), INVALID_OTP_OR_NO_OTP_FOUND(135), ACCOUNT_HAVENO_PHONE(136),
		HOLDING_BALANCE_CANBE_SUBSTRACT_TO_NEGATIVE(137);
		private int value;

		private RpCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static void main(String[] args) {
			// System.out.println(Pattern.compile("-?[0-9]+").matches("700000.0000"));
		}
	}

	public static void main(String[] args) {
		ResponseCode rpCode = new ResponseCode();

	}

	public String getErrorCode(int code) {
		String key = ERROR_KEY_PRF + code;
		return HASH_ERR.get(key);
	}

	static void loadErrorMsg() {
		String[] arrMappingErrorMesssage = MainConfig.ERROR_MESSAGE_MAPPING.split("&");
		for (String string : arrMappingErrorMesssage) {
			String[] entity = string.split("\t");
			String key = ERROR_KEY_PRF + entity[0];
			System.out.println(key + " AND " + entity[1]);
			HASH_ERR.put(key, entity[1]);
		}
	}

	static {
		loadErrorMsg();
	}
}
