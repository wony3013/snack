package app.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.util.IOUtils;
import server.RequestHandler;

public class HttpRequest {
    private static final Logger log =  LoggerFactory.getLogger(RequestHandler.class);

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public RequestParams getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(RequestParams requestParams) {
        this.requestParams = requestParams;
    }

    RequestLine requestLine;
    HttpHeaders httpHeaders;
    RequestParams requestParams;

    public HttpRequest(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        log.debug("request line : {}", line);

        if(line == null){
            return;
        }

        this.requestLine = new RequestLine(line);
        this.httpHeaders = new HttpHeaders(br);
        this.requestParams = new RequestParams(IOUtils.getCubf(br, dataLength))
    }

}
