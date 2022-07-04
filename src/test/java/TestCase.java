import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import app.util.CsvFileHandler;

public class TestCase {

	Logger logger = LoggerFactory.getLogger(TestCase.class);

	@Test
	public void indexFileLoadTest(){
		File index = new File("src/webapp/index.html");
		assertTrue(index.isFile());
	}

	@Test
	public void itemListLoadFromCSV(){
		CsvFileHandler csvFileHandler = new CsvFileHandler();
		List<String[]> itemList = csvFileHandler.getItemListArray();
		itemList.stream().forEach(strings -> logger.debug(strings[1]));
	}

	@Test
	public void split(){
		String a = "id=a1&name=b2&price=c33";
		String[] b = a.split("&");

		HashMap<String, String> request = new HashMap<>();

		Arrays.stream(b).forEach(s -> {
			String[] bb = s.split("=");
			request.put(bb[0], bb[1]);
		});

		logger.debug(request.get("id"));
		logger.debug(request.get("name"));
		logger.debug(request.get("price"));


	}
}
