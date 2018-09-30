package cn.leap.demo.common.key;

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
