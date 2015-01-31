package cmput301.assignment1.tracker;

/*
 * This class acts as global variable
 * It records the node (travel claim) that we get access to.
 */
public class ClaimIndex {

	private static TClaim tc;
	
	// update node
	public static void set_tc(TClaim new_tc) {
		tc = new_tc;
	}
	
	// get node
	public static TClaim get_tc() {
		// deal with null pointer
		if (tc==null) {
			tc = new TClaim();
		}
		return tc;
	}
}
