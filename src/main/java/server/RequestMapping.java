package server;

import app.controller.Controller;
import app.controller.CreateUserController;
import app.controller.LoginController;
import app.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/user/create", new CreateUserController());
        controllers.put("/login", new LoginController());
        controllers.put("/api/userlist", new UserListController());
    }

    public static Controller getController(String requestUrl){
        return controllers.get(requestUrl);
    }
}
