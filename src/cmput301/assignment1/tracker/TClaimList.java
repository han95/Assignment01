package cmput301.assignment1.tracker;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * This class allows user to save a list of travel claims,
 * and get access to one object throw this class.
 * It also contains a list of listeners
 */
public class TClaimList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1266127983788933379L;
	// protected variables
	protected ArrayList<TClaim> tcList = null;
	protected transient ArrayList<Listener> listeners;
	
	// get access to listeners
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	// constructor
	public TClaimList(){
		tcList = new ArrayList<TClaim>();
	}
	
	// get travel claim list
	public ArrayList<TClaim> getTClaims(){
		return tcList;
	}
	
	// add new travel claim and let listeners know
	public void addTClaim(){//TClaim newTC){
		tcList.add(new TClaim());
		notifyListeners();
	}
	
	// remove new travel claim and let listeners know
	public void removeTClaim(TClaim rTC){
		tcList.remove(rTC);
		notifyListeners();
	}
	
	// get length of list
	public int getSize() {
		return tcList.size();
	}
	
	// get last travel claim in list
	public TClaim getLastTClaim() {
		int i = getSize() - 1;
		return tcList.get(i);
	}
	
	// notify listener when list is changed
	public void notifyListeners(){
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}
	
	// and new listener
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	// remove listener from list
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}

}
