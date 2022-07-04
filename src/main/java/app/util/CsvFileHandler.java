package app.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvFileHandler {

	Logger logger = LoggerFactory.getLogger(CsvFileHandler.class);

	public List<String[]> getItemListArray(){
		File itemListCsvFile = new File("src/main/resources/snack_list.csv");

		List<String[]> itemList = new ArrayList<>();

		try(Scanner is = new Scanner(itemListCsvFile)){
			while (is.hasNext()){
				String[] itemValue = is.next().split(",");
				itemList.add(itemValue);
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return itemList;
	}
}
