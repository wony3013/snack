package app.controller;

import app.http.HttpRequest;
import app.http.HttpResponse;
import app.model.User;
import app.model.Users;

public class CreateUserController extends AbstractController{
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        super.doGet(request, response);
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        User user = new User(request.getParameter("id"), request.getParameter("password"),
            request.getParameter("name"));
        Users.addUser(user);
        response.sendRedirect("/index.html");
    }
}
