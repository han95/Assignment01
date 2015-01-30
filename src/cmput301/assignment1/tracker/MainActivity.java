package cmput301.assignment1.tracker;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
/*
 * Main activity is executed as soon as the application begins.
 * It shows all saved travel claims and allow user to choose add new travel claim
 */
public class MainActivity extends Activity {

	// initialize travel claim controller
	TClaimListController tcC = new TClaimListController();
	
	// on create
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize travel claim manager
		TClaimListManager.initManager(this.getApplicationContext());
		
		// find list view which contain travel claims
		ListView listView = (ListView) findViewById(R.id.TravelClaimList);
		// get saved travel claims
		Collection<TClaim> tcs = TClaimListController.getTClaimList().getTClaims();
		// change them to array list
		final ArrayList<TClaim> list = new ArrayList<TClaim>(tcs);
		final ArrayAdapter<TClaim> tcAdapter = new ArrayAdapter<TClaim>(this, android.R.layout.simple_list_item_1, list);
		// set array adapter, show claims on screen
		listView.setAdapter(tcAdapter);
		
		// update listener
		TClaimListController.getTClaimList().addListener(new Listener(){
			@Override
			public void update(){
				list.clear();
				Collection<TClaim> tcs = TClaimListController.getTClaimList().getTClaims();
				list.addAll(tcs);
				tcAdapter.notifyDataSetChanged();
			}
		});
		
		// long click: ask user if the item should be deleted
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
				adb.setMessage("Delete "+list.get(position).toString()+"?");
				adb.setCancelable(true);
				// get exact position of item which is clicked
				final int finalPosition = position;
				// add delete button which delete whole claim
				adb.setPositiveButton("Delete", new OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						TClaim tc = list.get(finalPosition);
						TClaimListController.getTClaimList().removeTClaim(tc);
					}
					
				});
				// add cancel button which cancel the delete action
				adb.setNegativeButton("Cancel", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Do Nothing
					}
				});
				adb.show();

				return false;
			}
		});
		
	}
	
/*
	public void onResume(Bundle savedInstanceState) {
		super.onResume();
		setContentView(R.layout.activity_main);
		
		TClaimListManager.initManager(this.getApplicationContext());
		// find list view which contain travel claims
		ListView listView = (ListView) findViewById(R.id.TravelClaimList);
		// get saved travel claims
		Collection<TClaim> tcs = TClaimListController.getTClaimList().getTClaims();
		// change them to array list
		final ArrayList<TClaim> list = new ArrayList<TClaim>(tcs);
		final ArrayAdapter<TClaim> tcAdapter = new ArrayAdapter<TClaim>(this, android.R.layout.simple_list_item_1, list);
		// set array adapter, show claims on screen
		listView.setAdapter(tcAdapter);
		
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// this function is called when "add travel claim" menu is clicked
	public void addTravelClaim(MenuItem menu){
		// show sentence on screen, to tell user to add information on other activity
		Toast.makeText(this, "add a new travel claim", Toast.LENGTH_SHORT).show();
		// build new claim object
		tcC.saveTClaim();
		// transfer to another activity
		Intent intent = new Intent(MainActivity.this, TravelClaimActivity.class);
		startActivity(intent);
		
	}

}
