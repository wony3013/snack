package app.http;

import java.io.InputStream;

public class HttpRequest {

    RequestLine requestLine;
    HttpHeaders httpHeaders;
    RequestParams requestParams;

    HttpRequest(InputStream is){

    }

}
