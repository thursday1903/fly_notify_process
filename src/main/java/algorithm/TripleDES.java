package algorithm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.buf.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;

import springboot.logs.Logs;


public class TripleDES {

	final static String HARD_KEY = "thiSISatempkey";
	final static Logs logger = new Logs(TripleDES.class);

	public static String Encrypt(String plainText, String key) {
		try {
			byte[] arrayBytes = HexUtils.fromHexString(key);
			// getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] plainByte = plainText.getBytes("UTF8");
			byte[] encryptedByte = cipher.doFinal(plainByte);

			return Base64Utils.encodeToString(encryptedByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ham giai du lieu tu client dang web
	 * 
	 * @param key
	 * @param cipher
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchProviderException
	 */
	public static String decrypt(String key, String cipher)
			throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		byte[] bytes = null;
		SecretKey sKey = null;
		Security.addProvider(new BouncyCastleProvider());
		Cipher encipher = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
		bytes = hexToBytes(cipher);
		// bytes=Base64Utils.base64Decode(cipher);
		// bytes = hexToBytes(cipher);
		sKey = getKey(key);
		// Encrypt
		byte[] enc;
		encipher.init(Cipher.DECRYPT_MODE, sKey);
		enc = encipher.doFinal(bytes);
		String returnStr = new String(enc, "UTF-8");
//		System.out.println("DU LIEU SAU GIAI MA=" + returnStr);
		logger.info("DU LIEU SAU GIAI MA=" + returnStr);
		return returnStr;
	}

	/**
	 * Ham encrypt du lieu tu client dang web
	 * 
	 * @param key
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 */
	public static String encrypt(String key, String input)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException, NoSuchProviderException {
		byte[] bytes = null;
		SecretKey sKey = null;
		Security.addProvider(new BouncyCastleProvider());
		Cipher encipher = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
		bytes = input.getBytes("UTF-8");
		sKey = getKey(key);
		// Encrypt
		byte[] enc;
		encipher.init(Cipher.ENCRYPT_MODE, sKey);
		enc = encipher.doFinal(bytes);
		return bytesToHex(enc);
	}

	private static SecretKey getKey(String key) {
		// key=TripleDESEncryption.md5(key);
		key = key.substring(0, 24);
		byte[] bKey = key.getBytes();
		try {
			DESedeKeySpec keyspec = new DESedeKeySpec(bKey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

			SecretKey lclSK = keyFactory.generateSecret(keyspec);

			return lclSK;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public static String EncryptHex(String plainText, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] plainByte = plainText.getBytes("UTF8");
			byte[] encryptedByte = cipher.doFinal(plainByte);
			return HexUtils.toHexString(encryptedByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String EncryptHexHexKey(String plainText, String key) {
		try {
			logger.info("DU LIEU TRUOC KHI TRA VE:" + plainText);
			byte[] arrayBytes = HexUtils.fromHexString(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] plainByte = plainText.getBytes("UTF8");
			byte[] encryptedByte = cipher.doFinal(plainByte);
			return HexUtils.toHexString(encryptedByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Decrypt(String encryptData, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);

			byte[] encryptByte = Base64Utils.decodeFromString(encryptData);
			byte[] plainByte = cipher.doFinal(encryptByte);
			return new String(plainByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String DecryptHexHexKey(String encryptData, String key) {
		try {
			byte[] arrayBytes = HexUtils.fromHexString(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			// BASE64Decoder decode = new BASE64Decoder();
			byte[] encryptByte = HexUtils.fromHexString(encryptData);
			byte[] plainByte = cipher.doFinal(encryptByte);
			String plainText = new String(plainByte);
			logger.info("DU LIEU CLEAR>>>>>>>>>:" + plainText);
			return plainText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] DecryptHexReturnByte(String encryptData, String key) {
		byte[] plainByte = null;
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			// BASE64Decoder decode = new BASE64Decoder();

			byte[] encryptByte = hexToBytes(encryptData);
			plainByte = cipher.doFinal(encryptByte);
			return plainByte;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getValidKey(String key) {
		try {
			String sTemp = MD5.hash(key).substring(0, 24);
			return sTemp.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decryptHardKey(String encryptData) {
		return Decrypt(encryptData, HARD_KEY);
	}

	public static String generateHexKey() {
		// Get a key generator for Triple DES (a.k.a DESede)
		KeyGenerator keygen;

		// 676b8a1085cdb3ec20629d5731bf8ff7
		// 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
		try {
			keygen = KeyGenerator.getInstance("DESede");
			keygen.init(168);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
			DESedeKeySpec keyspec;
			try {
				keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
				byte[] rawkey = keyspec.getKey();

				return bytesToHex(rawkey);
				// return bytesToHex(rawkey);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		// Use it to generate a key
	}

	public static String generateBase64StringKey() {
		// Get a key generator for Triple DES (a.k.a DESede)
		KeyGenerator keygen;

		// 676b8a1085cdb3ec20629d5731bf8ff7
		// 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
		try {
			keygen = KeyGenerator.getInstance("DESede");
			keygen.init(112);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
			DESedeKeySpec keyspec;
			try {
				keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
				byte[] rawkey = keyspec.getKey();

				return Base64Utils.encodeToString(rawkey);
				// return bytesToHex(rawkey);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		// Use it to generate a key
	}

	public static String generateBase64StringKeyDes() {
		// Get a key generator for Triple DES (a.k.a DESede)
		KeyGenerator keygen;

		// 676b8a1085cdb3ec20629d5731bf8ff7
		// 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
		try {
			keygen = KeyGenerator.getInstance("DES");
			keygen.init(168);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
			DESedeKeySpec keyspec;
			try {
				keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
				byte[] rawkey = keyspec.getKey();

				return Base64Utils.encodeToString(rawkey);
				// return bytesToHex(rawkey);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		// Use it to generate a key
	}

	public static String bytesToHex(final byte[] bytes) {
		final StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (final byte b : bytes) {
			final String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				buf.append("0");
			}
			buf.append(hex);
		}
		return buf.toString();
	}

	public static byte[] hexToBytes(final String hex) {
		final byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
		}
		return bytes;
	}
	
	public static void main(String[] args) {
		String cipher = "caa052c8afc214f9ac1baba9df39486b6ce5a61cb00a5a33f73ab7c96f0b0d18fe612eff62bc80c176811dc467acd38f345fcce2984a8a8ddfa2a145053a13819ae08c610ae7b75672ce57364aa4bb96ab0502f57eab3616216d1c79a0628093077a70edef3b61e046f7ff2bfa9ece03f2f24f2b0b9c8eeaf45e155a206b3de5d501d3806d54436b24d0cfa479b7a2327f77f7d5ff38592c5920bc5fd0052c4bedbca5563329a4f4f0a2b90a3711bdad43e790786dfbf22714197ecea39aa38b6ebc79c237bb6938a0db11d0a92945b4b30c630eee7280d90d915966d1712a72ea20c11f3eb769c50e30b4788891f113d722a221f1e2a6789f56251c367f249eb335673b2f56330029f1eef22aa4b13ba93a27f2bb19bf7f8cf1d3b66ab4f9230a2d4ea470113edebb5685968ff2cbb6c6d373e82279507f49d96a878d4ed50dad3d405953f4fb72";
		String key = "1f2f8c540779987923165751";

		String clear = "{  \"balChangeType\": 0,  \"balChangeStatus\": 0,  \"balChangeAmount\": 101000,  \"createdBy\": \"0985023151\",  \"accId\": 910,  \"client_request_id\":202006091643581231231231335356}";
		try {
			System.out.println(decrypt(key, cipher));

			System.out.println("DL MA HOA:" + encrypt(key, clear));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("KEY:" + generateBase64StringKey());
		System.out.println("KEY DES:" + generateBase64StringKeyDes());
	}
}