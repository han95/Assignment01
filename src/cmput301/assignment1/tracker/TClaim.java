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
	 * serialization ID
	 */
	private static final long serialVersionUID = -2962474581497384087L;
	// private variables
	protected String des = "";
	protected String start_date = "";
	protected String end_date = "";
	protected ArrayList<EItem> eiList = new ArrayList<EItem>();
	protected String status = "In progress";
	
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
		if (this.des != null | this.eiList != null | this.start_date != null | this.end_date != null) {
			return true;
		}
		return false;
	}
}
