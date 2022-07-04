package app.http;

import java.util.Arrays;
import java.util.HashMap;

public class RequestParams {
	public HashMap<String, String> requestParam;

	public RequestParams(String cubf) {

		String[] splitedRequestString = cubf.split("&");
		Arrays.stream(splitedRequestString).forEach(s -> {
			String[] bb = s.split("=");
			this.requestParam.put(bb[0], bb[1]);
		});

	}
}

