package springboot.main;

public final class MainSample {

	public static void main(String[] args) {
		try {
			// Doc danh sach port can start app
			// Runtime runtime = Runtime.getRuntime();
			// Process process = runtime.exec("");
			// Thread.sleep(5000);
			Boolean boo = Boolean.parseBoolean("2");

			System.out.println(boo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
