package app;

public class Item {

	String id;
	String name;
	int price;

	public Item(String id, String name, int price){
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName(){
		return this.name;
	}
	public int getPrice(){
		return this.price;
	}

}
