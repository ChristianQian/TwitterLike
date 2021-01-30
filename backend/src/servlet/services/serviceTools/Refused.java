package servlet.services.serviceTools;

import org.json.JSONException;
import org.json.JSONObject;

public class Refused {
	
	public static JSONObject serviceRefused(String mes, int num) throws JSONException {
		JSONObject j= new JSONObject();
		j.put(mes, num);
		return j;
	}	
}
