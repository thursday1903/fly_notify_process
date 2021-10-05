package springboot;

import java.io.PrintWriter;
import java.io.StringWriter;

import springboot.config.MainConfig;
import springboot.utils.EmailProcess;
import springboot.utils.GsonUltilities;

public class ExceptionHandle {

	private static ExceptionHandle instance;

	public static ExceptionHandle getInstance() {
		if (instance == null) {
			new ExceptionHandle();
		}
		return instance;
	}

	public ExceptionHandle() {
		super();
		// TODO Auto-generated constructor stub
		instance = this;
	}

	public void handleException(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String fullStackTrace = sw.toString();

		try {
			EmailProcess.getInstance().sendEmailAlert(MainConfig.listAdminEmail, null, null,
					"CORE-FIRM:CANH BAO LOI HE THONG CHI", fullStackTrace, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void handleException(Exception e, Object refeObj) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String jsonObject = GsonUltilities.toJson(refeObj);
		String fullStackTrace = jsonObject + "\r" + sw.toString();

		try {
			EmailProcess.getInstance().sendEmailAlert(MainConfig.listAdminEmail, null, null,
					"CORE-FIRM:CANH BAO LOI HE THONG CHI", fullStackTrace, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {

			Integer.parseInt("r");
		} catch (Exception e2) {
			// TODO: handle exception
			ExceptionHandle.getInstance().handleException(e2);
		}

	}
}
