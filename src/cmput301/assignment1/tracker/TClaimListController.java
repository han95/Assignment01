package cmput301.assignment1.tracker;

import java.io.IOException;

public class TClaimListController {
	private static TClaimList tcList = null;
	
	static public TClaimList getTClaimList(){
		if (tcList == null) {
			try {
				tcList = TClaimListManager.getManager().loadTClaimList();
				tcList.addListener(new Listener() {
					@Override
					public void update() {
						saveTClaimList();
					}
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deseralize tcList from TClaimListManager");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deseralize tcList from TClaimListManager");
			}
//			tcList = new TClaimList();
		}
		return tcList;
	}

	static public void saveTClaimList() {
		try{
			TClaimListManager.getManager().saveTClaimList(getTClaimList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not deseralize tcList from TClaimListManager");
		} 
	}
	
	public void saveTClaim() { 
		getTClaimList().addTClaim();
	}

	public void saveEItem(EItem ei) {
		getTClaimList().getLastTClaim().get_eiList().add(ei);
		
	}
	
	
}
