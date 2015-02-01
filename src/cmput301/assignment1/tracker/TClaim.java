package cmput301.assignment1.tracker;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Travel claim class.
 * It allows user to get and add travel claim, 
 * user can also get expense items throw this class
 */
public class TClaim implements Serializable {

	/**
	 * 
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
		int index = 0;
		int i = 0;
		String u = "";
		String p = ""; 
		double temp = 0;
		ArrayList<String> total = new ArrayList<String>();
		ArrayList<String> unit = new ArrayList<String>();
		boolean unitExist = false;
		while (index < eiList.size()) {
			u = eiList.get(index).get_unit();
			i = 0;
			while (i < unit.size() & unitExist == false) {
				if (u == unit.get(i)) {
					unitExist = true;
				}
				i++;
			}
			p = eiList.get(index).get_price();
			if (unitExist == true) {
				i--;
				temp = Double.parseDouble(total.get(i));
				temp += Double.parseDouble(p);
				total.set(i, String.valueOf(temp));
			} else {
				unit.add(u);
				total.add(p);
			}
			index++;
		}
		this.RString = "";
		index = 0;
		while (index < total.size()) {
			this.RString = this.RString + total.get(index) + "-" + unit.get(index)+"; \n";
			index++;
		}
		return this.RString;
	}
}
