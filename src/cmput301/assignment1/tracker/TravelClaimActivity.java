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
import android.widget.TextView;
import android.widget.Toast;
/*
 * This activity is executed when "add travel claim" menu is clicked in main activity layout
 * It allows user to edit new travel claim object, and shows saved expense items
 * It also allows user to edit new expense item, when user click "add expense item" menu.
 */
public class TravelClaimActivity extends Activity {
	// access to travel claim controller
	TClaimListController tcC = new TClaimListController();
	protected transient ArrayList<Listener> listeners;
	
	// get the current node we access to 
	TClaim tc = ClaimIndex.get_tc();
	
	// on create
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_travel_claim);
		
		// if tc is not empty:
		if (tc.has_content()) {
			
			// show all information on screen
			EditText des = (EditText) findViewById(R.id.descriptionEdit);
			EditText start = (EditText) findViewById(R.id.startDateEdit);
			EditText end = (EditText) findViewById(R.id.endDateEdit);
			TextView spend = (TextView) findViewById(R.id.TotalExpenseSpace);
			
			des.setText(tc.get_description());
			start.setText(tc.get_start_date());
			end.setText(tc.get_end_date());
			spend.setText(tc.total_expense());
		}
		
		// initialize manager
		TClaimListManager.initManager(this.getApplicationContext());
		
		// get access to expense item list view
		ListView listView = (ListView) findViewById(R.id.EItemList);

		// get the expense item list and show it on screen
		Collection<EItem> eis = TClaimListController.getTClaimList().getCurrentTClaim().get_eiList();
		final ArrayList<EItem> list = new ArrayList<EItem>(eis);
		final ArrayAdapter<EItem> eiAdapter = new ArrayAdapter<EItem>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(eiAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.travel_claim, menu);
		return true;
	}
	
	// This function is called when "save travel claim" button is clicked
	public void saveTC(View v){
		// check state, if the claim is submitted, editions are not allowed
		if (tc.get_status()=="Submitted") {
			Toast.makeText(this, "returning...", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(TravelClaimActivity.this, MainActivity.class);
			startActivity(intent);
		} else if (tc.get_status()=="Approved") {
			Toast.makeText(this, "Return to travel claim list", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(TravelClaimActivity.this, MainActivity.class);
			startActivity(intent);
		} else {
			// Show sentence on screen to tell user object is being saved
			Toast.makeText(this, "Submitted travel claim", Toast.LENGTH_SHORT).show();
			tc.change_status("Submitted");
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
		}
	
	}
	
	// This method is called when "add expense item" menu is clicked
	public void addExpenseItem(MenuItem menu) {
		// check state, if state is "Submitted", refuse editing
		if (tc.get_status()=="Submitted") {
			Toast.makeText(this, "this travel claim is submitted, \n" +
					"adding expense item is not allowed", Toast.LENGTH_LONG).show();
		
		// if state is "Approved", refuse editing
		} else if (tc.get_status()=="Approved") {
			Toast.makeText(this, "this travel claim is approved, \n" +
					"adding expense item is not allowed", Toast.LENGTH_LONG).show();
		
		// if state is "In progress" or "returned", allow adding
		} else {
			// Show sentence on screen to ask user to edit expense item
			Toast.makeText(this, "add expense item", Toast.LENGTH_SHORT).show();
			// Transfer to another activity, where user can edit expense item
			Intent intent = new Intent(TravelClaimActivity.this, ExpenseItemActivity.class);
			startActivity(intent);
		}
	}
	
	// This method is called when "email this claim" menu is clicked
	public void emailTClaim(MenuItem menu) {
		Toast.makeText(this, "prepare email page", Toast.LENGTH_SHORT).show();
		// transfer to email page
		Intent intent = new Intent(TravelClaimActivity.this, EmailTClaimActivity.class);
		startActivity(intent);
	}
}
