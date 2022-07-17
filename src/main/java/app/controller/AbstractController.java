package app.controller;

import app.http.HttpMethod;
import app.http.HttpRequest;
import app.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController implements Controller{

    Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        HttpMethod method = request.getMethod();
        if (method.isPost()){
            doPost(request, response);
        } else {
            doGet(request, response);
        }
    }

    public void doGet(HttpRequest request, HttpResponse response){
    };

    public void doPost(HttpRequest request, HttpResponse response){
    };

}
