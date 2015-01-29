package cmput301.assignment1.tracker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class TClaimListManager {
	static final String prefFile = "TravelClaimList";
	static final String tclkey = "travelClaimList";
	Context context;
	
	static private TClaimListManager tcListManager = null;
	
	public static void initManager(Context context) {
		if (tcListManager==null){
			if (context==null) {
				throw new RuntimeException("missing context for tcListmanager");
			}
			tcListManager = new TClaimListManager(context);
		}
	}
	
	public static TClaimListManager getManager(){
		if (tcListManager==null) {
			throw new RuntimeException("Did not initialize manager");
		}
		return tcListManager;
	}
	
	public TClaimListManager(Context context){
		this.context = context;
	}
	
	public TClaimList loadTClaimList() throws StreamCorruptedException, IOException, ClassNotFoundException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String tcListData = settings.getString(tclkey, "");
		if (tcListData.equals("")){
			return new TClaimList();
		} else {
			return TClaimFromString(tcListData);
		}

	}
	
	static public TClaimList TClaimFromString(String tcListData) throws StreamCorruptedException, IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(tcListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TClaimList)oi.readObject();
	}
	static public String TClaimListToString(TClaimList tcl) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(tcl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	public void saveTClaimList(TClaimList tcl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(tclkey, TClaimListToString(tcl));
        editor.commit();
	}


}
