package app.controller;

import app.http.HttpRequest;
import app.http.HttpResponse;

import java.io.PrintWriter;

public class ApiUsersController implements JsonApiController{
    @Override
    public String service(HttpRequest request, HttpResponse response) {
        PrintWriter out = response.getWriter();
        out.println(// JSON String return);
        return null;
    }
}
