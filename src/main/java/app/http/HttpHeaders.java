package app.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpHeaders {

    private static final String CONTENT_LENGTH = "Content-Length";
    private static final Logger log = LoggerFactory.getLogger(HttpHeaders.class);

    public Map<String, String> headers = new HashMap<>();

	public HttpHeaders(BufferedReader br) throws IOException {
		String requestLine = br.readLine();
		while (!"".equals(requestLine)){
			this.addHeader(requestLine);
			requestLine = br.readLine();
		}
	}

	public void addHeader(String requestLine){
		int dataLength = 0;
		StringBuffer response = new StringBuffer();
		response.append(requestLine);
		if(requestLine.contains(CONTENT_LENGTH)){
			dataLength = Integer.parseInt(requestLine.replaceAll(" ","").split(":")[1]);
			this.headers.put("dataLength", String.valueOf(dataLength));
		}
		log.debug("header : {}", requestLine);

    }
}
