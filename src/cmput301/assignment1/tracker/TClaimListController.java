package cmput301.assignment1.tracker;

import java.io.IOException;

/*
 * This is a controller of this application
 */
public class TClaimListController {
	
	private static TClaimList tcList = null;
	
	// get travel claim list
	static public TClaimList getTClaimList(){
		// if the list has not been initialized
		if (tcList == null) {
			try {
				// load list from manager
				tcList = TClaimListManager.getManager().loadTClaimList();
				// update listener
				tcList.addListener(new Listener() {
					@Override
					public void update() {
						saveTClaimList();
					}
				});
				
			// deal with IOException
			} catch (IOException e) {
				e.printStackTrace();
				// stop application
				throw new RuntimeException("Could not deseralize tcList from TClaimListManager");
			// deal with exception when class is not found
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				// stop application
				throw new RuntimeException("Could not deseralize tcList from TClaimListManager");
			}
		}
		return tcList;
	}

	// allow user to save a travel claim list through controller
	static public void saveTClaimList() {
		//deal with IOException
		try{
			TClaimListManager.getManager().saveTClaimList(getTClaimList());
		} catch (IOException e) {
			e.printStackTrace();
			// stop application
			throw new RuntimeException("Could not deseralize tcList from TClaimListManager");
		} 
	}
	
	// save one travel claim through controller
	public void saveTClaim() { 
		getTClaimList().addTClaim();
	}

	// save one expense item through controller
	public void saveEItem(EItem ei) {
		getTClaimList().getLastTClaim().get_eiList().add(ei);
		
	}
	
	
}
