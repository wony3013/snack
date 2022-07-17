package server;

import app.controller.ApiUsersController;
import app.controller.Controller;
import app.controller.CreateUserController;
import app.controller.ItemFormController;
import app.controller.JsonApiController;
import app.controller.LoginController;
import app.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();
    private static Map<String, JsonApiController> apiControllers = new HashMap<>();

    static {
        apiControllers.put("/api/users", new ApiUsersController());
    }

    static {
        controllers.put("/user/create", new CreateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/list", new UserListController());
        controllers.put("/item/new", new ItemFormController());

    }

    public static Controller getController(String requestUrl){
        return controllers.get(requestUrl);
    }

    public static JsonApiController getApiController(String requestUrl){
        return apiControllers.get(requestUrl);
    }
}
