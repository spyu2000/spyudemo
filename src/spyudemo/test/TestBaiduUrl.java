package spyudemo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestBaiduUrl {

	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		try {

			String address = URLEncoder.encode("莲花一村", "utf-8");
			String city = URLEncoder.encode("深圳市", "utf-8");

			address = "莲花一村";
			city = "深圳市";
			InputStream is = new URL(
					"http://api.map.baidu.com/place/v2/search?&q="
							+ address
							+ "&region="
							+ city
							+ "&output=json&ak=A7a3c1b7ba89e10564d2ea37dd230acf")
					.openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = TestBaiduUrl.readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			System.out.println(jsonText);
			System.out.println(json.toString());
			JSONArray tt = json.getJSONArray("results");
			JSONObject ttt = (JSONObject) tt.get(0);
			String tttt = ttt.getJSONObject("location").getDouble("lng") + "";
			String ttttt = ttt.getJSONObject("location").getDouble("lat") + "";
			System.out.println(tttt + "   " + ttttt);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
