package spyudemo.httpClient;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientGet {

	public static void main(String[] args){
		
		try {
			HttpClient httpClient=new HttpClient();
			GetMethod getMethod=new GetMethod("http://183.62.157.53:8086/web/user_login.go");
			NameValuePair nameValuePair=new NameValuePair("userName","968404");
			NameValuePair nameValuePair1=new NameValuePair("userPwd","1111");
			NameValuePair nameValuePair2=new NameValuePair("lang","1");
			NameValuePair[] paras=new NameValuePair[]{nameValuePair,nameValuePair1,nameValuePair2};
			getMethod.setQueryString(paras);
			int statusCode=httpClient.executeMethod(getMethod);
			System.out.println("response=:"+getMethod.getResponseBodyAsString());
			getMethod.releaseConnection();
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
