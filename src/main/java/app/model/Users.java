package app.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Users {

	private static Map<String, User> users = new HashMap<String, User>();

	private Users() {
		users.put("admin", new User("admin","admin","어드민"));
	}

	public static void addUser(User user){
		users.put(user.getId(), user);
	}

	public static Collection<User> findAll(){
		return users.values();
	}

	public static User getUser(String id){
		User loginUser = Users.findAll()
			.stream()
			.filter(user -> user.getName().equals(id))
			.findFirst()
			.orElse(null);

		if (loginUser == null) {
			return null;
		}
		return loginUser;

	}


}
