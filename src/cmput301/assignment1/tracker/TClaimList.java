package cmput301.assignment1.tracker;

import java.io.Serializable;
import java.util.ArrayList;

public class TClaimList implements Serializable {
	
	/**
	 * TClaimList serialization ID = 1919584890526746352L
	 */
	private static final long serialVersionUID = 1919584890526746352L;
	protected ArrayList<TClaim> tcList = null;
	protected transient ArrayList<Listener> listeners;
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public TClaimList(){
		tcList = new ArrayList<TClaim>();
	}
	
	public ArrayList<TClaim> getTClaims(){
		return tcList;
	}
	
	public void addTClaim(TClaim newTC){
		tcList.add(newTC);
		notifyListeners();
	}
	
	public void removeTClaim(TClaim rTC){
		tcList.remove(rTC);
		notifyListeners();
	}
	
	public void notifyListeners(){
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}

}
