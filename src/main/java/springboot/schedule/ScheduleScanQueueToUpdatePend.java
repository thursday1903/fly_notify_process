package springboot.schedule;

import org.springframework.boot.SpringApplication;

/**
 * @author vietanh
 * Schedule lấy dữ liệu từ queue đê cập nhật trạng thái cuối cùng của giao dịch
 */
public class ScheduleScanQueueToUpdatePend extends java.util.TimerTask {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleScanQueueToUpdatePend.class, args);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		MainBussiness.getInstance().scanPendingTransactionToUpdateFinalStatus();
	}

}
