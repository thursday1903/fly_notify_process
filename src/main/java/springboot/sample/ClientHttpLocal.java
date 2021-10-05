package springboot.sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import springboot.config.MainConfig;
import springboot.service.entities.RequestResponseObj;
import springboot.utils.GsonUltilities;

public class ClientHttpLocal {

	private static ClientHttpLocal instance;

	public static ClientHttpLocal getInstance() {
		if (instance == null) {
			new ClientHttpLocal();
		}
		return instance;
	}

	// static String URL = "http://45.76.203.179:8084/service?";

	static String URL = "http://localhost:8084/service?";
	static String URLQUERY = "http://localhost:8084/service/query?";// URL
																	// dùng
	// để kiểm
	// tra trạng
	// thái của
	// một
	// requestId
	// trước đó
	// dã gủi
	// lên
	// server
	static int partnerId = 1000;
	static String partnerUsername = "test_dev";
	// static com.google.gson.Gson gson = new com.google.gson.Gson();
	final static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	AtomicInteger autoInteger = new AtomicInteger();

	public ClientHttpLocal() {
		// TODO Auto-generated constructor stub
		instance = this;
	}

	// HTTP POST request
	/**
	 * Hàm gạch thẻ và nhận về kết quả giao dịch đẻ nạp cho user
	 * 
	 * @throws Exception
	 */
	public RequestResponseObj sendPost(String cardPin, String cardSerial, String telco, String requestId,
			int cardAmount) throws Exception {

		RequestResponseObj req = new RequestResponseObj();
		req.setPartnerId(partnerId);
		req.setPartner_username(partnerUsername);
		req.setCardPin(cardPin);
		req.setCardSerial(cardSerial);
		req.setProviderCode(telco);
		req.setRequestId(requestId);
		req.setCardPrintAmount(cardAmount);
		String body = GsonUltilities.toJson(req);

		String url = URL;
		java.net.URL obj = new java.net.URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozila");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "request=" + URLEncoder.encode(body);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());
		RequestResponseObj requestResponseObj = (RequestResponseObj) GsonUltilities.fromJson(response.toString(),
				RequestResponseObj.class);
		System.out.println("TRANG THAI:" + GsonUltilities.toJson(requestResponseObj));
		System.out.println("MENH GIA THE NEU STATUS = '00':" + requestResponseObj.getCardAmount());// Su
																									// dung
																									// gia
																									// tri
																									// nay
																									// de
																									// cong
																									// tien
																									// cho
		return requestResponseObj; // nguoi
		// choi
	}

	/**
	 * Hàm kiem tra trạng thái giao dịch theo mã request id truyền vào
	 * 
	 * @throws Exception
	 */
	private void queryTransaction(String requestId) throws Exception {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>START QUERY TRANSACTION");
		RequestResponseObj req = new RequestResponseObj();
		req.setPartnerId(partnerId);
		req.setPartner_username(partnerUsername);
		req.setCardPin("01232434356789");
		req.setCardSerial("01232434356111");
		req.setProviderCode("ViettelNN");
		req.setRequestId(requestId);
		String body = GsonUltilities.toJson(req);

		String url = URLQUERY;
		java.net.URL obj = new java.net.URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozila");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "request=" + URLEncoder.encode(body);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		RequestResponseObj requestResponseObj = (RequestResponseObj) GsonUltilities.fromJson(response.toString(),
				RequestResponseObj.class);
		System.out.println("TRANG THAI:" + requestResponseObj.getStatus());
		System.out.println(
				"MENH GIA THE NEU STATUS = '00':" + GsonUltilities.toJson(requestResponseObj.getQueryResult()));// Su
		// dung
		// gia
		// tri
		// nay
		// de
		// cong
		// tien
		// cho
		// nguoi
		// choi

	}

	String createRequestId() {
		String requestId = partnerId + "_" + dateFormat.format(new Date())
				+ String.format("%03d", autoInteger.incrementAndGet());
		return requestId;
	}

	public static void main(String[] args) {
		ClientHttpLocal clientHttp = new ClientHttpLocal();
		try {
			// Mã: 600025962275
			// Số seri: 074211000001346
			// Mã: 922735208746
			// Số seri: 074211000001347
			// Mã: 569012605644
			// Số seri: 074451000000084
			// Mã: 176083163012
			// Số seri: 074451000000085

			long beforeCharge = System.currentTimeMillis();
			String requestId = new ClientHttpLocal().createRequestId();
			// clientHttp.sendPost("812965926245492", "10000566219536", "VTT",
			// requestId, 100000);
			clientHttp.sendPost("112890563278", "074801000018309", "VMS", requestId, 100000);
			// clientHttp.queryTransaction("20002_20181115163251_358");
			// clientHttp.queryTransaction(requestId);
			long afterCharge = System.currentTimeMillis();
			System.out.println(afterCharge - beforeCharge);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
