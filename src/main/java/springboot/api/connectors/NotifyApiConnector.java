package springboot.api.connectors;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import springboot.config.MainConfig;
import springboot.logs.Logs;
import springboot.sso.bussiness.SSOBussiness;

public class NotifyApiConnector {

	private static NotifyApiConnector instance;
	final static Logs logger = new Logs(NotifyApiConnector.class);
	
	public static NotifyApiConnector getInstance() {
		if (instance == null) {
			new NotifyApiConnector();
		}
		return instance;
	}

	public NotifyApiConnector() {
		super();
		// TODO Auto-generated constructor stub
		instance = this;
	}
	
	public static void main(String[] args) {
		NotifyApiConnector.getInstance().callNotifyApi("0933045719", "zzz");
	}
	
	public Boolean callNotifyApi(String number, String content)
	{
		if(MainConfig.TELE_OTP)
		{
//			SSOBussiness.getInstance().composeAlertToTele(content);
		}
		
		final String API = MainConfig.NOTIFY_API;
		final String formatXml = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
							"<Body>"+
							"<sendSMS xmlns=\"http://Interface.notify\">"+
							    "<serviceNumber>[serviceNumber]</serviceNumber>"+
							    "<commandCode>IMEDIA</commandCode>"+
							    "<smsReceives>[smsReceives]</smsReceives>"+
							    "<smsContent>[smsContent]</smsContent>"+
							"</sendSMS>"+
							"</Body>"+
							"</Envelope>";
		
		String finalRequestXml = formatXml.replace("[smsReceives]", number).replace("[smsContent]", content);
		sendSoapRequest(API, finalRequestXml);
		return true;
	}
	
	/**
	 * Post request to soap service
	 * 
	 * @param url_
	 * @param xmlData
	 * @return
	 */
	public static synchronized String sendSoapRequest(String url_, String xmlData) {

		StringBuffer result = new StringBuffer();
		try {
			// =
			// buffer.getText(0,buffer.getLength())'
			java.net.URL url = new java.net.URL(url_);
			java.net.URLConnection conn = url.openConnection();

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] buffer = new byte[xmlData.length()];
			buffer = xmlData.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			String SOAPAction = "http://Interface.notify";
			// Set the appropriate HTTP parameters.
			httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", SOAPAction);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			// Write the content of the request to the outputstream of the HTTP
			// Connection.
			out.write(b);
			out.close();
			// Ready with sending the request.

			// Read the response.
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String responseString = null;
			String outputString = null;
			// Write the SOAP message response to a String.
			while ((responseString = in.readLine()) != null) {
				result.append(responseString);
			}

			logger.info("SOAP XML=" + xmlData);
			logger.info("CHARGE RESPONSE FROM PROVIDER=" + result.toString() + "");
		} catch (Exception e) {
			// TODO: handle exception
			logger.fatal("sendSoapRequest", e);
			// AlertBussiness.getInstance().composeAlertAndSend(e, 0);
		}
		System.out.println(result.toString());
		return result.toString();
	}
}
