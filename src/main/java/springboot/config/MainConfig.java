package springboot.config;

public class MainConfig extends BaseConfig {
	private final static String SYSTEM_CONFFIG = "/config/main.cfg";

	public static int REDIS_MAX_CONN = 50;
	public static int REDIS_MIN_IDLE = 5;
	public static int REDIS_MAX_IDLE = 50;
	public static String REDIS_HOST = "192.168.1.1";
	public static int REDIS_PORT = 6379;
	public static int REDIS_DATAFILE = 2;
	public static String REDIS_PASSWORD = "192.168.1.1";
	public static Boolean REDIS_ENABLE_AUTHEN = true;

	public static String mailServer = "smtp.gmail.com";
	public static int mailServerPort = 465;
	public static String mailFrom = "vietda@imediatech.com.vn";
	public static String mailFromPass = "mvbexbumfnrixbmw";
	public static Boolean mailEnableSSl = true;
	public static String listAdminEmail = "vietda@imediatech.com.vn,";
	public static String listAdminEmailCC = "";
	public static String listAdminEmailBCC = "";
	public static String PARTNER_LIMIT_BUDGET = "FUNMOBI:100000000";
	public static int INNIT_AMOUNT_ACCEPT_AMOUNT = 100000000;
	public static int TOTAL_OTP_LIVE_TIME = 300;// seconds.
	public static String TELE_URL = "https://api.telegram.org/bot973875366:AAGaP3YMEQepGDpqT0IlSwRvE6LS_Iga9x0/sendMessage";
	public static String TELE_CHATID = "-498023828";
	public static Boolean TELE_OTP = true;
	public static String dataFiles;

	// Config ket noi Thu ho
	public static String COLLECTIVE_MONEY_URL = "http://103.68.241.67:9013/partner-v1/sandbox/encash/register";
	public static String COLLECTIVE_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANIk4Dx2+9MNZD+WxUvImM0DdYO9/BHsWQYXuNzTilqie2A/OsP6ak2xPAkxmyAeFUm5Zbna2oarrf37OEQcLkZNj0P4bvygED6tZLRRuKoCe12WK13jGPndCtYBFh7YmN9Y86gbhp5SvcybePH0Yg+tNgK++zzTjrrRBJPGzgMjAgMBAAECgYB+XWBd26qoigf7WP2BCbogpfDXbLoUSQxrZn5qg+ZRhB0ujjcXPBEKcQZJ/QNsN8rTT2IFdb4sdzYkYRm/kzabRrlIW8lClkqxavNXRKiw2DdgASUYlQguOQmp1k9ZVNP7xlPbrD6Uf+5VPiCls3beSS3zxLi0CXZrsdgaJEZ+UQJBAPImu5v+vmsmjrXAJOlp8yBM5/G58Tq/gwMSIz8VfLusH3eZHLVFl36dLEITDncYu5Xkk2rjs0uQDzBn1HIFdUsCQQDeKYi8xrpfrI48bieqq7AkLDXjL+NnPD5jEeCjm0kLpLmGc4wr+hlHxsQw5x0+khDJM9Hu3K9r00UPiE38pfqJAkEAxRD+h4J2x072vzUjFQ5TfYr5DEEt74ih/2LIanN7MmRuq29g7Kk5FCKnPRKzJ/tp1h/SVR//71PK+LLXMv/8JwJBAMRyV64N0DnPnMqE4WCChrPFwDTPYLclx0jVQx2Ir5OzKjZgsD8m3PniIhPYGbz9bghsYSNlU1z2KWgtsEBLCHkCQB7jtgmthNaa+44w+VfOM7OWWH5PvNCChXdgKYD7Cz4Fm4JA9zbTcvRYp3VmiJVyFbTqed8WEp7eplwp6RNaxRw=";
	public static String COLLECTIVE_PARTNER_ID = "svc_dev";
	// API WARNING INTERFACE
	public static String NOTIFY_API = "";

	public static String ERROR_MESSAGE_MAPPING = "200	Thanh cong&" + "100	Sai user hoac pass&"
			+ "101	Khong co thong tin tai khoan&" + "102	No service found&" + "103	Giaima du lieu that bai&"
			+ "104	Decrypt data fail&" + "105	No session found&" + "106	He thong dang ban thu lai sau&"
			+ "107	Khong thay thong tin hoa don&" + "108	So du không du&" + "99	Giao dich dang duoc xu ly&"
			+ "110	San pham khong du&" + "111	Sai payment type&"
			+ "112	NO_BILL_SELECTED_TO_PAY	Khong co bill nao duoc chon de thanh toan&" + "113	Khong co bill data nao&"
			+ "114	Khong thay thong tin giao dich&" + "116	Sai gia tri tien&" + "115	Sai Sdt&"
			+ "117	Khong du so du&" + "118	Sai thong tin bill info&"
			+ "119	So dien thoai khong phai thue bao tra sau&"
			+ "120	Giao dich khong thanh cong, he thong dang ban, thu lai sau&" + "121	Xac nhan yeu cau nap tien&"
			+ "122	Tu choi yeu cau nap tien&" + "123	Thong tin bill da ton tai&"
			+ "124	Tong so tien trong file bill khong hop le&" + "125	Admin nam khong hop le, kiem tra lai&"
			+ "126	Cong tien cho Account that bai&" + "127	Khong the Approve cho request <> pending&"
			+ "128	Khong thay thong tin Account nhan tien, kiem tra lai&"
			+ "129	Khong thay thong tin bill nao de stop&" + "130	Request ID empty&"
			+ "131	Khong duoc update giao dich trang thai khac pending&" + "132	Account da ton tai&"
			+ "133	Sai do dai password&" + "134	Dinh dang email khong dung&"
			+ "135	Otp khong dung hoac khong ton tai&"
			+ "136	Tai khoang khong co dien thoai, de nghi cap nhat dien thoai roi thu lai chuc nang nay&"
			+ "137	Khong the tru so du tam giu cua account thanh gia tri < 0";

