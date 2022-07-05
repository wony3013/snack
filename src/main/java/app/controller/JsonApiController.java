package app.controller;

import app.http.HttpRequest;
import app.http.HttpResponse;

public interface JsonApiController {
    String service(HttpRequest request, HttpResponse response);
}
