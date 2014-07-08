package spyudemo.httpClient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientPost {

	public static void main(String[] args){
		
		PostMethod postMethod=null;
		try {
			String charset="utf-8";
			HttpClient client=new HttpClient();
			postMethod=new PostMethod("http://183.62.157.53:8086/web/user_login.go");
			NameValuePair nameValuePair=new NameValuePair("userName","968404");
			NameValuePair nameValuePair1=new NameValuePair("userPwd","1111");
			NameValuePair nameValuePair2=new NameValuePair("lang","1");
			NameValuePair[] paras=new NameValuePair[]{nameValuePair,nameValuePair1,nameValuePair2};
			postMethod.addParameters(paras);
			postMethod.setRequestBody(paras);

			postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;text/html ;chartset="+charset);
			int statusCode=client.executeMethod(postMethod);
			String responseCharset=postMethod.getResponseCharSet();
			String result=postMethod.getResponseBodyAsString();
			System.out.println(result);
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
		
	}
}
