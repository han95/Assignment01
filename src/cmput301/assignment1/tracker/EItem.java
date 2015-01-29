package cmput301.assignment1.tracker;

public class EItem {
	private String description = "";
	private String date = "";
	private String category = "";
	private String price = "";
	
	public EItem(String des, String dat, String cat, String pri) {
		this.description = des;
		this.date = dat;
		this.category = cat;
		this.price = pri;
		
	}
	
	public String get_description() {
		return this.description;
	}
	
	public String get_date() {
		return this.date;
	}
	
	public String get_category() {
		return this.category;
	}
	
	public String get_price() {
		return this.price;
	}
	
	
}
