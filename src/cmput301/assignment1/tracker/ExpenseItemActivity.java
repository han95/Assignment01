package cmput301.assignment1.tracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ExpenseItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_item, menu);
		return true;
	}
	
	public void saveEItem(View v) {
		Toast.makeText(this, "saving expense item", Toast.LENGTH_SHORT).show();
		
		
		
		
		Intent intent = new Intent(ExpenseItemActivity.this, TravelClaimActivity.class);
		startActivity(intent);
	}

}
