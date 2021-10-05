package springboot;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TelegramBussiness {

	public static void main(String[] args) {
		String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

		String apiToken = "my_bot_api_token";
		String chatId = "@my_channel_name";
		String text = "Hello world!";

		urlString = String.format(urlString, apiToken, chatId, text);

		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			StringBuilder sb = new StringBuilder();
			InputStream is = new BufferedInputStream(conn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine = "";
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			String response = sb.toString();
			// Do what you want with response
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
