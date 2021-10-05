package springboot.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import springboot.config.MainConfig;
import springboot.hbn.entities.TblServiceKeys;
import springboot.hbn.home.TblServiceKeysHome;
import springboot.sso.bussiness.NotifyBussiness;

@Configuration
@EnableScheduling
public class ScheduleConfigue {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	final static TblServiceKeysHome TBL_SERVICE_KEYS_HOME = new TblServiceKeysHome();

	public static void main(String[] args) {
		SpringApplication.run(ScheduleConfigue.class, args);
	}

	@Async
	@Scheduled(fixedRate = 60 * 1000)
	public void ScheduleConfigue() {
		try {
			System.out.println("RELOAD SERVICE");
			MainConfig.GetInstance().reloadConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Async
	@Scheduled(fixedRate = 10 * 1000)
	public void scheduleLoadQueue() {
		try {
			System.out.println("START SCAN QUEUE NOTIFY");
			new NotifyBussiness().process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
