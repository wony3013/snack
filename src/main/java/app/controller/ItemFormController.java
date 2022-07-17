package app.controller;

import app.http.HttpRequest;
import app.http.HttpResponse;
import app.model.User;
import app.model.Users;

public class ItemFormController extends AbstractController{
	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		response.forward("/product.html");
	}

}
