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
	 * generated serialization ID
	 */
	private static final long serialVersionUID = 6611768379810913336L;
	
	// private variables
	protected String des = "";
	protected String start_date = "";
	protected String end_date = "";
	protected ArrayList<EItem> eiList = new ArrayList<EItem>();
	
	// constructor
	public TClaim(){
		
	}
	
	// overwrite to String function
	public String toString(){
		return get_description();
	}
	
	// get information: description of travel claim
	public String get_description(){
		return this.des;
	}
	
	// get information: expense items
	public ArrayList<EItem> get_eiList() {
		return eiList;
	}
	
	// set information of travel claim
	public void setInfo(String des, String start, String end) {
		this.des = des;
		this.start_date = start;
		this.end_date = end;
	}
}
