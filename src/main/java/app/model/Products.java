package app.model;

import java.util.ArrayList;
import java.util.List;

import app.model.Item;
import app.util.CsvFileHandler;

public class Products {

	List<Item> itemList = new ArrayList<Item>();

	public Products(){
		CsvFileHandler csvFileHandler = new CsvFileHandler();
		csvFileHandler.getItemListArray().stream().forEach(strings -> {
			this.itemList.add(new Item(strings[0], strings[1], Integer.parseInt(strings[2])));
		});
	}

	public void addItem(String id, String name, int price){
		this.itemList.add(new Item(id, name, price));
	}

	public void removeItem(String targetId){
		this.itemList.removeIf(item -> item.id.equals(targetId));
	}

	public List<Item> getItems(){
		return this.itemList;
	}

}
