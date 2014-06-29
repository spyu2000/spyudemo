package spyudemo.httpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientDownFile {
	private final static String REMOTE_FILE_URL = "http://archive.apache.org/dist/httpcomponents/commons-httpclient/3.0/source/commons-httpclient-3.0.1-src.zip"; 
	public static void main(String[] args){	
		
		try {
			HttpClient client=new HttpClient();
			GetMethod method=new GetMethod(REMOTE_FILE_URL);
			 client.executeMethod(method);
			BufferedInputStream buffIns=new BufferedInputStream(method.getResponseBodyAsStream());
			
			BufferedOutputStream buffOus=new BufferedOutputStream(new FileOutputStream("test.zip"));
			byte[] arr=new byte[10240];
			
			long startTime=System.currentTimeMillis();
			System.out.println("start down:"+new Date(startTime));
			int len=0;
			int count=0;
			while((len=buffIns.read(arr))!=-1){
				count++;
				System.out.println("down:"+count+" length:"+len);
				buffOus.write(arr,0,len);
				buffOus.flush();
			}	
			buffOus.flush();
			System.out.println("finish down:"+(System.currentTimeMillis()-startTime));
			
			method.releaseConnection();
			buffIns.close();
			buffOus.close();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
