package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;

import app.Products;

public class RequestHandler extends Thread{
	private static final Logger log =  LoggerFactory.getLogger(RequestHandler.class);
	private Socket connection;

	static Products products = new Products();

	public RequestHandler(Socket socket){
		this.connection = socket;

	}

	@Override
	public void run(){
		log.debug("New Client Connect Connect IP: {}, Port : {}", connection.getInetAddress(), connection.getPort());

		try(InputStream in = connection.getInputStream();
			OutputStream out = connection.getOutputStream()){
			//TODO 사용자 요청에 대한 처리는 이곳에서 구현하면 된다.
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			log.debug("request line : {}", line);

			if(line == null){
				return;
			}

			String[] tokens = line.split(" ");
			StringBuffer response = new StringBuffer();
			int dataLength = 0;

			while (!"".equals(line)){
				response.append(line);
				if(line.contains("Content-Length")){
					dataLength = Integer.parseInt(line.replaceAll(" ","").split(":")[1]);
				}
				log.debug("header : {}", line);
				line = br.readLine();
			}
			byte[] body = Files.readAllBytes(new File("./src/webapp/index.html").toPath());
			if("/product".equals(tokens[1])){
				body = Files.readAllBytes(new File("./src/webapp" + tokens[1]+".html").toPath());
			} else if ("/product/regForm".equals(tokens[1])) {
				int requestBodyLength;
				HashMap<String, String> requestParam = null;
				if(dataLength > 0) {
					char[] cubf = getCubf(dataLength, br);
					requestParam = makeRequestParam(cubf);
					this.products.addItem(requestParam.get("id"), requestParam.get("name"), Integer.parseInt(requestParam.get("price")));
				}
			}


			File index = new File("./src/webapp/index.html");

			DataOutputStream dos = new DataOutputStream(out);
			response200Header(dos, body.length);
			responseBody(dos,body);

		}catch (IOException e){
			log.error(e.getMessage());

		}

	}

	private char[] getCubf(int dataLength, BufferedReader br) throws IOException {
		char[] cubf = null;
		cubf = new char[dataLength];
		int requestBodyLength = br.read(cubf);
		return cubf;
	}


	private HashMap<String, String> makeRequestParam(char[] cubf){
		HashMap<String, String> request = new HashMap<>();
		String requestString = new String(cubf);
		String[] splitedRequestString = requestString.split("&");
		Arrays.stream(splitedRequestString).forEach(s -> {
			String[] bb = s.split("=");
			request.put(bb[0], bb[1]);
		});
		return request;
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
