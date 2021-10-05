package springboot.schedule;

import org.springframework.boot.SpringApplication;

/**
 * @author vietanh
 * Schedule load các bill cần thanh toán tự động để xử lý
 */
public class ScheduleScanQueueToLoadBill extends java.util.TimerTask {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleScanQueueToLoadBill.class, args);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		MainBussiness.getInstance().scanBillingPendingToProcess();
	}

}
