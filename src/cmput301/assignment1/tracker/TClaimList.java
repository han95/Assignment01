package cmput301.assignment1.tracker;

/*
 * This class allows user to save a list of travel claims,
 * and get access to one object throw this class.
 * It also contains a list of listeners
 */

import java.io.Serializable;
import java.util.ArrayList;

public class TClaimList implements Serializable {

	/**
	 * generated serialization ID
	 */
	private static final long serialVersionUID = 5149747580509242561L;
	
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
	
	// get the current node we access to
	public TClaim getCurrentTClaim() {
		return ClaimIndex.get_tc();
	}
	
	// add new travel claim and let listeners know
	public void addTClaim(){
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
