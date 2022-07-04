package app.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLine {
    private static final Logger log = LoggerFactory.getLogger(RequestLine.class);

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    private HttpMethod httpMethod;

    private String path;

    private String queryString;

    public RequestLine(String requestLine) {
        log.debug("request line = {}", requestLine);
        String[] tokens = requestLine.split(" ");
        this.httpMethod = HttpMethod.valueOf(tokens[0]);

        String[] url = tokens[1].split("\\?");
        this.path = url[0];

        if(url.length == 2){
            this.queryString = url[1];
        }
    }
}
