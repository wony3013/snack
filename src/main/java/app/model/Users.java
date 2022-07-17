package app.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Users {

	private static Map<String, User> users = new HashMap<String, User>();

	public static void addUser(User user){
		users.put(user.getId(), user);
	}

	public static Collection<User> findAll(){
		return users.values();
	}


}
