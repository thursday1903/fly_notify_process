package springboot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import algorithm.MD5;
import algorithm.TripleDES;
import springboot.config.Constant;
import springboot.config.YAMLConfig;
import springboot.hbn.home.ServicesRegister;
import springboot.interfaces.ILog;
import springboot.service.entities.RequestResponseMsg;
import springboot.service.entities.SSOBaseObject;
import springboot.service.entities.SSOUserData;
import springboot.service.entities.SystemPaymentData;
import springboot.sso.bussiness.SSOBussiness;
import springboot.utils.Commons;
import springboot.utils.GsonUltilities;

@RestController
public class Controller {

	@Autowired
	private YAMLConfig myConfig;

	public Controller() {
		// TODO Auto-generated constructor stub
		log = (ILog) ServicesRegister.shareInstance().context.getBean("LogManager");
		log.setClass(this.getClass());
	}

	private static final String template = "%s";
	static ILog log = null;

	@RequestMapping(value = "/SSO/API", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String servicepost(HttpServletRequest request, @RequestBody String payload) {
		String clientIp = Commons.getClientIp(request);

		return handleServiceRequest(payload, clientIp);
	}

	/**
	 * Parse messsage firm va day vao nghiep vu chinh
	 * 
	 * @param request
	 * @param requestIp
	 * @return
	 */
	String handleServiceRequest(String request, String requestIp) {
		String messageResponse = "NOPRCODE";

		log.info(
				"------------------------------------------------------------------------------------------------------------------------------------");
		log.info(">>>>>REQUEST FROM[" + requestIp + "][" + request + "]");
		System.out.println(">>>>>REQUEST FROM[" + requestIp + "][" + request + "]");
		RequestResponseMsg requestResponseMsg = null;
		try {
			requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(request, RequestResponseMsg.class);
			int processingCode = requestResponseMsg.getP_code();

			String identifyRq = UUID.randomUUID().toString();
			Random random = new Random(System.currentTimeMillis());
			identifyRq = identifyRq.replace("-", "") + random.nextInt(99999);
			requestResponseMsg.setResponse_identify(identifyRq);

			
		} catch (Exception e) {
			// TODO: handle exception
			log.fatal("processFirmRequest", e);
		} finally {
			System.out.println("<<<<<<REQUEST RESPONSE[" + requestIp + "][" + request + "]:[" + messageResponse + "]");
			log.info("<<<<<<REQUEST RESPONSE[" + requestIp + "][" + request + "]:[" + messageResponse + "]");
		}
		return messageResponse;
	}

	public Map<String, String> parsePostBody(String input) {
		try {
			Map<String, String> mapParams = new HashMap<>();
			String[] nameValuePairs = input.split("&");
			for (String nameValuePair : nameValuePairs) {
				String[] entity = nameValuePair.trim().split("=");
				mapParams.put(entity[0], entity[1]);
			}
			return mapParams;
		} catch (Exception e) {
			// TODO: handle exception
			log.fatal("parsePostBody", e);
		}
		return null;
	}

	static void testSVCFunction() {

		Controller contr = new Controller();
		String key3des = "1f2f8c540779987923165751";
		String serviceCode = "f90b20cd58124b2ab992fab896ba888e";
		final int CLIENT_TYPE = 3;

		String user = "0933045719";
		String pwd = "123456";
		String loginSuccessSession = null;

		SSOBaseObject ssoBaseObject = new SSOBaseObject();
		ssoBaseObject.setAcc_email("vietda@imediatech.com.vn");
		ssoBaseObject.setCell_phone("0933045719");
		ssoBaseObject.setAcc_identify("0933045719");
		ssoBaseObject.setAcc_pwd("123123");
		ssoBaseObject.setReceive_notify(1);
		ssoBaseObject.setDisplay_name("Amokachi");
		String registerData = GsonUltilities.toJson(ssoBaseObject);

		String regisDataE = null;
		try {
			regisDataE = TripleDES.encrypt(key3des, registerData);
			pwd = MD5.hash("123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String postData = null;
		String requestRs = null;

		RequestResponseMsg requestResponseMsg = new RequestResponseMsg(1400, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("REGIS HISTORY DATA CLEAR:" + registerData);
		System.out.println("REGIS HISTORY DATA ENCRYPT:" + regisDataE);
		System.out.println("REGIS HISTORY POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("REGIS DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("END REGIS HISTORY-----------------------------------------------");

		SSOUserData ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		ssoUserData.setPassword(pwd);
		ssoUserData.setSession_key("[login success session]");

		regisDataE = null;
		String login = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, login);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_LOGIN, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("LOGIN DATA CLEAR:" + login);
		System.out.println("LOGIN DATA ENCRYPT:" + regisDataE);
		System.out.println("LOGIN POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			String strrp = TripleDES.decrypt(key3des, requestResponseMsg.getData());
			ssoUserData = (SSOUserData) GsonUltilities.fromJson(strrp, SSOUserData.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("END LOGIN-----------------------------------------------");

		System.out.println("START CHECK PASSWORD AND USER");

		ssoUserData.setUsername(user);
		ssoUserData.setPassword(pwd);
		ssoUserData.setSession_key("[login success session]");

		regisDataE = null;
		login = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, login);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_USER_PASS_IS_VALID, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("CHECK PASS DATA CLEAR:" + login);
		System.out.println("CHECK PASS DATA ENCRYPT:" + regisDataE);
		System.out.println("CHECK PASS POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			String strrp = TripleDES.decrypt(key3des, requestResponseMsg.getData());
			ssoUserData = (SSOUserData) GsonUltilities.fromJson(strrp, SSOUserData.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("END CHECK USER PASS-----------------------------------------------");
		System.exit(2);

		ssoUserData.setUsername(user);
		ssoUserData.setPassword(pwd);
		ssoUserData.setNewpassword("123456789");
		ssoUserData.setSession_key(ssoUserData.getSession_key());
		loginSuccessSession = ssoUserData.getSession_key();
		regisDataE = null;
		String changepass = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, changepass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_CHANGEPASS, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("CHANGEPASS DATA CLEAR:" + changepass);
		System.out.println("CHANGEPASS DATA ENCRYPT:" + regisDataE);
		System.out.println("CHANGEPASS POST DATA:" + postData);
		// requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("END CHANGEPASS-----------------------------------------------");

		ssoUserData.setUsername(user);
		// ssoUserData.setSession_key("[login success session]");

		regisDataE = null;
		String getUserInf = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, getUserInf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_GETUSER_INF, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("GET USER INF DATA CLEAR:" + changepass);
		System.out.println("GET USER INF DATA ENCRYPT:" + regisDataE);
		System.out.println("GET USER INF POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("END GET USER INF-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setEmail("vietda1@imediatech.com.vn");
		;
		ssoUserData.setOther_system_auth_user_id(System.currentTimeMillis() + "");
		ssoUserData.setLogin_from(2);

		regisDataE = null;
		String loginSocialData = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, loginSocialData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_LOGIN_SOCIAL, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("LOGIN SOCIAL DATA CLEAR:" + loginSocialData);
		System.out.println("LOGIN SOCIAL DATA ENCRYPT:" + regisDataE);
		System.out.println("LOGIN SOCIAL POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("LOGIN SOCIAL-----------------------------------------------");

		SystemPaymentData systemPaymentData = new SystemPaymentData();
		systemPaymentData.setCreatedBy(user);
		systemPaymentData.setSession_key(loginSuccessSession);
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		String addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_ADD_HOLDING_BALANCE, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("ADD HOLDING DATA CLEAR:" + addHolding);
		System.out.println("ADD HOLDING DATA ENCRYPT:" + regisDataE);
		System.out.println("ADD HOLDING POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("ADD HOLDING BALANCE:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(
				"ADD HOLDING BALANCE-----------------------------------------------" + requestResponseMsg.toString());

		systemPaymentData.setCreatedBy(user);
		// systemPaymentData.setSession_key(ssoUserData.getSession_key());
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_SUBSTRACT_HOLDING_BALANCE, "", "", serviceCode,
				CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("SUBSTRACT HOLDING DATA CLEAR:" + addHolding);
		System.out.println("SUBSTRACT HOLDING DATA ENCRYPT:" + regisDataE);
		System.out.println("SUBSTRACT HOLDING POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("SUBSTRACT HOLDING BALANCE-----------------------------------------------");

		systemPaymentData.setCreatedBy(user);
		// systemPaymentData.setSession_key(ssoUserData.getSession_key());
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_CREDIT_BALANCE, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("CREDIT DATA CLEAR:" + addHolding);
		System.out.println("CREDIT DATA ENCRYPT:" + regisDataE);
		System.out.println("CREDIT POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("CREDIT DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("CREDIT BALANCE-----------------------------------------------");

		systemPaymentData.setCreatedBy(user);
		// systemPaymentData.setSession_key(ssoUserData.getSession_key());
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_DEBIT_BALANCE, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("DEBIT DATA CLEAR:" + addHolding);
		System.out.println("DEBIT DATA ENCRYPT:" + regisDataE);
		System.out.println("DEBIT POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("DEBIT DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("DEBIT BALANCE-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		String getOTPData = GsonUltilities.toJson(ssoUserData);
		System.out.println("GET OTP CLEAR:" + getOTPData);
		try {
			getOTPData = TripleDES.encrypt(key3des, getOTPData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_GET_OTP, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(getOTPData);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("GET OTP ENCRYPT:" + getOTPData);
		System.out.println("GET OTP DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("GET OTP RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("GET OTP-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		ssoUserData.setOtp_code("ABCXYZ");
		getOTPData = GsonUltilities.toJson(ssoUserData);
		System.out.println("VALID OTP CLEAR:" + getOTPData);
		try {
			getOTPData = TripleDES.encrypt(key3des, getOTPData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_CHECK_OTP, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(getOTPData);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("VALID OTP ENCRYPT:" + getOTPData);
		System.out.println("VALID OTP DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("GET OTP RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("VALID OTP-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		ssoUserData.setOtp_code("123456");
		String resetPass = GsonUltilities.toJson(ssoUserData);
		System.out.println("RESET PASS USE CLEAR:" + resetPass);
		try {
			resetPass = TripleDES.encrypt(key3des, resetPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_RESET_PASS, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(resetPass);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("RESET PASS USE ENCRYPT:" + resetPass);
		System.out.println("RESET PASS USE DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("RESET PASS USE  RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("RESET PASS USE OTP-----------------------------------------------");
	}

	static void generateMessage() {

		Controller contr = new Controller();
		String key3des = "1f2f8c540779987923165751";
		String serviceCode = "f90b20cd58124b2ab992fab896ba888e";
		final int CLIENT_TYPE = 3;

		Boolean preg = false;
		Boolean plogin = true;
		Boolean pgetUserInf = true;
		Boolean pchangePass = false;
		Boolean pgetOtp = false;
		Boolean pCheckOtp = false;

		String user = "0908310776";
		String pwd = null;
		String loginSuccessSession = null;

		SSOBaseObject ssoBaseObject = new SSOBaseObject();
		ssoBaseObject.setAcc_email("vietda1@imediatech.com.vn");
		ssoBaseObject.setCell_phone("0933045686");
		ssoBaseObject.setAcc_identify("0933045686");
		ssoBaseObject.setAcc_pwd("123123");
		ssoBaseObject.setReceive_notify(1);
		ssoBaseObject.setDisplay_name("Amokachi");

		String registerData = GsonUltilities.toJson(ssoBaseObject);

		String regisDataE = null;
		try {
			regisDataE = TripleDES.encrypt(key3des, registerData);
			pwd = MD5.hash("123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String postData = null;
		String requestRs = null;

		RequestResponseMsg requestResponseMsg = new RequestResponseMsg(1400, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("REGIS HISTORY DATA CLEAR:" + registerData);
		System.out.println("REGIS HISTORY DATA ENCRYPT:" + regisDataE);
		System.out.println("REGIS HISTORY POST DATA:" + postData);

		if (preg) {
			requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		}
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("REGIS DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("END REGIS HISTORY-----------------------------------------------");

		SSOUserData ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		ssoUserData.setPassword(pwd);
		ssoUserData.setSession_key("[login success session]");
		ssoUserData.setLogin_from(0);
		ssoUserData.setClient_identity_str(UUID.randomUUID().toString());

		regisDataE = null;
		String login = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, login);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_LOGIN, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("LOGIN DATA CLEAR:" + login);
		System.out.println("LOGIN DATA ENCRYPT:" + regisDataE);
		System.out.println("LOGIN POST DATA:" + postData);
		if (plogin) {
			requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		}
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			String strrp = TripleDES.decrypt(key3des, requestResponseMsg.getData());
			ssoUserData = (SSOUserData) GsonUltilities.fromJson(strrp, SSOUserData.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("END LOGIN-----------------------------------------------");

		ssoUserData.setUsername(user);
		ssoUserData.setPassword(pwd);
		ssoUserData.setNewpassword("123456789");
		ssoUserData.setSession_key(ssoUserData.getSession_key());
		loginSuccessSession = ssoUserData.getSession_key();
		regisDataE = null;
		String changepass = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, changepass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_CHANGEPASS, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("CHANGEPASS DATA CLEAR:" + changepass);
		System.out.println("CHANGEPASS DATA ENCRYPT:" + regisDataE);
		System.out.println("CHANGEPASS POST DATA:" + postData);
		if (pchangePass) {
			requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		}
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("CHANGEPASS DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("END CHANGEPASS-----------------------------------------------");

		ssoUserData.setUsername(user);
		// ssoUserData.setSession_key("[login success session]");

		regisDataE = null;
		String getUserInf = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, getUserInf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_GETUSER_INF, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("GET USER INF DATA CLEAR:" + changepass);
		System.out.println("GET USER INF DATA ENCRYPT:" + regisDataE);
		System.out.println("GET USER INF POST DATA:" + postData);

		if (pgetUserInf) {
			requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		}
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("END GET USER INF-----------------------------------------------");

		System.exit(1);
		ssoUserData = new SSOUserData();
		ssoUserData.setEmail("vietda1@imediatech.com.vn");
		;
		ssoUserData.setOther_system_auth_user_id(System.currentTimeMillis() + "");
		ssoUserData.setLogin_from(2);

		regisDataE = null;
		String loginSocialData = GsonUltilities.toJson(ssoUserData);
		try {
			regisDataE = TripleDES.encrypt(key3des, loginSocialData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_LOGIN_SOCIAL, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("LOGIN SOCIAL DATA CLEAR:" + loginSocialData);
		System.out.println("LOGIN SOCIAL DATA ENCRYPT:" + regisDataE);
		System.out.println("LOGIN SOCIAL POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("LOGIN SOCIAL-----------------------------------------------");

		SystemPaymentData systemPaymentData = new SystemPaymentData();
		systemPaymentData.setCreatedBy(user);
		systemPaymentData.setSession_key(loginSuccessSession);
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		String addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_ADD_HOLDING_BALANCE, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("ADD HOLDING DATA CLEAR:" + addHolding);
		System.out.println("ADD HOLDING DATA ENCRYPT:" + regisDataE);
		System.out.println("ADD HOLDING POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("ADD HOLDING BALANCE:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(
				"ADD HOLDING BALANCE-----------------------------------------------" + requestResponseMsg.toString());

		System.exit(0);
		systemPaymentData.setCreatedBy(user);
		// systemPaymentData.setSession_key(ssoUserData.getSession_key());
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_SUBSTRACT_HOLDING_BALANCE, "", "", serviceCode,
				CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("SUBSTRACT HOLDING DATA CLEAR:" + addHolding);
		System.out.println("SUBSTRACT HOLDING DATA ENCRYPT:" + regisDataE);
		System.out.println("SUBSTRACT HOLDING POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("LOGIN DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("SUBSTRACT HOLDING BALANCE-----------------------------------------------");

		systemPaymentData.setCreatedBy(user);
		// systemPaymentData.setSession_key(ssoUserData.getSession_key());
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_CREDIT_BALANCE, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("CREDIT DATA CLEAR:" + addHolding);
		System.out.println("CREDIT DATA ENCRYPT:" + regisDataE);
		System.out.println("CREDIT POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("CREDIT DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("CREDIT BALANCE-----------------------------------------------");

		systemPaymentData.setCreatedBy(user);
		// systemPaymentData.setSession_key(ssoUserData.getSession_key());
		systemPaymentData.setBalChangeAmount(1000);

		regisDataE = null;
		addHolding = GsonUltilities.toJson(systemPaymentData);
		try {
			regisDataE = TripleDES.encrypt(key3des, addHolding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_DEBIT_BALANCE, "", "", serviceCode, CLIENT_TYPE,
				"1.0.0");
		requestResponseMsg.setData(regisDataE);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("DEBIT DATA CLEAR:" + addHolding);
		System.out.println("DEBIT DATA ENCRYPT:" + regisDataE);
		System.out.println("DEBIT POST DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("DEBIT DATA RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("DEBIT BALANCE-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		String getOTPData = GsonUltilities.toJson(ssoUserData);
		System.out.println("GET OTP CLEAR:" + getOTPData);
		try {
			getOTPData = TripleDES.encrypt(key3des, getOTPData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_GET_OTP, "", "", serviceCode, CLIENT_TYPE, "1.0.0");
		requestResponseMsg.setData(getOTPData);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("GET OTP ENCRYPT:" + getOTPData);
		System.out.println("GET OTP DATA:" + postData);
		if (pgetOtp) {
			requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		}
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("GET OTP RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("GET OTP-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		ssoUserData.setOtp_code("ABCXYZ");
		getOTPData = GsonUltilities.toJson(ssoUserData);
		System.out.println("VALID OTP CLEAR:" + getOTPData);
		try {
			getOTPData = TripleDES.encrypt(key3des, getOTPData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_CHECK_OTP, "", "", serviceCode, 2, "1.0.0");
		requestResponseMsg.setData(getOTPData);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("VALID OTP ENCRYPT:" + getOTPData);
		System.out.println("VALID OTP DATA:" + postData);
		if (pCheckOtp) {
			requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		}
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("GET OTP RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("VALID OTP-----------------------------------------------");

		ssoUserData = new SSOUserData();
		ssoUserData.setUsername(user);
		ssoUserData.setOtp_code("123456");
		String resetPass = GsonUltilities.toJson(ssoUserData);
		System.out.println("RESET PASS USE CLEAR:" + resetPass);
		try {
			resetPass = TripleDES.encrypt(key3des, resetPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestResponseMsg = new RequestResponseMsg(Constant.PR_RESET_PASS, "", "", serviceCode, 2, "1.0.0");
		requestResponseMsg.setData(resetPass);

		postData = GsonUltilities.toJson(requestResponseMsg);
		System.out.println("RESET PASS USE ENCRYPT:" + resetPass);
		System.out.println("RESET PASS USE DATA:" + postData);
		requestRs = contr.handleServiceRequest(postData, "127.0.0.1");
		System.out.println("<<<<<<<<<<<<<<<" + requestRs);
		requestResponseMsg = (RequestResponseMsg) GsonUltilities.fromJson(requestRs, RequestResponseMsg.class);
		try {
			System.out.println("RESET PASS USE  RP:" + TripleDES.decrypt(requestResponseMsg.getData(), key3des));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("RESET PASS USE OTP-----------------------------------------------");
	}

	public static void main(String[] args) {
//		 testCoreFunction();
		testSVCFunction();
//		generateMessage();
	}
}
