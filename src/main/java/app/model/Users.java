package app.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Users {

	private static Map<String, User> users = new HashMap<String, User>();

	public static void addUser(User user){
		users.put(user.getId(), user);
	}

	public static Collection<User> findAll(){
		return users.values();
	}

	public static User getUser(String id){
		User user = users.get(id);

		if (user == null) {
			return null;
		}
		return user;

	}

}