	public static String LIST_QUEUENAME_NEED_TO_SCAN = "QUEUE_NAME_,";
	public static String LIST_TO_EMAIL = "";
	public static String LIST_CC_EMAIL = "";

	public MainConfig(String configPath) {
		super(configPath);
		// TODO Auto-generated constructor stub
		getAllParas();
		instance = this;
	}

	@Override
	protected void getAllParas() {
		// TODO Auto-generated method stub
		super.getAllParas();

		REDIS_MAX_CONN = getInt("REDIS_MAX_CONN", REDIS_MAX_CONN);
		REDIS_MIN_IDLE = getInt("REDIS_MIN_IDLE", REDIS_MIN_IDLE);
		REDIS_MAX_IDLE = getInt("REDIS_MAX_IDLE", REDIS_MAX_IDLE);
		REDIS_HOST = properties.getProperty("REDIS_HOST", REDIS_HOST);
		REDIS_PORT = getInt("REDIS_PORT", REDIS_PORT);
		REDIS_DATAFILE = getInt("REDIS_DATAFILE", REDIS_DATAFILE);

		mailServer = properties.getProperty("mailServer", mailServer).trim();
		mailServerPort = getInt("mailServerPort", mailServerPort);
		mailFrom = properties.getProperty("mailFrom", mailFrom).trim();
		mailFromPass = properties.getProperty("mailFromPass", mailFromPass).trim();
		mailEnableSSl = getBoolProperty("mailEnableSSl", mailEnableSSl);
		listAdminEmail = properties.getProperty("listAdminEmail", listAdminEmail).trim();
		PARTNER_LIMIT_BUDGET = properties.getProperty("PARTNER_LIMIT_BUDGET", PARTNER_LIMIT_BUDGET).trim();
		INNIT_AMOUNT_ACCEPT_AMOUNT = getInt("INNIT_AMOUNT_ACCEPT_AMOUNT", INNIT_AMOUNT_ACCEPT_AMOUNT);
		ERROR_MESSAGE_MAPPING = properties.getProperty("ERROR_MESSAGE_MAPPING", ERROR_MESSAGE_MAPPING);
		TOTAL_OTP_LIVE_TIME = getInt("TOTAL_OTP_LIVE_TIME", TOTAL_OTP_LIVE_TIME);
		TELE_URL = properties.getProperty("TELE_URL", TELE_URL);
		TELE_CHATID = properties.getProperty("TELE_CHATID", TELE_CHATID);
		TELE_OTP = getBoolProperty("TELE_OTP", TELE_OTP);
		dataFiles = properties.getProperty("dataFiles",dataFiles);

		// Config ket noi Thu ho
		COLLECTIVE_MONEY_URL = properties.getProperty("COLLECTIVE_MONEY_URL", COLLECTIVE_MONEY_URL);
		COLLECTIVE_PRIVATE_KEY = properties.getProperty("COLLECTIVE_PRIVATE_KEY", COLLECTIVE_PRIVATE_KEY);
		COLLECTIVE_PARTNER_ID = properties.getProperty("COLLECTIVE_PARTNER_ID", COLLECTIVE_PARTNER_ID);
		NOTIFY_API = properties.getProperty("NOTIFY_API", NOTIFY_API);
		REDIS_PASSWORD = properties.getProperty("REDIS_PASSWORD", REDIS_PASSWORD).trim();
		REDIS_ENABLE_AUTHEN = getBoolProperty("REDIS_ENABLE_AUTHEN", REDIS_ENABLE_AUTHEN);
		LIST_QUEUENAME_NEED_TO_SCAN = properties.getProperty("LIST_QUEUENAME_NEED_TO_SCAN",
				LIST_QUEUENAME_NEED_TO_SCAN);
		LIST_TO_EMAIL = properties.getProperty("LIST_TO_EMAIL", LIST_TO_EMAIL);
		LIST_CC_EMAIL = properties.getProperty("LIST_CC_EMAIL", LIST_CC_EMAIL);
		listAdminEmailBCC= properties.getProperty("listAdminEmailBCC", listAdminEmailBCC);
	}

	private static MainConfig instance = null;

	public static MainConfig GetInstance() {
		if (instance == null)
			new MainConfig(SYSTEM_CONFFIG);
		return instance;
	}

	public void reloadConfig() {
		loadProperties();
	}

	static {
		MainConfig.GetInstance();
	}

	public static void main(String[] args) {

	}

}
