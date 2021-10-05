package springboot.sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import springboot.config.Constant;
import springboot.config.MainConfig;
import springboot.service.entities.RequestResponseObj;
import springboot.utils.GsonUltilities;

public class ClientHttp {

	private static ClientHttp instance;

	public static ClientHttp getInstance() {
		if (instance == null) {
			new ClientHttp();
		}
		return instance;
	}

	// static String URL = "http://45.76.203.179:8084/service?";
	static String URL = "";
	static String URLQUERY = "";// URL dùng
								// để kiểm
								// tra trạng
								// thái của
								// một
								// requestId
								// trước đó
								// dã gủi
								// lên
								// server
	static int partnerId = 0;
	static String partnerUsername = "";
	// static com.google.gson.Gson gson = new com.google.gson.Gson();
	final static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	static AtomicInteger autoInteger = new AtomicInteger();

	public ClientHttp() {
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
			int cardAmount, String subPartnerUsername, String subPartnerid) throws Exception {

		RequestResponseObj req = new RequestResponseObj();
		req.setPartnerId(partnerId);
		req.setPartner_username(partnerUsername);
		req.setCardPin(cardPin);
		req.setCardSerial(cardSerial);
		req.setProviderCode(telco);
		req.setRequestId(requestId);
		req.setCardPrintAmount(cardAmount);
		req.setSubPartnerId(subPartnerid);
		req.setSubPartner_username(subPartnerUsername);
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

		return requestResponseObj; // nguoi
		// choi
	}

	/**
	 * Hàm kiem tra trạng thái giao dịch theo mã request id truyền vào
	 * 
	 * @throws Exception
	 */
	public RequestResponseObj queryTransaction(String requestId) throws Exception {

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
		System.out.println("MENH GIA THE NEU STATUS = '00':" + requestResponseObj.getQueryResult().getCardAmount());// Su
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
		if (requestResponseObj.getStatus().equalsIgnoreCase("00")) {
			if (requestResponseObj.getQueryResult() != null) {
				System.out.println("TRANG THAI:" + requestResponseObj.getQueryResult().getStatus());
				System.out.println(
						"MENH GIA THE NEU STATUS = '00':" + requestResponseObj.getQueryResult().getCardAmount());// Su
			}
		}
		return requestResponseObj;
	}

	static String createRequestId() {
		String requestId = partnerId + "_" + dateFormat.format(new Date())
				+ String.format("%03d", autoInteger.incrementAndGet());
		return requestId;
	}

	public static void main(String[] args) {
		ClientHttp clientHttp = new ClientHttp();
		try {
			String requestId = new ClientHttp().createRequestId();
			clientHttp.sendPost("091234567812", "123456789023456", "VMS", createRequestId(), 10000, "xxx", "xxx2");
			// clientHttp.queryTransaction(requestId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
