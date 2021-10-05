package algorithm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RSA {
	private String private_key = "";
	private String public_key = "";

	public static void genKey() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);
			KeyPair keypair = keyGen.genKeyPair();
			;
			PrivateKey privateKey = keypair.getPrivate();
			;
			PublicKey publicKey = keypair.getPublic();
			//

			// private_key = encoder.encode(privateKey.getEncoded());
			// public_key = encoder.encode(publicKey.getEncoded());
			//
			// System.out.println("This is privatekey: \n" + private_key);
			// System.out.println("This is publickey: \n" + public_key);
			// // Write to file:
			// writeKeyBytesToFile(private_key.getBytes(),
			// "KEY/private_key.pem");
			// writeKeyBytesToFile(public_key.getBytes(), "KEY/public_key.pem");
			// //
			// RSAPublicKey rsaPublicKey = (RSAPublicKey)
			// KeyFactory.getInstance("RSA")
			// .generatePublic(new X509EncodedKeySpec(publicKey.getEncoded()));
			// String xml = getRSAPublicKeyAsXMLString(rsaPublicKey);
			// writeKeyBytesToFile(xml.getBytes(), "keyRSA/public_key.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeKeyBytesToFile(byte[] key, String file) throws IOException {
		OutputStream out = new FileOutputStream(file);
		out.write(key);
		out.close();
	}

	// Read key
	public static void initializeKeys() {
		// private_key = Readfile("./config/keyRSA/private_key.pem");
		// System.out.println("Read Private key:");
		// System.out.println(private_key);
		// public_key = Readfile("./config/keyRSA/public_key.pem");
		// System.out.println("Read Public key:");
		// System.out.println(public_key);
	}

	public RSA(String private_key, String public_key) {
		super();
		this.private_key = private_key;
		this.public_key = public_key;
	}

	public RSA() {
		// TODO Auto-generated constructor stub
	}

	private static String Readfile(String path) {
		String xau = "";
		try {
			FileInputStream fstream = new FileInputStream(path);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				xau += strLine + " ";
			}
			xau = xau.trim();
			xau = xau.replace(" ", "\n");
			br.close();
			in.close();
			fstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xau;
	}

	private static String getRSAPublicKeyAsXMLString(RSAPublicKey key)
			throws UnsupportedEncodingException, ParserConfigurationException, TransformerException {
		Document xml = getRSAPublicKeyAsXML(key);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StringWriter sw = new StringWriter();
		transformer.transform(new DOMSource(xml), new StreamResult(sw));
		return sw.getBuffer().toString();
	}

	private static Document getRSAPublicKeyAsXML(RSAPublicKey key)
			throws ParserConfigurationException, UnsupportedEncodingException {
		Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element rsaKeyValue = result.createElement("RSAKeyValue");
		result.appendChild(rsaKeyValue);
		Element modulus = result.createElement("Modulus");
		rsaKeyValue.appendChild(modulus);

		byte[] modulusBytes = key.getModulus().toByteArray();
		modulusBytes = stripLeadingZeros(modulusBytes);
		modulus.appendChild(result.createTextNode(new String(Base64.getDecoder().decode(modulusBytes))));

		Element exponent = result.createElement("Exponent");
		rsaKeyValue.appendChild(exponent);

		byte[] exponentBytes = key.getPublicExponent().toByteArray();
		exponent.appendChild(result.createTextNode(new String(Base64.getEncoder().encode(exponentBytes))));

		return result;
	}

	private static byte[] stripLeadingZeros(byte[] a) {
		int lastZero = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0) {
				lastZero = i;
			} else {
				break;
			}
		}
		lastZero++;
		byte[] result = new byte[a.length - lastZero];
		System.arraycopy(a, lastZero, result, 0, result.length);
		return result;
	}

	//
	public static String sign(String data, String key_private) {
		try {
			byte[] privateKeyBytes = Base64.getDecoder().decode(key_private);
			// byte[] privateKeyBytes = key_private.getBytes();
			PrivateKey privateKey = KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
			Signature rsa = Signature.getInstance("SHA256withRSA");
			rsa.initSign(privateKey);
			rsa.update(data.getBytes());
			//

			return Base64.getEncoder().encodeToString(rsa.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String createSign(String data, String keyFile) {
		try {
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initSign(getPrivate1(keyFile));
			rsa.update(data.getBytes());
			return new String(Base64.getEncoder().encode(rsa.sign()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public boolean verifySign(String data, String signature, String keyFile) {
		try {
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(getPublic(keyFile));
			sig.update(data.getBytes());
			return sig.verify(Base64.getDecoder().decode(signature));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public PublicKey getPublic(String filename) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(filename);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return (RSAPublicKey) kf.generatePublic(spec);
	}

	public PrivateKey getPrivate1(String filename) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(filename);
		// Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public PrivateKey getPrivate(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		String strFileData = new String(keyBytes);
		strFileData = strFileData.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "")
				.replace("\r\n", "");
		keyBytes = Base64.getDecoder().decode(strFileData);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public String selfSign(String data) {
		try {

			byte[] privateKeyBytes = Base64.getDecoder().decode(private_key);
			// byte[] privateKeyBytes = key_private.getBytes();
			PrivateKey privateKey = KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initSign(privateKey);
			rsa.update(data.getBytes());
			//
			return Base64.getEncoder().encodeToString(rsa.sign());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public Boolean selfVerify(String data, String sign) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			byte[] publicKeyBytes = Base64.getDecoder().decode(public_key);
			// byte[] publicKeyBytes = key_public.getBytes();
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initVerify(publicKey);
			rsa.update(data.getBytes());
			byte[] signByte = Base64.getDecoder().decode(sign);
			return (rsa.verify(signByte));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public Boolean selfVerifyPKCS8(String data, String sign) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			byte[] publicKeyBytes = Base64.getDecoder().decode(public_key);
			// byte[] publicKeyBytes = key_public.getBytes();
			RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
					.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initVerify(publicKey);
			rsa.update(data.getBytes());
			byte[] signByte = Base64.getDecoder().decode(sign);
			return (rsa.verify(signByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public static boolean verify(String data, String sign, String key_public) {
		try {

			byte[] publicKeyBytes = Base64.getDecoder().decode(key_public);
			// byte[] publicKeyBytes = key_public.getBytes();
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			Signature rsa = Signature.getInstance("SHA1withRSA");
			rsa.initVerify(publicKey);
			rsa.update(data.getBytes());
			byte[] signByte = Base64.getDecoder().decode(sign);
			return (rsa.verify(signByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		// genKey();
		initializeKeys();

		RSA rsa = new RSA();
		String data = "vtczota";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEDtIRT57TJAfmub2RsIM32jdo8ijsds/u1fpY6hwtkC01/LFJkNTXqSwvpaO5tp86o0SlzBHdF0WxPtsKqdc8F7kQuHm7hUTLX0zPGRdGCsy9q/PIGlVGAFTBSVXl+grmGGZuS1CHI13L/oulBGENQOxO8r6D1RyPjt6z0BAndQIDAQAB";
		String sign = rsa.createSign(data,
				"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJtdewd4fa4ua7DrIAWbe+nFWojypPuVfVwizEKZtRj/ZYxASSU3zvCBl0zIy2p3rG7nCCF027OmK1ooEcP9mkIrkowtHmWgppHsLB1ut4pxeJrME5wmvUZ3xVQmLtnu/NT5XhwrdOV2LrdVtOW/uz8LFaiII3NeduJcFEurQWPdAgMBAAECgYAEeczxiicUvYGctylVAAWx1l2lVFgsZ0IVRWtc/9CXx8PFJpT03Ya4pwMU0zQI/SmaiE1TqWoq4r6c+kqIuuWWtuzIw5SWU2LSRYruYix8bjUBsnL3HQZLXPaBTp8fDEFJ/xfq2kO5IxyDCUuwizQhA0eJaTdrz1ZlkfScTaqOgQJBAMUMY146hWqX0nFLy3e0/9Wconrz7U37nqPzMhpLFLh9y0nSKOpLa2KGP/sqFW6LhVu7ndUwqQX/T+0duxWSSMsCQQDJ2KRrc+N4ElHfDVjdq/N2yHUi7qhW8D9ACsY7j3noWU8OvqtdjvtRzq75Fn8cfCup2n2UdnKmRQIdT8ABOnj3AkA5QlRNuWuzgdKhIYlKi8ob5JxCZkd69bcvpSfWBCNNSSCdnvhMvQXDZ4eIEL6RlPF3qpk+rhkvqPUszgrjRdLpAkEAnKbtnJN5k+Or9M/YC1p7ftG+z1in/6k/fYWfKfSFaYV+rPPf7aGdfXVKKPmDt2jLPmzbD9EJ0+803+jYHFv0KwJARF5ISy71/EiCf5+xNrOoCPpPttZscVAIQySjP3MnZMtfXU+aNf2JTMmfMZJVIXKdgz1sSl31zfEGn7Z++3x9RQ==");
		System.out.println("CHU KY:" + sign);
		System.out.println("KET QUA VERIFY:" + rsa.verifySign(data, sign, publicKey));

		Boolean verifyRS = rsa.selfVerify(
				// "PARTNERTESTIM330769645642584064|2019-05-28
				// 02:21:34|129|0030006520190528142134431746",
				"FAN_TUANIM20190715bc8c7362006c48da|2019-07-15 16:31:43.0|11|61de1d0df44bfb4866",
				"c9put/+4gsNhUDEUbAt5Y1CvIGI2T6XGbRUvYnBboYFdU+0FxxIU/xrF/ngiJqwHWS3JG+W1KcPQMLxplaePWTFNOZsKNPRpAtj6VzF3iQVPsYx/y1Biuqk+NF0imSu60eLXTHa33r4VK+reXRWG1qCR0N1f7tkjDytqGeWrA7w=");
		System.out.println("VERIFY RS=" + verifyRS);
		// String data = "abc";
		// String sign = sign(data, private_key);
		// System.out.println("--------------------------------");
		// System.out.println("Sign: " + sign);
		// System.out.println("--------------------------------");
		// System.out.println("Verify: " + verify(data, sign, public_key));
	}
}
