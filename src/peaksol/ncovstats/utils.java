package peaksol.ncovstats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class utils {
	public final static Map<String, String> map = new HashMap<String, String>() {{
		put("上海", "上海市");
		put("云南", "云南省");
		put("内蒙古", "内蒙古自治区");
		put("北京", "北京市");
		put("吉林", "吉林省");
		put("四川", "四川省");
		put("天津", "天津市");
		put("宁夏", "宁夏回族自治区");
		put("安徽", "安徽省");
		put("山东", "山东省");
		put("山西", "山西省");
		put("广东", "广东省");
		put("广西", "广西壮族自治区");
		put("江苏", "江苏省");
		put("江西", "江西省");
		put("河北", "河北省");
		put("河南", "河南省");
		put("浙江", "浙江省");
		put("新疆", "新疆维吾尔自治区");
		put("海南", "海南省");
		put("湖北", "湖北省");
		put("湖南", "湖南省");
		put("甘肃", "甘肃省");
		put("福建", "福建省");
		put("西藏", "西藏自治区");
		put("贵州", "贵州省");
		put("辽宁", "辽宁省");
		put("重庆", "重庆市");
		put("陕西", "陕西省");
		put("青海", "青海省");
		put("黑龙江", "黑龙江省");
	}};

	public static String get(String url, String toEncode) throws IOException {
		String result = "";
		BufferedReader in = null;
		String urlNameString = url + URLEncoder.encode(toEncode, "UTF-8");
		URL realUrl = new URL(urlNameString);
		URLConnection connection = realUrl.openConnection();
		connection.connect();
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		if (in != null) {
			in.close();
		}
        return result;
    }
}
