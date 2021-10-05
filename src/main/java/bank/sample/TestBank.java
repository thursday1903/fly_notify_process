package bank.sample;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import springboot.utils.GsonUltilities;

public class TestBank {

	public static void main(String[] args) {
		disableCertificateValidation();
		
		try {
			// # Constants used in this example
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"; 
			final String LOGIN_FORM_URL = "https://ib.techcombank.com.vn/servlet/BrowserServlet";
			final String USERNAME = "yourUsername";  
			final String PASSWORD = "yourPassword";  

			// # Go to login page
			Connection.Response loginFormResponse = Jsoup.connect(LOGIN_FORM_URL)
			                                             .method(Connection.Method.GET)
			                                             .userAgent(USER_AGENT)
			                                             .execute();  

			// # Fill the login form
			// ## Find the form first...
			Element loginForm = loginFormResponse.parse()
			                                         .select("div#qwfs_content").first();
			checkElement("Login Form", loginForm);

			// ## ... then "type" the username ...
			Element loginField = loginForm.select("#signOnName").first();
			checkElement("Login Field", loginField);
			loginField.val(USERNAME);

			// ## ... and "type" the password
			Element passwordField = loginForm.select("#password").first();
			checkElement("Password Field", passwordField);
			passwordField.val(PASSWORD);        


			// # Now send the form for login
			FormElement formElement = (FormElement)loginForm;
			
			Connection.Response loginActionResponse =  formElement.submit()
			         .cookies(loginFormResponse.cookies())
			         .userAgent(USER_AGENT)  
			         .execute();

			System.out.println(loginActionResponse.parse().html());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
	}
	
	public static void checkElement(String name, Element elem) {
	    if (elem == null) {
	        throw new RuntimeException("Unable to find " + name);
	    }
	}
	
	public static void disableCertificateValidation() {
	    // Create a trust manager that does not validate certificate chains
	    TrustManager[] trustAllCerts = new TrustManager[] { 
	      new X509TrustManager() {
	        public X509Certificate[] getAcceptedIssuers() { 
	          return new X509Certificate[0]; 
	        }
	        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	    }};

	    // Ignore differences between given hostname and certificate hostname
	    HostnameVerifier hv = new HostnameVerifier() {
	      public boolean verify(String hostname, SSLSession session) { return true; }
	    };

	    // Install the all-trusting trust manager
	    try {
	      SSLContext sc = SSLContext.getInstance("SSL");
	      sc.init(null, trustAllCerts, new SecureRandom());
	      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	      HttpsURLConnection.setDefaultHostnameVerifier(hv);
	    } catch (Exception e) {}
	  }
}
