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

public class TravelClaimActivity extends Activity {
	
	TClaimListController tcC = new TClaimListController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_travel_claim);
		
		TClaimListManager.initManager(this.getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.expenseItemList);

		Collection<EItem> eis = TClaimListController.getTClaimList().getLastTClaim().get_eiList();
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
	
	public void saveTC(View v){
		Toast.makeText(this, "saving travel claim", Toast.LENGTH_SHORT).show();
		
		
		EditText des = (EditText) findViewById(R.id.descriptionEdit);
		EditText start = (EditText) findViewById(R.id.startDateEdit);
		EditText end = (EditText) findViewById(R.id.endDateEdit);
		
		TClaimListController.getTClaimList().getLastTClaim().setInfo(des.getText().toString(), start.getText().toString(), end.getText().toString());

		
		Toast.makeText(this, "new claim is saved", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(TravelClaimActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void addExpenseItem(MenuItem menu) {
		Toast.makeText(this, "add expense item", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(TravelClaimActivity.this, ExpenseItemActivity.class);
		startActivity(intent);
	}

}
