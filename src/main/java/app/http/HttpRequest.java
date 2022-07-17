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

    private RequestLine requestLine;
    private HttpHeaders httpHeaders;
    private RequestParams requestParams = new RequestParams();

    public HttpRequest(InputStream is){

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            requestLine = new RequestLine(createRequestLine(br));
            requestParams.addQueryString(requestLine.getQueryString());
            httpHeaders = processHeaders(br);
            requestParams.addBody(IOUtils.getCubf(br, httpHeaders.getContentLength()));
        } catch (IOException e){
            log.error(e.getMessage());
        }

    }

    private String createRequestLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        if(line == null){
            throw new IllegalArgumentException();
        }
        return line;
    }

    private HttpHeaders processHeaders(BufferedReader br) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String line;
        while (!(line = br.readLine()).equals("")){
            headers.add(line);
        }
        return headers;
    }

    public HttpMethod getMethod(){
        return requestLine.getHttpMethod();
    }

    public String getPath(){
        return requestLine.getPath();
    }

    public String getHeader(String key){
        return httpHeaders.getHeader(key);
    }

    public String getParameter(String key){
        return requestParams.getParameter(key);
    }

}
