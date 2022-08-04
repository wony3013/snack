package app.controller;

import app.http.HttpRequest;
import app.http.HttpResponse;

public class loginFormController extends AbstractController{
	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		response.forward("/loginForm.html");
	}
}
