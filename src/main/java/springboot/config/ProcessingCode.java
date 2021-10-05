package springboot.config;

public class ProcessingCode {

	public enum PrCode {
		// synch_key: 1000
		// login: 1100
		// login_social(FB): 1200
		// login_gmail: 1300
		// register: 1400
		// active phone: 1500
		// active email: 1600
		// update user info: 1700
		// get user info: 1800
		// innit payment(fee): 1900
		// - payment
		// - transfer
		// - credit
		// get otp: 2000
		// submit payment: 2100
		// change_password: 2200
		PR_GET_SESSION(1000), PR_LOGIN(1100), PR_LOGIN_SOCIAL(1200), PR_LOGIN_GMAIL(1300), PR_REGISTER(
				1400), PR_ACTIVEPHONE(1500), PR_ACTIVE_EMAIL(1600), PR_UPDATE_USER_INFO(1700), PR_GETUSER_INF(
						1800), PR_INNIT_PAYMENT(1900), PR_GET_OTP(2000), PR_SUBMIT_PAYMENT(2100), PR_CHANGEPASS(2200);

		private int value;

		private PrCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static void main(String[] args) {
			// System.out.println(Pattern.compile("-?[0-9]+").matches("700000.0000"));
		}
	}

}
