package app.http;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.RequestHandler;

import java.util.Map;

public class HttpResponseBody {

    private static final Logger log =  LoggerFactory.getLogger(RequestHandler.class);
    String responseBody;

    public HttpResponseBody(Map<String, String> response) {
        this.responseBody = JSONObject.toJSONString(response);
    }

    public String getResponseBody() {
        return responseBody;
    }
}
