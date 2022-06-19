package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServer {
	private static final Logger log = LoggerFactory.getLogger(WebServer.class);
	private static final int DEFAULT_PORT = 8080;

	public static void main(String[] args) {
		int port = 0;
		if((args.length == 0) || (args == null)){
			port = DEFAULT_PORT;
		} else {
			port = Integer.parseInt(args[0]);
		}

		try(ServerSocket listenSocket = new ServerSocket(port)){
			log.info("Web Application Server started {} port",port);
			Socket connection;
			while ((connection = listenSocket.accept()) != null){
				RequestHandler requestHandler = new RequestHandler(connection);
				requestHandler.start();
			}
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}

}
