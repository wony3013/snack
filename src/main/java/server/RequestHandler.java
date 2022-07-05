package server;

import app.controller.Controller;
import app.http.HttpRequest;
import app.http.HttpResponse;
import app.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import com.google.common.net.HttpHeaders;

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
			HttpRequest httpRequest = new HttpRequest(in);
			HttpResponse httpResponse = new HttpResponse(out);

			String path = httpRequest.getRequestLine().getPath();

			Controller controller = RequestMapping.getController(path);
			if(Objects.isNull(controller)){
				String path2 = getDefaultPath(url);
			} else {
				controller.service(httpRequest, httpResponse);
			}

			// TODO Ajax!! 사용자목록을 json으로 리턴하는 기능 /api/users


			int dataLength = Integer.parseInt(httpRequest.getHttpHeaders().headers.get("dataLength"));

			// BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//
			// if(line == null){
			// 	return;
			// }
			//
			// String[] tokens = line.split(" ");
			// StringBuffer response = new StringBuffer();
			// int dataLength = 0;

			// while (!"".equals(line)){
			// 	response.append(line);
			// 	if(line.contains("Content-Length")){
			// 		dataLength = Integer.parseInt(line.replaceAll(" ","").split(":")[1]);
			// 	}
			// 	log.debug("header : {}", line);
			// 	line = br.readLine();
			// }
			byte[] body = Files.readAllBytes(new File("./src/webapp/index.html").toPath());




			// TODO if와 else를 제거
			if("/product".equals(path)){
				body = Files.readAllBytes(new File("./src/webapp" + path+".html").toPath());
			} else if ("/product/regForm".equals(path)) {
				int requestBodyLength;
				HashMap<String, String> requestParam = null;
				if(dataLength > 0) {
					httpRequest.getRequestParams(dataLength);

					requestParam = makeRequestParam(cubf);
					this.products.addItem(requestParam.get("id"), requestParam.get("name"), Integer.parseInt(requestParam.get("price")));
				}
			} else if ("/user/login".equals(path)){
				/** TODO response add Header "Set-Cookie" "logined=true"
				request Cookie logined key value
				Cookie key jsessionid=*/
			} else if ("/user/create".equals(path)){
				String bodys = IOUtils.getCubf(br, dataLength);
			}









			File index = new File("./src/webapp/index.html");

			DataOutputStream dos = new DataOutputStream(out);
			response200Header(dos, body.length);
			responseBody(dos,body);

		}catch (IOException e){
			log.error(e.getMessage());

		}

	}

	private HashMap<String, String> makeRequestParam(String cubf){
		HashMap<String, String> request = new HashMap<>();
		String[] splitedRequestString = cubf.split("&");
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
			dos.writeBytes("Set-Cookie: logined=true; Path=/");
			dos.writeBytes("\r\n");

		}catch (IOException e){
			log.error(e.getMessage());
		}
	}

	// Redirect
	private void response302Header(DataOutputStream dos, int lengthOfBodyContent){
		try{
			dos.writeBytes("HTTP/1.1 302 Redirect \r\n");
			dos.writeBytes("Location:  \r\n");
			dos.writeBytes("Set-Cookie: logined=true; Path=/");
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
