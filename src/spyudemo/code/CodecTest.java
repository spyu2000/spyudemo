package spyudemo.code;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class CodecTest {

	public static void main(String[] args){
		try{
			CodecTest.testBase64();
			CodecTest.testHex();
		}catch(Exception ex){
			
		}
	}
	
	public static void testHex() throws DecoderException{
		Hex hex = new Hex(); 
		String str = "中文";
		char[] enbytes = null; 
		String encodeStr = null;
		byte[] debytes = null;
		String decodeStr = null;
		enbytes = hex.encodeHex(str.getBytes());
		encodeStr = new String(enbytes);
		debytes = hex.decodeHex(enbytes); 
		decodeStr = new String(debytes); 
		System.out.println("编码前:" + str); 
		System.out.println("编码后:" + encodeStr); 
		System.out.println("解码后:" + decodeStr); 
	}
	public static void testBase64(){
		Base64 base64=new Base64();
		String str = "中文"; 
		byte[] enbytes = null; 
		String encodeStr = null; 
		byte[] debytes = null; 
		String decodeStr = null;
		enbytes = base64.encode(str.getBytes()); 
		encodeStr = new String(enbytes);
		debytes = base64.decode(enbytes); 
		decodeStr = new String(debytes); 
		System.out.println("编码前:" + str); 
		System.out.println("编码后:" + encodeStr);
		System.out.println("解码后:" + decodeStr); 


			
		
		
	}
}
