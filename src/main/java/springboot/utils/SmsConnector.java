package springboot.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import springboot.config.MainConfig;

public class SmsConnector {

	String XML_REQUEST = readHtmlEmailContent("./config/NotifySMS.xml");
	final static String URL_NOTIFY = "";
	static springboot.interfaces.ILog logger = null;

	private static SmsConnector instance = null;

	public static SmsConnector getInstance() {
		if (instance == null) {
			new SmsConnector();
		}
		return instance;
	}

	public SmsConnector() {
		// TODO Auto-generated constructor stub
		logger = (springboot.interfaces.ILog) springboot.hbn.home.ServicesRegister.shareInstance().context
				.getBean("LogManager");
		logger.setClass(this.getClass());
		instance = this;
	}

	public static void main(String[] args) {
		SmsConnector smsConnector = new SmsConnector();
		smsConnector.sendSMSBrandnameRequest("0933045719", null, "Hello");
	}

	public void sendSMSBrandnameRequest(String receiver, String alias, String content) {

		try {
			String soapXml = new String(XML_REQUEST);
			// =
			// buffer.getText(0,buffer.getLength())'
			String destination = receiver;
			String sender = alias;

			String outContent = content;
			String chargingFlag = "0";
			String moSeqNo = "0";
			String contentType = "SMS";
			String localTime = DateConvert.dateToString(new Date(), "yyyyMMddHHmmssSSS");

			soapXml = soapXml.replace("{receiver}", destination);
			soapXml = soapXml.replace("{content}", content);

			java.net.URL url = new java.net.URL(URL_NOTIFY);
			java.net.URLConnection conn = url.openConnection();
			// Set the necessary header fields
			conn.setRequestProperty("Content-type", "text/xml; charset=utf-8");
			conn.setRequestProperty("SOAPAction", URL_NOTIFY);
			conn.setDoOutput(true);
			// Send the request
			java.io.OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream());
			wr.write(soapXml);
			wr.flush();

			// Read the response
			java.io.BufferedReader rd = new java.io.BufferedReader(
					new java.io.InputStreamReader(conn.getInputStream()));
			String line;
			String result = "";

			while ((line = rd.readLine()) != null) {
				result += line;
				/* jEdit: print(line); */ }

			logger.info("SMS RESPONSE FROM PROVIDER=" + result + " &RECEIVER=" + receiver);
		} catch (Exception e) {
			// TODO: handle exception
			logger.fatal("SMS REQUEST TO VNPAY FAIL", e);

		}
	}

	static String readHtmlEmailContent(String filepath) {
		System.out.println(">>>>>>>>>>>>>>>>>READ FILE");
		String emailContent = "";
		BufferedReader br = null;
		try {
			FileInputStream fstream = new FileInputStream(filepath);
			DataInputStream in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in, "UTF8"));

			String strLine = "";
			while ((strLine = br.readLine()) != null) {
				emailContent = emailContent + " \n" + strLine;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return "";
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return emailContent;
	}
}
