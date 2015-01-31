package cmput301.assignment1.tracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class EmailTClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_travel_claim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_tclaim, menu);
		return true;
	}

	public void sendEmail(View v) {
		Toast.makeText(this, "this travel claim is sent", Toast.LENGTH_LONG).show();
		finish();
	}
}
