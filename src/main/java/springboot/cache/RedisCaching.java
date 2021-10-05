package springboot.cache;

import org.springframework.data.redis.connection.RedisConnection;

import springboot.utils.GsonUltilities;

/**
 * @author vietanh
 *
 */
public class RedisCaching {

	private static RedisCaching instance;

	public static RedisCaching getInstance() {
		if (instance == null)
			synchronized (RedisCaching.class) {
				new RedisCaching();
			}
		return instance;
	}

	public RedisCaching() {
		instance = this;
	}

	final long CACHE_TIMEOUT = 3600;

	/**
	 * Lưu object vào cache
	 * 
	 * @param object
	 * @param key
	 */
	public void cacheObject(Object object, String hashName, String key) {
		key = hashName + key;
		RedisConnection redisConnection = null;
		try {
			redisConnection = RedisDataConfig.redisConnectionFactory().getConnection();

			String input = GsonUltilities.toJson(object);
			// redisConnection.set(key.getBytes(), guId.getBytes());
			if (!redisConnection.exists(key.getBytes())) {
				redisConnection.set(key.getBytes(), input.getBytes());
				redisConnection.expire(key.getBytes(), CACHE_TIMEOUT);
			}
		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			RedisDataConfig.releseConnection(redisConnection);
		}
	}

	/**
	 * Lay thông tin từ cache theo key String
	 * 
	 * @param key
	 */
	public Object getObject(String hashName, String key) {
		key = hashName + key;
		RedisConnection redisConnection = null;
		try {
			redisConnection = RedisDataConfig.redisConnectionFactory().getConnection();

			byte[] retByte = redisConnection.get(key.getBytes());
			return new String(retByte);
		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			RedisDataConfig.releseConnection(redisConnection);
		}
		return null;
	}

	/**
	 * Đẩy message bất kỳ vào queue
	 * 
	 * @param queueName
	 * @param message
	 * @return
	 */
	public Boolean pushMessageToQueue(String queueName, String message) {
		RedisConnection redisConnection = null;
		try {
			redisConnection = RedisDataConfig.redisConnectionFactory().getConnection();

			long index = redisConnection.rPush(queueName.getBytes(), message.getBytes());
			return true;
		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			RedisDataConfig.releseConnection(redisConnection);
		}
		return false;
	}

	public Object dequeueMessageFromQueue(String queueName, String message) {
		RedisConnection redisConnection = null;
		try {
			redisConnection = RedisDataConfig.redisConnectionFactory().getConnection();

			byte[] queueMessage = redisConnection.lPop(queueName.getBytes());
			return queueMessage;
		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			RedisDataConfig.releseConnection(redisConnection);
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
