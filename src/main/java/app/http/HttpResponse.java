package app.http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.RequestHandler;

public class HttpResponse {
    private static final Logger log =  LoggerFactory.getLogger(RequestHandler.class);

    private DataOutputStream dos = null;
    private Map<String,String> headers = new HashMap<>();

    public HttpResponse(OutputStream out){
        dos = new DataOutputStream(out);
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }

    public void forward(String url){
        try{
            byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
            if(url.endsWith(".css")){
                headers.put("Content-Type", "text/css");
            } else if (url.endsWith(".js")) {
                headers.put("Content-Type", "application/javascript");
            } else {
                headers.put("Content-Type", "text/html;charset=utf-8");
            }
            headers.put("Content-Length", String.valueOf(body.length));
            response200Header(body.length);
            responseBody(body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void forwardBody(String body){
        byte[] contents = body.getBytes();
        headers.put("Content-Type", "text/html;charset=utf-8");
        headers.put("Content-Length", String.valueOf(contents.length));
        response200Header(contents.length);
        responseBody(contents);
    }

    public void sendRedirect(String redirectUrl){
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            processHeaders();
            dos.writeBytes("Location: " + redirectUrl + " \r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    //TODO ResponseEntity 작업 알아보기
    // public PrintWriter getWriter(Object obj){
    //     try {
    //         dos.writeBytes("HTTP/1.1 200 OK \r\n");
    //         dos.writeBytes("Content-Type: application/json \r\n");
    //         dos.writeBytes("Content-Type:");
    //         dos.writeBytes("\r\n");
    //     } catch (IOException e) {
    //         log.error(e.getMessage());
    //     }
    //     headers.put();
    //     return new PrintWriter(dos);
    //
    // }

    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(OutputStream.nullOutputStream());
    }

    private void response200Header(int lengthOfBodyContent){
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            processHeaders();
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void processHeaders(){
        try {
            Set<String> keys = headers.keySet();
            for (String key : keys){
                dos.writeBytes(key + ": " + headers.get(key) + " \r\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(byte[] body){
        try {
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
