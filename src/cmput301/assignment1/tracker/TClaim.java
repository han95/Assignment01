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
	 * Serialization ID
	 */
	private static final long serialVersionUID = 7863671238087385791L;
	// private variables
	protected String des = "";
	protected String start_date = "";
	protected String end_date = "";
	protected ArrayList<EItem> eiList = new ArrayList<EItem>();
	protected String status = "Inprogress";
	
	// constructor
	public TClaim(){
		
	}
	
	// overwrite to String function
	public String toString(){
		return get_description();
	}
	
	// get information: description of travel claim
	public String get_description(){
		return this.des+"--status:"+this.status;
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
