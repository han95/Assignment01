package cmput301.assignment1.tracker;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
/*
 * This activity is executed when "add travel claim" menu is clicked in main activity layout
 * It allows user to edit new travel claim object, and shows saved expense items
 * It also allows user to edit new expense item, when user click "add expense item" menu.
 */
public class TravelClaimActivity extends Activity {
	// access to travel claim controller
	TClaimListController tcC = new TClaimListController();
	
	// on create
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_travel_claim);
		
		// initialize manager
		TClaimListManager.initManager(this.getApplicationContext());
		
		// get access to expense item list view
		ListView listView = (ListView) findViewById(R.id.expenseItemList);

		// get the expense item list and show it on screen
		Collection<EItem> eis = TClaimListController.getTClaimList().getLastTClaim().get_eiList();
		final ArrayList<EItem> list = new ArrayList<EItem>(eis);
		final ArrayAdapter<EItem> eiAdapter = new ArrayAdapter<EItem>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(eiAdapter);
	}
	/*
	protected void onResume(Bundle savedInstanceState) {
		super.onResume();
		setContentView(R.layout.add_travel_claim);
		
		TClaimListManager.initManager(this.getApplicationContext());
		// get access to expense item list view
		ListView listView = (ListView) findViewById(R.id.expenseItemList);

		// get the expense item list and show it on screen
		Collection<EItem> eis = TClaimListController.getTClaimList().getLastTClaim().get_eiList();
		final ArrayList<EItem> list = new ArrayList<EItem>(eis);
		final ArrayAdapter<EItem> eiAdapter = new ArrayAdapter<EItem>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(eiAdapter);
	}
	*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.travel_claim, menu);
		return true;
	}
	
	// This function is called when "save travel claim" button is clicked
	public void saveTC(View v){
		// Show sentence on screen to tell user object is being saved
		Toast.makeText(this, "saving travel claim", Toast.LENGTH_SHORT).show();
		
		// Get input from screen
		EditText des = (EditText) findViewById(R.id.descriptionEdit);
		EditText start = (EditText) findViewById(R.id.startDateEdit);
		EditText end = (EditText) findViewById(R.id.endDateEdit);
		
		// save information of travel claim
		TClaimListController.getTClaimList().getLastTClaim().setInfo(des.getText().toString(), start.getText().toString(), end.getText().toString());

		// Show sentence on screen to tell user that the object is saved
		Toast.makeText(this, "new claim is saved", Toast.LENGTH_SHORT).show();
		// Transfer to main activity automatically
		Intent intent = new Intent(TravelClaimActivity.this, MainActivity.class);
		startActivity(intent);
		//finish();
	}
	
	// This method is called when "add expense item" menu is clicked
	public void addExpenseItem(MenuItem menu) {
		// Show sentence on screen to ask user to edit expense item
		Toast.makeText(this, "add expense item", Toast.LENGTH_SHORT).show();
		// Transfer to another activity, where user can edit expense item
		Intent intent = new Intent(TravelClaimActivity.this, ExpenseItemActivity.class);
		startActivity(intent);
	}

}
