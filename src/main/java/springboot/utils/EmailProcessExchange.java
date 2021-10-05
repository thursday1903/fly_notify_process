package springboot.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import springboot.config.MainConfig;

//Need: mail.jar

/**
 * Lop nghiep vu xu ly gui email, email_from duoc cau hinh trong he thong
 * 
 * @author ptdv_dev
 *
 */
public class EmailProcessExchange {
	static springboot.interfaces.ILog log = null;

	public EmailProcessExchange() {
		super();
		// TODO Auto-generated constructor stub
		log = (springboot.interfaces.ILog) springboot.hbn.home.ServicesRegister.shareInstance().context
				.getBean("LogManager");
		log.setClass(this.getClass());
		instance = this;
	}
	
	private static EmailProcessExchange instance;

	public static EmailProcessExchange getInstance() {
		if (instance == null) {
			new EmailProcessExchange();
		}
		return instance;
	}

	static Session session;
	static Properties props;
	public final static String path_before = "config/mail_warning_before.html";
	public final static String path_after = "config/mail_warning_after.html";
	public static String[] Excelcolumn;
	public static DecimalFormat df = new DecimalFormat("#,###,##0");
	static Transport trans = null;
	
	public static void main(String[] args) {
		System.out.println("load config");

		System.out.println("load config success");
		try {
			for (int i = 0; i < 3; i++) {

				EmailProcessExchange.getInstance().sendEmailAlert1("vietda@imediatech.com.vn", null,"vietda@imediatech.com.vn","vietda@imediatech.com.vn", 
						"just for test", "this is test content", false);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void run() {
		// TODO Auto-generated method stub
		try {
			final String HOST = "pro51.emailserver.vn";
			final String MAILFROM = "noreply@techcore.com.vn";
			final String MAILFROMPASS = "techcore1q23456";
			final Integer PORT = 465;
			
//			log.info("Send alert mail: START");
			Properties mailProp = new Properties();
			mailProp.put("mail.smtp.host", HOST);
			mailProp.put("mail.smtp.port", "true");
			mailProp.put("mail.smtp.auth", "true");
			mailProp.put("mail.smtp.socketFactory.port", PORT);
			mailProp.put("mail.smtp.timeout", 60000);
			mailProp.put("mail.smtp.ssl.enable", true);
			mailProp.put("mail.smtp.starttls.enable", true);
		
			Session session = null;
			session = Session.getInstance(mailProp, null);
		
			MimeMessage mymsg = new MimeMessage(session);
			mymsg.setSentDate(new Date());
			mymsg.setFrom(new InternetAddress(MAILFROM));
			
			String subject = "XXX";
			if(subject  == null) {
				mymsg.setSubject(subject);
			}else {
				mymsg.setSubject(subject);
			}
			
			mymsg.setContent("heeloo","text/html; charset=UTF-8");
			String recipient_ls = "vietda@imediatech.com.vn";
			if (recipient_ls == null)
				recipient_ls = recipient_ls;
			
			
			String[] recipientList = recipient_ls.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			
			for (String recipient : recipientList) {
			    recipientAddress[counter] = new InternetAddress(recipient.trim());
			    counter++;
			}
			
			mymsg.setRecipients(Message.RecipientType.TO, recipientAddress);
			
			mymsg.saveChanges();
			Transport trans = null;
//			System.out.println(ApplicationCfg.email_username);
//			System.out.println(ApplicationCfg.email_password);
			trans = session.getTransport("smtp");
			final String username = MAILFROM;
	        final String password = MAILFROMPASS;
			trans.connect(username, password);
			trans.sendMessage(mymsg, mymsg.getAllRecipients());
			System.out.println("Send alert mail DONE");
			log.info("Send alert mail DONE");
		} catch (Exception e) {
			System.out.println("Send alert email exception: " + e.getMessage());
			log.info("Send alert email exception: ",e);
		}
	}

	/*
	 * Send email with attach
	 */
	public static Properties getMailProper() {
		if (props == null) {
			props = new Properties();
			props.put("mail.debug", "false");

			props.put("mail.smtp.host", MainConfig.mailServer);
			props.put("mail.smtp.port", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", MainConfig.mailServerPort);
			props.put("mail.smtp.timeout", 60000);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.starttls.enable", true);
			
			return props;
		} else
			return props;
	}

	/**
	 * 
	 * @param email
	 * @param filepath
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public  int sendEmailAlert1(String email, String filepath,String cc,String bcc, String subject, String content, Boolean isHmtl)
			throws Exception {
		try {
			// String spName = "";
			String to = email;
			String from = MainConfig.mailFrom;
			// String Path = "";
			String msgText1 = content;

			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, MailConfig.log_file_duration);
			// DateFormat dateFormat = new SimpleDateFormat("MM_yyyy");
			// String dateLog = dateFormat.format(cal.getTime());
			// Path = MainConfig.urlFolder + "/" + dateLog;

			// ---------------------------------------------
			// config mail
			// create some properties and get the default Session
			Properties props = getMailProper();

			if (session == null)
				session = Session.getDefaultInstance(props);
//				session = Session.getInstance(props, new javax.mail.Authenticator() {
//					@Override
//					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//						return new javax.mail.PasswordAuthentication("noreply@techcore.com.vn", "techcore1q23456");
//					}
//				});

			// from address
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			// msg.setContent(content, "text/html; charset=utf-8");
			// to address

			InternetAddress[] addressTo = convertListEmail(to);
			InternetAddress[] addressCC = convertListEmail(cc);
			InternetAddress[] addressBcc = convertListEmail(bcc);
			
			
			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);
			if (addressCC != null) {
				msg.setRecipients(javax.mail.Message.RecipientType.CC, addressCC);
			}
			if (addressBcc != null) {
				msg.setRecipients(javax.mail.Message.RecipientType.BCC, addressBcc);
			}
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			msg.setSubject(subject);
			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message

			// atack to mail
			// mp.addBodyPart(mbp2);
			if (isHmtl) {
				msg.setContent(msgText1, "text/html; charset=utf-8");
			} else {
				msg.setContent(mp);
			}
			log.info("dang gui email");
			try {

//				System.out.println(ApplicationCfg.email_username);
//				System.out.println(ApplicationCfg.email_password);
				if (trans == null) {
					System.out.println("Innit mail");
					trans = session.getTransport("smtp");
					trans.connect(MainConfig.mailFrom, MainConfig.mailFromPass);
				}
				trans.sendMessage(msg, msg.getAllRecipients());
				log.info("gui email thanh cong toi danh sach: " + email);
				return 1;
			} catch (Exception e) {
				trans = session.getTransport("smtp");
				trans.connect(MainConfig.mailFrom, MainConfig.mailFromPass);
				log.fatal("SEND MAIL", e);
				return -1;
			}
		} catch (Exception e) {
			log.fatal("sendEmailAlert", e);
			return -1;
		}
	}
	
	

	/**
	 * 
	 * @param email
	 * @param filepath
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public int sendEmailAlert(String email, String listEmailcc, String filepath, String subject, String content,
			Boolean isHmtl) throws Exception {
		try {
			// String spName = "";
			String to = email;
			String from = "noreply@techcore.com.vn";
			// String Path = "";
			String msgText1 = content;

			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, MailConfig.log_file_duration);
			// DateFormat dateFormat = new SimpleDateFormat("MM_yyyy");
			// String dateLog = dateFormat.format(cal.getTime());
			// Path = MainConfig.urlFolder + "/" + dateLog;

			// ---------------------------------------------
			// config mail
			// create some properties and get the default Session
			Properties props = getMailProper();

//			if (session == null)
				session = Session.getInstance(props);
//						props, new javax.mail.Authenticator() 
//				{
//					@Override
//					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//						return new javax.mail.PasswordAuthentication(MainConfig.mailFrom, MainConfig.mailFromPass);
//					}
//				});

			// from address
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			// msg.setContent(content, "text/html; charset=utf-8");
			// to address

			InternetAddress[] addressTo = convertListEmail(to);
			InternetAddress[] addressCc = convertListEmail(listEmailcc);

			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);
			msg.setRecipients(javax.mail.Message.RecipientType.CC, addressCc);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			msg.setSubject(subject);
			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message

			// atack to mail
			// mp.addBodyPart(mbp2);
			if (isHmtl) {
				msg.setContent(msgText1, "text/html; charset=utf-8");
			} else {
				msg.setContent(mp);
			}
			log.info("dang gui email");
			try {
				Transport.send(msg);
				log.info("gui email thanh cong toi danh sach: " + email);

			} catch (Exception e) {
				log.fatal("", e);
			}

			return 1;

		} catch (Exception e) {

			log.fatal("", e);
			return -1;
		}

	}

	/**
	 * Ham gui email có kèm file attach
	 * 
	 * @param receiverEmail
	 *            email cua nguoi nhan
	 * @param filepath
	 *            duong dan tuyet doi toi file attach can gui kem
	 * @param subject
	 *            noi dung email
	 * @return
	 * @throws Exception
	 */
	public static int sentEmailWithAttach(String receiverEmail, String[] filepath, String subject, String content)
			throws Exception {
		try {
			// String spName = "";
			String to = receiverEmail;
			String from = MainConfig.mailFrom;
			// String Path = "";
			String msgText1 = content;

			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, MailConfig.log_file_duration);
			// DateFormat dateFormat = new SimpleDateFormat("MM_yyyy");
			// String dateLog = dateFormat.format(cal.getTime());
			// Path = MainConfig.urlFolder + "/" + dateLog;

			// ---------------------------------------------
			// config mail
			// create some properties and get the default Session
			Properties props = getMailProper();

			if (session == null)
				session = Session.getInstance(props, new javax.mail.Authenticator() {
					@Override
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(MainConfig.mailFrom, MainConfig.mailFromPass);
					}
				});
			// from address
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			// to address

			InternetAddress[] addressTo = convertListEmail(to);

			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			msg.setSubject(subject);
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();

			if (filepath != null) {
				for (int i = 0; i < filepath.length; i++) {
					// attach the file to the message
					MimeBodyPart mbp2 = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(filepath[i]);
					log.info("SEND TO RECEIVER:" + receiverEmail + ", FILE TO SEND:" + filepath[i]);
					mbp2.setDataHandler(new DataHandler(fds));
					mbp2.setFileName(fds.getName());
					mbp2.attachFile(filepath[i]);
					mp.addBodyPart(mbp2);
					// addAttachment(mp, filepath[i]);
				}
			}
			// atack to mail
			// mp.addBodyPart(mbp2);
			msg.setContent(mp);

			try {
				Transport.send(msg);
				log.info("SEND SETTLE MAIL SUCCESS TO RECEIVER: " + receiverEmail);
			} catch (Exception e) {
				e.printStackTrace();
				log.fatal("SEND SETTLE MAIL FAIL", e);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("SEND SETTLE MAIL FAIL", e);
			return -1;
		}
	}

	/**
	 * Ham gui email có kèm file attach
	 * 
	 * @param receiverEmail
	 *            email cua nguoi nhan
	 * @param filepath
	 *            duong dan tuyet doi toi file attach can gui kem
	 * @param subject
	 *            noi dung email
	 * @return
	 * @throws Exception
	 */
	public static int sentEmailWithAttach(String receiverEmail, String listEmailcc, String[] filepath, String subject,
			String emailContent, Boolean isHmtl) throws Exception {
		try {
			// String spName = "";
			String to = receiverEmail;
			String from = MainConfig.mailFrom;
			// String Path = "";
			String msgText1 = emailContent;

			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, MailConfig.log_file_duration);
			// DateFormat dateFormat = new SimpleDateFormat("MM_yyyy");
			// String dateLog = dateFormat.format(cal.getTime());
			// Path = MainConfig.urlFolder + "/" + dateLog;

			// ---------------------------------------------
			// config mail
			// create some properties and get the default Session
			Properties props = getMailProper();

			if (session == null)
				session = Session.getInstance(props, new javax.mail.Authenticator() {
					@Override
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(MainConfig.mailFrom, MainConfig.mailFromPass);
					}
				});
			// from address
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			// to address

			InternetAddress[] addressTo = convertListEmail(to);
			InternetAddress[] addressCc = convertListEmail(listEmailcc);

			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);
			if (addressCc != null) {
				msg.setRecipients(javax.mail.Message.RecipientType.CC, addressCc);
			}
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setContent(msgText1, "text/html; charset=UTF-8");

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);

			// set the Date: header
			msg.setSentDate(new Date());

			msg.setSubject(subject);
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();

			// Create the HTML Part
			BodyPart htmlBodyPart = new MimeBodyPart(); // 4
			htmlBodyPart.setContent(msgText1, "text/html; charset=UTF-8"); // 5

			mp.addBodyPart(htmlBodyPart); // 6

			// filepath = new String[1];
			// filepath[0] = "./Reports/2016/pikachu/10/15/CG_pikachu.xls";
			if (filepath != null) {
				for (int i = 0; i < filepath.length; i++) {
					// attach the file to the message
					MimeBodyPart mbp2 = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(filepath[i]);
					log.info("SEND TO RECEIVER:" + receiverEmail + ", FILE TO SEND:" + filepath[i]);
					mbp2.setDataHandler(new DataHandler(fds));
					mbp2.setFileName(fds.getName());
					mbp2.attachFile(filepath[i]);
					mp.addBodyPart(mbp2);
					// addAttachment(mp, filepath[i]);
				}
			}

			msg.setContent(mp);
			// msg.saveChanges();
			try {
				Transport.send(msg);
				log.info("SEND SETTLE MAIL SUCCESS TO RECEIVER: " + receiverEmail);
			} catch (Exception e) {
				e.printStackTrace();
				log.fatal("SEND SETTLE MAIL FAIL", e);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("SEND SETTLE MAIL FAIL", e);
			return -1;
		}
	}

	/**
	 * Convert danh sach email config ra thanh arr InternetAddress[]
	 * 
	 * @param listEmailConfig
	 * @return
	 */
	private static InternetAddress[] convertListEmail(String listEmailConfig) {
		try {
			if(Commons.isEmpty(listEmailConfig))
				return null;
			String[] recipientArr = null;
			if (listEmailConfig.contains(";"))
				recipientArr = listEmailConfig.split(";");
			else if (listEmailConfig.contains(","))
				recipientArr = listEmailConfig.split(",");
			else
				recipientArr = listEmailConfig.split(",");

			InternetAddress[] internetAddresses = new InternetAddress[recipientArr.length];

			for (int i = 0; i < recipientArr.length; i++) {
				internetAddresses[i] = new javax.mail.internet.InternetAddress(recipientArr[i]);
			}

			return internetAddresses;
		} catch (Exception e1) {
			log.info("RECEIPIENT INVALID" + listEmailConfig + "");
		}
		return null;
	}
}