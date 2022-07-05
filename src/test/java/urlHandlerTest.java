import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class urlHandlerTest {

    @Test
    public void urlCatchTest(){
        String testUrl = "/user";
        UrlHandler urlHandler = new Urlhandler();

        assertEquals(urlHandler.urlCatch(testUrl), "/user");



    }
}
