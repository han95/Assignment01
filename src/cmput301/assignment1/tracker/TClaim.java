package cmput301.assignment1.tracker;

import java.io.Serializable;
import java.util.ArrayList;

public class TClaim implements Serializable {
	/**
	 * TClaim serializable ID = 938418800071445994L
	 */
	private static final long serialVersionUID = 938418800071445994L;
	protected String des = "";
	protected String start_date = "";
	protected String end_date = "";
	
	protected ArrayList<EItem> eiList = new ArrayList<EItem>();
	
	public TClaim(){
		
	}
	
	public String toString(){
		return get_description();
	}
	
	public String get_description(){
		return this.des;
	}
	
	public ArrayList<EItem> get_eiList() {
		return eiList;
	}
	
	public void setInfo(String des, String start, String end) {
		this.des = des;
		this.start_date = start;
		this.end_date = end;
	}
}
