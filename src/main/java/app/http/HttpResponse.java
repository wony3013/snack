package app.http;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    /*
    *
    * */

    private DataOutputStream dos = null;
    private Map<String,String> header = new HashMap<>();

    public HttpResponse(OutputStream out){
        dos = new DataOutputStream(out);
    }
}
