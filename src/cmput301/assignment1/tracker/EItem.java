package cmput301.assignment1.tracker;

public class EItem {
	// private variables
	private String description = "";
	private String date = "";
	private String category = "";
	private String price = "";
	private String unit = "";
	
	// constructor, update variables
	public EItem(String des, String dat, String cat, String pri, String uni) {
		this.description = des;
		this.date = dat;
		this.category = cat;
		this.price = pri;
		this.unit = uni;
	}
	
	// overwrite toString function
	public String toString() {
		return get_description();
	}
	
	// get private variable: description
	public String get_description() {
		return this.description;
	}
	
	// get private variable: date
	public String get_date() {
		return this.date;
	}
	
	// get private variable: category
	public String get_category() {
		return this.category;
	}
	
	// get private variable: price
	public String get_price() {
		return this.price;
	}
	
	// get private variable: currency_unit
	public String get_unit() {
		return this.unit;
	}
}
