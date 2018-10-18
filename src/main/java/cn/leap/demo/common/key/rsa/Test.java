package cn.leap.demo.common.key.rsa;

import cn.leap.demo.common.key.rsa.KeyGenerater;
import cn.leap.demo.common.key.rsa.SignProvider;
import cn.leap.demo.common.key.rsa.Signaturer;

public class Test {
	
	/**
	 * 测试demo
	 * @param args
	 */
	public static void main(String[] args) {
		
		KeyGenerater gen = new KeyGenerater();
		gen.generater();
		
		String plainText = "测试123";
		byte[] signText = Signaturer.sign(gen.getPriKey(), plainText);
		
		boolean verify = SignProvider.verify(gen.getPubKey(), plainText, signText);
		System.out.println(verify);
	}
}
