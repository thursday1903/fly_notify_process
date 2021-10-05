package springboot.schedule;

import java.util.TimerTask;

import org.springframework.boot.SpringApplication;

public class ScheduleScanQueueToUpdateSimStt extends TimerTask {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleScanQueueToUpdateSimStt.class, args);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		MainBussiness.getInstance().scanToGetSimInfoFromCache();
	}
}
