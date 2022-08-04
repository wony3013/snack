import static org.junit.Assert.*;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.User;
import app.model.Users;

public class userTest {

	Logger logger = LoggerFactory.getLogger(ItemTest.class);

	Users users;

	@Test
	public void createAndLoginTest(){

		Users.addUser(new User("admin","admin","어드민"));

		User user = Users.getUser("admin");
		assertEquals("어드민", user.getName());
	}

}
