package cmput301.assignment1.tracker;

import android.app.Application;

public class GlobalV extends Application {

	private TClaim myTC = null;
	
	public TClaim getLabel(){
		return myTC;
	}
	
	public void setLabel(TClaim tc){
		myTC = tc;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
}
