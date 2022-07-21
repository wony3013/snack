package server;

import app.controller.Controller;
import app.controller.JsonApiController;
import app.http.HttpRequest;
import app.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread{
	private static final Logger log =  LoggerFactory.getLogger(RequestHandler.class);
	private Socket connection;

	public RequestHandler(Socket socket){
		this.connection = socket;
	}

	@Override
	public void run(){
		log.debug("New Client Connect Connect IP: {}, Port : {}", connection.getInetAddress(), connection.getPort());

		try(InputStream in = connection.getInputStream();
			OutputStream out = connection.getOutputStream()){
			HttpRequest httpRequest = new HttpRequest(in);
			HttpResponse httpResponse = new HttpResponse(out);

			// TODO if와 else를 제거



			Controller controller = RequestMapping.getController(httpRequest.getPath());
			JsonApiController jController = RequestMapping.getApiController(httpRequest.getPath());

			if (controller == null && jController == null){
				log.debug("Empty Controller");
				String url = getDefaultPath(httpRequest.getPath());
				httpResponse.forward(url);
			} else if (jController != null) {
				log.debug("REST API");
				jController.service(httpRequest, httpResponse);
			} else {
				log.debug("HTTP Controller");
				controller.service(httpRequest, httpResponse);
			}

		}catch (IOException e){
			log.error(e.getMessage());

		}

	}

	private String getDefaultPath(String path){
		if("/".equals(path)){
			return "/index.html";
		}
		return path;
	}

}
