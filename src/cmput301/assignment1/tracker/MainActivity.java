/*
Tracker: Record expense items and calculate total expense in travel
Copyright (C) 2015 Han Wang

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/


package cmput301.assignment1.tracker;


/*
 * Main activity is executed as soon as the application begins.
 * It shows all saved travel claims and allow user to choose add new travel claim
 */

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// initialize travel claim controller
	TClaimListController tcC = new TClaimListController();
	
	// initialize global value
	//ClaimIndex tcPosition = new ClaimIndex();
	
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

		// when user click saved claim: 
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapterV, View v, int index,
					long id) {

				// update global value, save the position in travel claim list
				ClaimIndex.set_tc(TClaimListController.getTClaimList().getTClaims().get(index));
				Intent intent = new Intent(MainActivity.this,ViewTravelClaimActivity.class);
				startActivity(intent);
				
			}
			
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	 * This function is called when "add travel claim" menu is clicked
	 * It starts a new activity called AddTravelClaimActivity
	 */
	public void addTravelClaim(MenuItem menu){
		
		// show sentence on screen, to tell user to add information on other activity
		Toast.makeText(this, "add a new travel claim", Toast.LENGTH_SHORT).show();
		
		// build new claim object
		tcC.saveTClaim();
		
		//update global variable
		ClaimIndex.set_tc(TClaimListController.getTClaimList().getLastTClaim());
		
		// transfer to another activity
		Intent intent = new Intent(MainActivity.this, TravelClaimActivity.class);
		startActivity(intent);
		
	}

}
