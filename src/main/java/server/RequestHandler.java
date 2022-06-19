package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

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
			//TODO 사용자 요청에 대한 처리는 이곳에서 구현하면 된다.
			File index = new File("src/webapp/index.html");
			DataOutputStream dos = new DataOutputStream(out);
			byte[] body = "SNACK WORLD".getBytes();
			byte[] body2 = Files.readAllBytes(index.toPath());
			response200Header(dos, body2.length);
			responseBody(dos,body2);

		}catch (IOException e){
			log.error(e.getMessage());

		}

	}

	private void response200Header(DataOutputStream dos, int lengthOfBodyContent){
		try{
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: text/html; charset=utf-8 \r\n");
			dos.writeBytes("Content-Length:"+lengthOfBodyContent+"\r\n");
			dos.writeBytes("\r\n");

		}catch (IOException e){
			log.error(e.getMessage());
		}

	}

	private void responseBody(DataOutputStream dos, byte[] body){
		try{
			dos.write(body,0,body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		}catch (IOException e){
			log.error(e.getMessage());
		}
	}


}
