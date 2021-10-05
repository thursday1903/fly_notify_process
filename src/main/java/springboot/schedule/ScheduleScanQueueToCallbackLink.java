package springboot.schedule;

import org.springframework.boot.SpringApplication;

public class ScheduleScanQueueToCallbackLink extends java.util.TimerTask {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleScanQueueToCallbackLink.class, args);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		Bussiness.getInstance().scanQueueToCreateCallbackLink();
	}

}
