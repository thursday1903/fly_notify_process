package algorithm;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import io.netty.handler.codec.base64.Base64Encoder;

public class HashMac {

	public static String encode(String key, String data) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);

		return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}

	public static void main(String[] args) {
		String data = "{ \"shipper_id\": 921, \"status\": \"Arrived at Sorting Hub\", \"shipper_ref_no\": \"149431\", \"tracking_ref_no\": \"149431\", \"shipper_order_ref_no\": \"8374\", \"timestamp\": \"2017-04-03T11:50:44+0800\", \"id\": \"3b7327b9-54bf-417f-3104-f4e155a22308\", \"previous_status\": \" Van en-route to pickup\", \"tracking_id\": \"DX149431\", \"comments\": \"SG-Singapore-Ninja Van Sorting Facility\" }";
		try {
			System.out.println(encode("my_shared_secret", data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
