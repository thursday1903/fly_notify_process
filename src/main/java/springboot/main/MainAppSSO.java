package springboot.main;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springboot.Controller;
import springboot.cache.RedisDataConfig;
import springboot.config.YAMLConfig;
import springboot.hbn.home.ServicesRegister;
import springboot.interfaces.ILog;
import springboot.schedule.TaskManager;
import springboot.utils.Classloader;
import springboot.utils.Commons;

@SpringBootApplication(exclude = { RedisRepositoriesAutoConfiguration.class })
@ComponentScan(basePackageClasses = Controller.class)
@EnableAutoConfiguration
public class MainAppSSO {

	@Autowired
	private YAMLConfig myConfig;

	public MainAppSSO() {
		// TODO Auto-generated constructor stub
	}

	// @Bean
	// public PropertySourcesPlaceholderConfigurer
	// propertySourcesPlaceholderConfigurer() {
	// System.setProperty("javax.net.ssl.trustStore",
	// "./config/ssl/ssl-server.jks");
	// PropertySourcesPlaceholderConfigurer properties = new
	// PropertySourcesPlaceholderConfigurer();
	// properties.setLocation(new
	// FileSystemResource("./config/ssl/application.properties"));
	// properties.setIgnoreResourceNotFound(false);
	// return properties;
	// }

	@SuppressWarnings("deprecation")
	private final class WebMvcConfigurerAdapterExtension extends WebMvcConfigurerAdapter {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		}
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapterExtension();
	}

	public static void main(String[] args) {
		Classloader.loadLib();
		String defaultCharacterEncoding = System.getProperty("file.encoding");
		System.out.println("defaultCharacterEncoding by property: " + defaultCharacterEncoding);
		System.setProperty("file.encoding", "UTF-8");
		defaultCharacterEncoding = System.getProperty("file.encoding");
		System.out.println("defaultCharacterEncoding by property reset: " + defaultCharacterEncoding);
		setTimeZone();

		Boolean continue_ = true;
		try {
			RedisDataConfig.redisConnectionFactory();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("KET NOI REDIS THAT BAI, KIEM TRA LAI CACHING");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				continue_ = false;
			}
			System.exit(5);
		}
		if (continue_) {

			SpringApplication app = new SpringApplication(MainAppSSO.class);
			app.setWebEnvironment(false); // <<<<<<<<<
			ConfigurableApplicationContext ctx = app.run(args);
		}
	}

	// @Bean
	// public ServletWebServerFactory servletContainer() {
	// TomcatServletWebServerFactory tomcat = new
	// TomcatServletWebServerFactory() {
	// @Override
	// protected void postProcessContext(Context context) {
	// SecurityConstraint securityConstraint = new SecurityConstraint();
	// securityConstraint.setUserConstraint("CONFIDENTIAL");
	// SecurityCollection collection = new SecurityCollection();
	// collection.addPattern("/*");
	// securityConstraint.addCollection(collection);
	// context.addConstraint(securityConstraint);
	// }
	// };
	// tomcat.addAdditionalTomcatConnectors(redirectConnector());
	// return tomcat;
	// }
	//
	// private Connector redirectConnector() {
	// Connector connector = new
	// Connector("org.apache.coyote.http11.Http11NioProtocol");
	// connector.setScheme("http");
	// connector.setPort(8080);
	// connector.setSecure(false);
	// connector.setRedirectPort(8443);
	//
	// return connector;
	// }

	private static void setTimeZone() {
		String defaultTimeZone = "Asia/Bangkok";
		// checking default time zone
		String timezoneId = defaultTimeZone;
		// Calendar.getInstance().getTimeZone().getID();
		// create time zone object

		TimeZone tzone = TimeZone.getTimeZone(timezoneId);

		System.out.println("Current TZ:" + tzone);
		// set time zone to default
		tzone.setDefault(tzone);

		// checking default time zone
		System.out.println("Default time zone:" + tzone);
	}

	@SuppressWarnings("resource")
	@Bean
	public CommandLineRunner commandLineRunner() {
		try {
			return args -> {
				System.out.println("SERVER CONFIG:........");
				System.out.println(myConfig.getEnvironment());
				System.out.println(myConfig.getName());
				System.out.println(myConfig.getServers());
				System.out.println(myConfig.getCard_url());

				System.out.println("Let's inspect the beans provided by Spring Boot:");
				ApplicationContext context = null;
				context = new FileSystemXmlApplicationContext("./config/Beans.xml");
				String[] beanNames = ((ListableBeanFactory) context).getBeanDefinitionNames();
				Arrays.sort(beanNames);
				for (String beanName : beanNames) {
					System.out.println(beanName);
				}
				ILog log = (ILog) ServicesRegister.shareInstance().context.getBean("LogManager");
				log.setClass(this.getClass());
				log.info("-----------_START NOTIFY API 1.0 INSTANCE SUCCESS AT ----------" + new Date());
				
			};
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
