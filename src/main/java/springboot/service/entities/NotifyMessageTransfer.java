package springboot.service.entities;

public class NotifyMessageTransfer {

	String subject;
	String content;
	Integer message_type;
//	[1:email,2:sms,3:telegram,4:other]
	Boolean is_html;
	String receive_email_expect;
	String receive_sms_expect;
	String receive_chat_id_expect;
	String service_code;
	String sub_service_code;
	String receive_email_expect_cc;
	String receive_email_expect_bcc;
	
	
	public String getReceive_email_expect_bcc() {
		return receive_email_expect_bcc;
	}

	public void setReceive_email_expect_bcc(String receive_email_expect_bcc) {
		this.receive_email_expect_bcc = receive_email_expect_bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMessage_type() {
		return message_type;
	}

	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}

	public Boolean getIs_html() {
		return is_html;
	}

	public void setIs_html(Boolean is_html) {
		this.is_html = is_html;
	}

	public String getReceive_email_expect() {
		return receive_email_expect;
	}

	public void setReceive_email_expect(String receive_email_expect) {
		this.receive_email_expect = receive_email_expect;
	}

	public String getReceive_sms_expect() {
		return receive_sms_expect;
	}

	public void setReceive_sms_expect(String receive_sms_expect) {
		this.receive_sms_expect = receive_sms_expect;
	}

	public String getReceive_chat_id_expect() {
		return receive_chat_id_expect;
	}

	public void setReceive_chat_id_expect(String receive_chat_id_expect) {
		this.receive_chat_id_expect = receive_chat_id_expect;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getSub_service_code() {
		return sub_service_code;
	}

	public void setSub_service_code(String sub_service_code) {
		this.sub_service_code = sub_service_code;
	}

	public String getReceive_email_expect_cc() {
		return receive_email_expect_cc;
	}

	public void setReceive_email_expect_cc(String receive_email_expect_cc) {
		this.receive_email_expect_cc = receive_email_expect_cc;
	}

	public NotifyMessageTransfer(String subject, String content, Integer message_type, Boolean is_html,
			String receive_email_expect, String receive_sms_expect, String receive_chat_id_expect, String service_code,
			String sub_service_code, String receive_email_expect_cc) {
		this.subject = subject;
		this.content = content;
		this.message_type = message_type;
		this.is_html = is_html;
		this.receive_email_expect = receive_email_expect;
		this.receive_sms_expect = receive_sms_expect;
		this.receive_chat_id_expect = receive_chat_id_expect;
		this.service_code = service_code;
		this.sub_service_code = sub_service_code;
		this.receive_email_expect_cc = receive_email_expect_cc;
	}

	@Override
	public String toString() {
		return "NotifyMessageTransfer [subject=" + subject + ", content=" + content + ", message_type=" + message_type
				+ ", is_html=" + is_html + ", receive_email_expect=" + receive_email_expect + ", receive_sms_expect="
				+ receive_sms_expect + ", receive_chat_id_expect=" + receive_chat_id_expect + ", service_code="
				+ service_code + ", sub_service_code=" + sub_service_code + ", receive_email_expect_cc="
				+ receive_email_expect_cc + "]";
	}

}
