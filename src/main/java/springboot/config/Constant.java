package springboot.config;

public class Constant {

	public Constant() {
		// TODO Auto-generated constructor stub
	}

	public static final int PR_LOGIN = 1100;
	public static final int PR_LOGIN_SOCIAL = 1200;
	public static final int PR_LOGIN_GMAIL = 1300;
	public static final int PR_REGISTER = 1400;
	public static final int PR_ACTIVEPHONE = 1500;
	public static final int PR_ACTIVE_EMAIL = 1600;
	public static final int PR_UPDATE_USER_INFO = 1700;
	public static final int PR_GETUSER_INF = 1800;
	public static final int PR_INNIT_PAYMENT = 1900;
	public static final int PR_GET_OTP = 2000;
	public static final int PR_SUBMIT_PAYMENT = 2100;
	public static final int PR_CHANGEPASS = 2200;
	public static final int PR_GET_BILL_INFO = 2300;
	public static final int PR_GET_HISTORY = 2400;
	public static final int PR_GET_HISTORY_DETAIL = 2500;
	public static final int PR_LOGOUT = 2600;

	public static final int PR_ADD_HOLDING_BALANCE = 2700;// CONG SO DU TAM GIU
	public static final int PR_SUBSTRACT_HOLDING_BALANCE = 2800;// TRU SO DU TAM
																// GIU
	public static final int PR_CREDIT_BALANCE = 2900;// CONG TIEN SO DU CHINH
	public static final int PR_DEBIT_BALANCE = 3000;// TRU TIEN SO DU CHINH
	public static final int PR_RESET_PASS = 3100;// Reset password
	public static final int PR_REGISTER_VA_ACCOUNT = 3200;// Reset password
	public static final int PR_DEBIT_SERVER_SERVER = 3300;// Tru tien server -
															// server
	public static final int PR_CREDIT_SERVER_SERVER = 3400;// Cong tien server -
															// server
	public static final int PR_CHECK_OTP = 3500;// Check OPT is valid
	public static final int PR_GETUSER_SERVER_SERVER = 3600;// Lay thông tin user server server
	public static final int PR_USER_PASS_IS_VALID = 3700;// Lay thông tin user server server

	public static final int PR_IMPORT_LIST_BILL = 2700;// Import danh sách
	public static final int PR_REQUEST_BUDGET = 2800;// Yeu cau han mức
	public static final int PR_APPROVE_BUDGET = 2900;// Approve hạn mức
	public static final int PR_REJECT_BUDGET = 2901;// Từ chôi hạn mức
	public static final int PR_STOP_LIST_BILL = 2902;// Dừng danh sách bill
	public static final int PR_START_LIST_BILL = 2903;// Khởi động ds bill
	public static final int PR_TRANSFER_TO_ACC = 2904;// Chuyển khoản cho acount
	public static final int PR_UPDATE_TRANSACTION_STT = 2905;// CAP NHAT TRANG
																// THAI GD

	public static final int PAYMENT_TYPE_BILL = 0;
	public static final int PAYMENT_TYPE_TOPUP = 1;
	public static final int PAYMENT_TYPE_DOWNLOAD = 2;
	public static final int PAYMENT_TYPE_TRANSFER_FROM_MASTER_TO_AGENT = 3;
	public static final int PAYMENT_TYPE_IMPORT_BILL_FROM_AD = 4;

	// 1: Tru tien khi thanh toan,2:Cong tien khi thanh toan;3:Cong han muc
	// Account;4:Cong han muc cho Admin
	public static final int BAL_CHANGE_TYPE_DEBIT = 1;// Tru tien thanh toan
	public static final int BAL_CHANGE_TYPE_CREDIT = 2;// Cong tien
	public static final int BAL_CHANGE_TYPE_CREDIT_BUDGET_ADMIN = 4;// Cong han
																	// muc cho
																	// Admin
	public static final int BAL_CHANGE_TYPE_RECEIVE_TRANSFER = 5;// Account duoc
																	// cong tien
																	// chuyen
																	// toi tu
																	// Admin
	public static final int BAL_CHANGE_TYPE_TRANSFER_TO_ACC = 6;// Admin bi tru
																// tien khi
																// chuyen cho
																// Account

	// public static final int BAL_CHANGE_TYPE_CREDIT_BUDGET_ACCOUNT___ =
	// 3;//Cong han muc cho Account
	public static final String BAL_CHANGE_AUTO = "AUTOMATE";

	public static final int BILL_TYPE_PAY_INTEMEDIATE = 1;
	public static final int BILL_TYPE_PAY_LATER = 2;

	public static final int BILL_STATUS_WAIT_TOBE_PAY = 0;// Bill da import va
															// cho thanh toan
	// public static final int BILL_STATUS_WAIT_ACCOUNT_PAY = 1;//bill chờ user
	// thanh toán
	// public static final int BILL_STATUS_WAIT_SCAN_TO_BE_PAY = 2;//bill chờ
	// scan để thanh toán
	public static final int BILL_STATUS_BE_STOPED = 3;// bill bị stop thanh toán
	public static final int SIM_AGENT = 0;// bill bị stop thanh toán
	public static final int SIM_MASTER = 1;// bill bị stop thanh toán

	public static final int ACC_ID_REF_TO_ADMIN_IMPORT_BILL = -100;// gia trij
																	// mac dinh
																	// cua
																	// truong
																	// AccID
																	// trong
																	// TBL_TRNSSACTION
																	// khi
																	// import
																	// file Bill

	public static final int STATUS_ACTIVE_ALL = 1;
	public static final int STATUS_UNACTIVE_ALL = 0;

	public static final int SERVICE_TYPE_SPECIAL = 3;// service type dac biet,
														// chi check key va
														// service code
}
