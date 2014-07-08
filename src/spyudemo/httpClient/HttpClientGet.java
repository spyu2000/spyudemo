package spyudemo.httpClient;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientGet {

	public static void main(String[] args){
		
		try {
			HttpClient httpClient=new HttpClient(new SimpleHttpConnectionManager());
			GetMethod getMethod=new GetMethod("http://183.62.157.53:8086/web/user_login.go");
			NameValuePair nameValuePair=new NameValuePair("userName","968404");
			NameValuePair nameValuePair1=new NameValuePair("userPwd","1111");
			NameValuePair nameValuePair2=new NameValuePair("lang","1");
			NameValuePair[] paras=new NameValuePair[]{nameValuePair,nameValuePair1,nameValuePair2};
			getMethod.setQueryString(paras);
			int statusCode=httpClient.executeMethod(getMethod);
			System.out.println("response=:"+getMethod.getResponseBodyAsString());
			getMethod.releaseConnection();
			
			String address = URLEncoder.encode("华富街道办事处", "utf-8");
			String city = URLEncoder.encode("深圳市", "utf-8");
			
			GetMethod getMethod1=new GetMethod("http://api.map.baidu.com/place/v2/search?&q="+address+"&region="+city+"&output=json&ak=A7a3c1b7ba89e10564d2ea37dd230acf");
			
//			NameValuePair nameValuePair11=new NameValuePair("ak","A7a3c1b7ba89e10564d2ea37dd230acf");
//			NameValuePair nameValuePair12=new NameValuePair("output","json");
//			NameValuePair nameValuePair13=new NameValuePair("q",URLEncoder.encode("莲花一村","utf-8"));
//			NameValuePair nameValuePair14=new NameValuePair("region",URLEncoder.encode("深圳市","utf-8"));
//			NameValuePair[] paras1=new NameValuePair[]{nameValuePair11,nameValuePair12,nameValuePair13,nameValuePair14};			
//			getMethod1.setQueryString(paras1);
			statusCode=httpClient.executeMethod(getMethod1);
			System.out.println("response=:"+getMethod1.getResponseBodyAsString());
			getMethod1.releaseConnection();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
