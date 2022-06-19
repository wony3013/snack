import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import app.CsvFileHandler;

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
}
