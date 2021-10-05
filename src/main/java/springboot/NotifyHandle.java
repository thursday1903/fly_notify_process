package springboot;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import springboot.service.entities.NotifyMessageTransfer;
import springboot.utils.GsonUltilities;

public class NotifyHandle {
	public static void main(String[] args) {
		String a = "Xin chào Shop [Account_id]!\r\n"
				+ "Vào thời gian [Time] HolaShip.vn đang ghi nhận đơn hàng Chờ Hoàn của shop với mã: [Order_id]. Quý khách vui lòng kiểm tra và phản hồi lại HolaShip.vn nhé.\r\n"
				+ "Lưu ý: thời gian nhận phản hồi Giao Lại cho các đơn hàng này là 24 tiếng tính từ thời điểm gửi email. Nếu không nhận được phản hồi của shop trong thời gian quy định trên thì HolaShip.vn sẽ mặc định chuyển hoàn các đơn hàng này.\r\n"
				+ "Mọi thắc mắc xin vui lòng liên hệ Chăm sóc khách hàng của HolaShip.vn qua hotline: 1900 2345 39 hoặc email: cj@imediatech.com.vn\r\n"
				+ "Cảm ơn bạn đã tin tưởng sử dụng dịch vụ HolaShip.vn.\r\n"
				+ "Trân trọng, \r\n"
				+ "BQT HolaShip.vn\r\n";
		String b = "Xin ch\\xE0o Shop 0328253285!\\r\\nV\\xE0o th?i gian 16:37:07 29-12-2020 HolaShip.vn ?ang ghi nh?n ??n h\\xE0ng Ch? Ho\\xE0n c?a shop v?i m\\xE3: 201225022867. Qu\\xFD kh\\xE1ch vui l\\xF2ng ki?m tra v\\xE0 ph?n h?i l?i HolaShip.vn nh\\xE9.\\r\\nL?u \\xFD: th?i gian nh?n ph?n h?i Giao L?i cho c\\xE1c ??n h\\xE0ng n\\xE0y l\\xE0 24 ti?ng t\\xEDnh t? th?i ?i?m g?i email. N?u kh\\xF4ng nh?n ???c ph?n h?i c?a shop trong th?i gian quy ??nh tr\\xEAn th\\xEC HolaShip.vn s? m?c ??nh chuy?n ho\\xE0n c\\xE1c ??n h\\xE0ng n\\xE0y.\\r\\nM?i th?c m?c xin vui l\\xF2ng li\\xEAn h? Ch?m s\\xF3c kh\\xE1ch h\\xE0ng c?a HolaShip.vn qua hotline: 1900 2345 39 ho?c email: cj@imediatech.com.vn\\r\\nC?m ?n b?n ?\\xE3 tin t??ng s? d?ng d?ch v? HolaShip.vn.\\r\\nTr\\xE2n tr?ng, \\r\\nBQT HolaShip.vn\\r\\n";
		String messageFull = a.replace("[Account_id]", "111").replace("[Time]", "3213213").replace("[Order_id]", "12344444");
		System.out.println("A-------------" + a);
		byte[] bytes = messageFull.getBytes();
		String messageEmail = null;
		try {
			messageEmail = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("B-----------" + messageEmail);
		
	}
}
