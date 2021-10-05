package springboot.utils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BussinesStringGenerator {

	private static BussinesStringGenerator instance;

	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	private Random random = new Random(System.currentTimeMillis());

	public static BussinesStringGenerator getInstance() {
		if (instance == null) {
			new BussinesStringGenerator();
		}
		return instance;
	}

	public BussinesStringGenerator() {
		super();
		// TODO Auto-generated constructor stub
		instance = this;
	}

	public String generateUniqueTransId() {
		String current = DateConvert.dateToString(new Date(), "yyyyMMddHHmmssSSS");
		int ranNumber = random.nextInt(9999);
		String nextIntNumber = String.format("%04d", atomicInteger.getAndIncrement());
		if (atomicInteger.get() == 9999) {
			synchronized (atomicInteger) {
				System.out.println("RESET COUNTER");
				atomicInteger.set(0);
			}
		}
		return current + ranNumber + nextIntNumber;

	}

	/**
	 * Sinh mot chuoi random
	 * 
	 * @return
	 */
	public String generateRandomString() {
		int randomNumber = random.nextInt(99999999);
		return randomNumber + "";
	}

	/**
	 * Sinh chuoi n ky tu gom ca so va chu
	 * @param n
	 * @return
	 */
	public String generateRandomMixString(int n, Boolean onlyNumber)
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		if (onlyNumber) {
			AlphaNumericString = "0123456789987654321";
		}
		// chose a Character random from this String
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("NEW TRANSID:" + BussinesStringGenerator.getInstance().generateUniqueTransId());
				}
			}).start();

		}

	}
}
