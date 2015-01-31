package cmput301.assignment1.tracker;

public class ClaimIndex {

	private static TClaim tc;
	
	public void set_tc(TClaim new_tc) {
		tc = new_tc;
	}
	
	public TClaim get_tc() {
		if (tc==null) {
			tc = new TClaim();
		}
		return tc;
	}
}
