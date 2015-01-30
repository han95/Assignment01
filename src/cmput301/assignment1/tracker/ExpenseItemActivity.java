package cmput301.assignment1.tracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
 * This activity is executed when the "add expense item" menu in add_travel_claim layout is clicked
 * It asks user to input necessary information,
 * and add new expense item in travel claim object
 */
public class ExpenseItemActivity extends Activity {
	
	// get controller
	TClaimListController tcC = new TClaimListController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense_item);
		
		TClaimListManager.initManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_item, menu);
		return true;
	}
	
	// This function is called when "save expense item" button is clicked
	public void saveEItem(View v) {
		// show sentence on screen to tell user new item is being saved
		Toast.makeText(this, "saving expense item", Toast.LENGTH_SHORT).show();
		
		// get input from screen 
		EditText des = (EditText) findViewById(R.id.descriptionEdit);
		EditText date = (EditText) findViewById(R.id.dateEdit);
		EditText cat = (EditText) findViewById(R.id.categoryEdit);
		EditText price = (EditText) findViewById(R.id.priceEdit);
		EditText unit = (EditText) findViewById(R.id.unitEdit);
		// build new expense item and save it into list
		tcC.saveEItem(new EItem(des.getText().toString(), date.getText().toString(), cat.getText().toString(), price.getText().toString(), unit.getText().toString()));
		
		// transfer to another activity
		Intent intent = new Intent(ExpenseItemActivity.this, TravelClaimActivity.class);
		startActivity(intent);
	}

}
