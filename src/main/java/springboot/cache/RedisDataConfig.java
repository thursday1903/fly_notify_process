package springboot.cache;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;

import redis.clients.jedis.JedisPoolConfig;
import springboot.config.MainConfig;

@ContextConfiguration(classes = RedisDataConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Configuration
public class RedisDataConfig {

	static JedisConnectionFactory factory = null;

	@Bean
	public static synchronized RedisConnectionFactory redisConnectionFactory() throws UnknownHostException {
		if (factory == null) {
			System.out.println("INNIT POOL");
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(MainConfig.REDIS_MAX_CONN);
			poolConfig.setMinIdle(MainConfig.REDIS_MIN_IDLE);
			poolConfig.setMaxIdle(MainConfig.REDIS_MAX_IDLE);
			poolConfig.setBlockWhenExhausted(true);

			factory = new JedisConnectionFactory(poolConfig);
			factory.setHostName(MainConfig.REDIS_HOST);
			factory.setUsePool(true);
			factory.setPort(MainConfig.REDIS_PORT);
			factory.setDatabase(MainConfig.REDIS_DATAFILE);
			if (MainConfig.REDIS_ENABLE_AUTHEN)
				factory.setPassword(MainConfig.REDIS_PASSWORD);
			System.out.println("GET Use poll:" + factory.getUsePool());
		}
		return factory;
	}

	@Bean(name = "redisUserTemplate")
	public RedisTemplate<String, String> redisTemplateUser(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	private void testSaveObj() {

	}

	public static Boolean releseConnection(RedisConnection conn) {
		try {
			if (conn != null)
				conn.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {

		// new RedisDataConfig().testSaveObj();
		//
		// for (int i = 0; i < 10; i++) {
		// // String string = args[i];
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// RedisConnection conn = null;
		// try {
		// conn = RedisDataConfig.redisConnectionFactory().getConnection();
		// conn.set("KEY".getBytes(), "VALUE HERE".getBytes());
		//
		// byte[] data = conn.get("KEY".getBytes());
		//
		// System.out.println(new String(data));
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// } finally {
		// conn.close();
		// }
		// }
		// }).start();
		//
		// }

	}
}