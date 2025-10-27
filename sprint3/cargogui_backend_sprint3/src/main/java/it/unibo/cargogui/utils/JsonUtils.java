package it.unibo.cargogui.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtils {
	public static int getJsonInt(String jsonStr, String key)  {
		try {
			JSONObject j = (JSONObject) new JSONParser().parse(jsonStr);
			long jpd = (long) j.get(key);
			return Long.valueOf(jpd).intValue();
		}catch(Exception e) {
			return 0;
		}
	}
		public static String getJsonString(String jsonStr, String key)   {
		try {
			JSONObject j = (JSONObject) new JSONParser().parse(jsonStr);
			return (String) j.get(key);
		}catch(Exception e) {
			return "error";
		}
	}
}
