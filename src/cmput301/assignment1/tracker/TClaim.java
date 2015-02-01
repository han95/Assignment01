package cmput301.assignment1.tracker;

/*
 * Travel claim class.
 * It allows user to edit key information of claim through public methods, 
 * user can also get expense items of this claim
 */

import java.io.Serializable;
import java.util.ArrayList;

public class TClaim implements Serializable {

	/**
	 * generated serialization ID
	 */
	private static final long serialVersionUID = -6294145498306239207L;
	
	// private variables
	protected String des = "";
	protected String start_date = "";
	protected String end_date = "";
	protected ArrayList<EItem> eiList = new ArrayList<EItem>();
	protected String status = "In progress";
	protected String RString = "";
	
	// constructor
	public TClaim(){
		
	}
	

	// set information of travel claim
	public void setInfo(String des, String start, String end) {
		this.des = des;
		this.start_date = start;
		this.end_date = end;
	}
	
	public void change_status(String s) {
		this.status = s;
	}
	
	// overwrite to String function
	public String toString(){
		return shown_information();
	}
	

	// return information shown  list
	public String shown_information(){
		return this.des+"--state:"+this.status;
	}
	
	// get information: description of travel claim
	public String get_description() {
		return this.des;
	}
	
	// get information: expense items
	public ArrayList<EItem> get_eiList() {
		return eiList;
	}

	// get start date
	public String get_start_date() {
		return this.start_date;
	}

	// get end date
	public String get_end_date() {
		return this.end_date;
	}
	
	// get status
	public String get_status() {
		return this.status;
	}

	// see if the claim is empty
	public boolean has_content() {
		if (this.des != "" |
				this.start_date != "" | 
				this.end_date != "" |
				this.RString != "" |
				this.eiList.size() != 0) {
			return true;
		}
		return false;
	}
	
	// get total expense from expense item list
	public String total_expense() {
		
		// index of eiList
		int index = 0;
		// index of new list
		int i = 0;
		// variable to save unit of currency
		String u = "";
		// variable to save price
		String p = ""; 
		// temporary variable, saving double data which is converted from string
		double temp = 0;
		// list to save total prices
		ArrayList<String> total = new ArrayList<String>();
		// list to save units
		ArrayList<String> unit = new ArrayList<String>();
		// result of compare. If the unit is exist in unit list, it is true
		boolean unitExist = false;
		// check each expense item in eiList
		while (index < eiList.size()) {
			// get currency unit of this expense item
			u = eiList.get(index).get_unit();
			// initialize index of unit list
			i = 0;
			// check if this unit has been in unit list
			// if it is found, stop while loop
			while (i < unit.size() & unitExist == false) {
				if (u.equals(unit.get(i).toString())) {
					unitExist = true;
				}
				i++;
			}
			// get price of this expense item
			p = eiList.get(index).get_price();
			// if unit has been in unit list, calculate the sum of price represented by this unit
			if (unitExist == true) {
				i--;
				temp = Double.parseDouble(total.get(i));
				temp += Double.parseDouble(p);
				total.set(i, String.valueOf(temp));
			// if it is a new unit, append it in to list
			} else {
				unit.add(u);
				total.add(p);
			}
			// update variables and check next item
			unitExist = false;
			index++;
		}
		// initialize string which will be returned
		this.RString = "";
		index = 0;
		// add all information in unit list and price list into string
		while (index < total.size()) {
			this.RString = this.RString + total.get(index) + "-" + unit.get(index)+"; \n";
			index++;
		}
		return this.RString;
	}
}
