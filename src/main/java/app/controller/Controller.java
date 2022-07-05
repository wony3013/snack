package app.controller;

import app.http.HttpRequest;
import app.http.HttpResponse;

public interface Controller {

    void service(HttpRequest request, HttpResponse response);

}
