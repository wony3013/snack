package app.controller;

import app.http.HttpHeaders;
import app.http.HttpRequest;
import app.http.HttpResponse;
import app.model.User;
import app.model.Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiUsersController implements JsonApiController{
    private static final Logger log = LoggerFactory.getLogger(HttpHeaders.class);
    @Override
    public String service(HttpRequest request, HttpResponse response) {
        // TODO Ajax!! 사용자목록을 json으로 리턴하는 기능 /api/users / ResponseEntity

        Collection<User> userCollection = Users.findAll();
        // JSONObject jsonObject = (JSONObject)userCollection.toArray().toString();
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("userList", "userCollection.toArray()");

            response.addHeader("Content-type", "application/json");
            PrintWriter out = response.getWriter();
            log.debug(jsonObject.toJSONString());
            out.write(jsonObject.toJSONString());
            out.println(jsonObject.toJSONString());
            response.forwardBody(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "aTest";
    }
}
