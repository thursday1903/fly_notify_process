package springboot.sso.bussiness;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Component;

import springboot.cache.RedisDataConfig;
import springboot.config.MainConfig;
import springboot.config.YAMLConfig;
import springboot.logs.Logs;
import springboot.service.entities.NotifyMessageTransfer;
import springboot.utils.EmailProcess;
import springboot.utils.EmailProcessExchange;
import springboot.utils.GsonUltilities;

/**
 * @author vietanh Lop xu ly notify
 */
@EnableAutoConfiguration
@Configuration
@Component
public class NotifyBussiness {

	@Autowired
	private YAMLConfig myConfig;
	final static Logs LOGGER = new Logs(NotifyBussiness.class);

//	private static NotifyBussiness instance;

//	public NotifyBussiness() {
//
//		// TODO Auto-generated constructor stub
//	}
//
//	public static NotifyBussiness getInstance() {
//		if (instance == null) {
//			synchronized (NotifyBussiness.class) {
//				new NotifyBussiness();
//			}
//		}
//		return instance;
//	}

	public String processNotifyMessage(NotifyMessageTransfer notifyMessageTransfer) {
		try {
			int messageType = notifyMessageTransfer.getMessage_type();
			switch (messageType) {
			case 1:// email
				LOGGER.info("Du lieu nhan duoc: " + notifyMessageTransfer.toString());
				int sendEmailStatus = EmailProcess.getInstance().sendEmailAlert(
						notifyMessageTransfer.getReceive_email_expect(),
						notifyMessageTransfer.getReceive_email_expect_cc(), null, notifyMessageTransfer.getSubject(),
						notifyMessageTransfer.getContent(), notifyMessageTransfer.getIs_html());

				break;
			case 2:// sms

				break;
			case 3:// tele
				composeAlertToTele(notifyMessageTransfer.getContent(),
						notifyMessageTransfer.getReceive_chat_id_expect());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		return null;
	}

	/**
	 * Xu ly queue de notify
	 */
	public void process() {
		// TODO Auto-generated method stub
		String[] listQueuName = MainConfig.LIST_QUEUENAME_NEED_TO_SCAN.split(",");
		for (String string : listQueuName) {
			LOGGER.info("START SCAN QUEUE NOTIFY OF:" + string);
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					RedisConnection conn = null;
					try {
						conn = RedisDataConfig.redisConnectionFactory().getConnection();
						if (string.equalsIgnoreCase("BILL_NOTIFER")) {
							conn.select(7);
						} else if (string.equalsIgnoreCase("HOLA_NOTIFER")) {
							conn.select(0);
						} else {
							conn.select(Integer.parseInt(MainConfig.dataFiles));
						}
						byte[] bData = conn.lPop(string.getBytes());
						LOGGER.info("LPOP QUEUE----------- " + bData);
						if (bData == null)
							return;
						LOGGER.info("DU LIEU CAN XU LY:" + new String(bData));
						while (bData != null) {
							String outputMessage = new String(bData, "UTF-8");
							LOGGER.info("DATA BEFORE FROMJSON---------" + outputMessage);
							NotifyMessageTransfer notifyMessageTransfer = (NotifyMessageTransfer) GsonUltilities
									.fromJson(outputMessage, NotifyMessageTransfer.class);
							LOGGER.info("DATA---------" + notifyMessageTransfer);
							int messageType = notifyMessageTransfer.getMessage_type();
							switch (messageType) {
							case 1:// email
								LOGGER.info("gui email");
								String emailList = notifyMessageTransfer.getReceive_email_expect();
								LOGGER.info("EMAIL GUI DI: " + emailList);
								String cc = notifyMessageTransfer.getReceive_email_expect_cc();
								LOGGER.info("EMAIL CC: " + cc);
								notifyMessageTransfer.setReceive_email_expect_cc(MainConfig.listAdminEmailBCC);
								int sendEmailStatus = EmailProcess.getInstance().sendEmail(notifyMessageTransfer);
								System.out.println("KET QUA GUI EMAIL:" + sendEmailStatus + "=>>>"
										+ notifyMessageTransfer.getContent());
								if (sendEmailStatus < 0) {
									new Thread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											try {
												LOGGER.fatal("Retry push message to queue.......");
												// Delay push lại queue sau 1 khoảng tgian
												Thread.sleep(60000);
												pushTransactionToRedisQueue(outputMessage, string);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}).start();
								}
								break;
							case 2:// sms

								break;
							case 3:// tele
								LOGGER.info("gui tele");
								String chatId = notifyMessageTransfer.getReceive_chat_id_expect() == null
										? MainConfig.TELE_CHATID
										: notifyMessageTransfer.getReceive_chat_id_expect();
								composeAlertToTele(notifyMessageTransfer.getContent(), chatId);
								break;
							default:
								break;
							}
							bData = conn.lPop(string.getBytes());
						}
					} catch (Exception e) {
						// TODO: handle exception
						LOGGER.fatal("Co mot loi j do", e);
					} finally {
						RedisDataConfig.releseConnection(conn);
					}
				}
			}).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Boolean pushTransactionToRedisQueue(String data, String queueName) {
		RedisConnection conn = null;
		try {
			conn = RedisDataConfig.redisConnectionFactory().getConnection();
			conn.rPush(queueName.getBytes(), data.getBytes("utf-8"));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			RedisDataConfig.releseConnection(conn);
		}
		return false;
	}

