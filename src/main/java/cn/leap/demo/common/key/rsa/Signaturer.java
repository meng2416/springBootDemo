package cn.leap.demo.common.key.rsa;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;


public class Signaturer {
	/**
	 * 
	 * Description:数字签名
	 * 
	 * @param priKeyText
	 * @param plainText
	 * @return
	 */
	public static byte[] sign(byte[] priKeyText, String plainText) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKeyText));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey prikey = keyf.generatePrivate(priPKCS8);

			// 用私钥对信息生成数字签名
			Signature signet = Signature.getInstance("MD5withRSA");
			signet.initSign(prikey);
			signet.update(plainText.getBytes());
			byte[] signed = Base64.encodeBase64(signet.sign());
			System.out.println("signed = " + signed);
			return signed;
		} catch (java.lang.Exception e) {
			System.out.println("签名失败");
			e.printStackTrace();
		}
		return null;
	}
}
