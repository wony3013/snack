package app.http;

import java.util.Arrays;
import java.util.HashMap;

import app.util.HttpRequestUtils;

public class RequestParams {
	public HashMap<String, String> requestParam = new HashMap<>();

	public void addQueryString(String queryString){
		putParams(queryString);
	}

	private void putParams(String data){
		if(data == null || data.isEmpty()){
			return;
		}
		requestParam.putAll(HttpRequestUtils.parseQueryString(data));
	}

	public void addBody(String body){
		putParams(body);
	}

	public String getParameter(String key){
		return requestParam.get(key);
	}
}