	public void composeAlertToTele(String message, String teleChatId) {
		String url = MainConfig.TELE_URL;
		HttpPost post = new HttpPost(url);

		// add request parameter, form parameters
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair("chat_id", teleChatId));
		urlParameters.add(new BasicNameValuePair("text", message));
		// urlParameters.add(new BasicNameValuePair("custom", "secret"));

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			CloseableHttpClient httpClient = HttpClients.createDefault();
			try {
				CloseableHttpResponse response = httpClient.execute(post);
//				System.out.println(EntityUtils.toString(response.getEntity()));
				LOGGER.info("send tele rs:" + EntityUtils.toString(response.getEntity()));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				LOGGER.fatal("send tele rs:", e);
			} finally {
				httpClient.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.fatal("send tele rs:", e);
		}
	}

	public static void main(String[] args) {
		try {
//			final String QUEUE_NAME = "QUEUE_NAME_";
//			RedisConnection conn = null;
//			conn = RedisDataConfig.redisConnectionFactory().getConnection();
//			NotifyMessageTransfer notifyMessageTransfer = new NotifyMessageTransfer();
//			notifyMessageTransfer.setContent("<html><body>Pikachu mèo mèo gru</body></html>");
//			notifyMessageTransfer.setMessage_type(1);
//			notifyMessageTransfer.setSubject("What do cuu");
//
//			notifyMessageTransfer.setIs_html(true);
//			conn.rPush(QUEUE_NAME.getBytes("utf-8"), GsonUltilities.toJson(notifyMessageTransfer).getBytes("utf-8"));
//			System.out.println("QUEUE ON SUCCESS");
			NotifyBussiness notifyBussiness = new NotifyBussiness();
			String subject = "[BILL -  ACCOUNT: imd_dev]";
			String content = "28-12-2020 10:24:58\n[BILL -  ACCOUNT: imd_dev]\n So du hien tai dang thap hon muc yeu cau! So du hien tai: 9999999";
			int message_type = 1;
			boolean is_html = false;
			String receive_email_expect = "huyvd@imediatech.com.vn";
			String receive_sms_expect = "";
			String receive_chat_id_expect = "";
			String service_code = "BILL";
			String sub_service_code = "";
			EmailProcess.getInstance().sendEmailAlert(receive_email_expect, null, null, subject, content, is_html);
//			notifyBussiness.composeAlertToTele("Hello tele", "-452728715");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
