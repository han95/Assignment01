package cmput301.assignment1.tracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
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
		boolean form = false;
		int i = 0;
		String addr = ((EditText) findViewById(R.id.emailAddressEdit)).getText().toString();
		i = 0;
		while (i < addr.length()) {
			if (addr.charAt(i) == '@') {
				form = true;
			}
			i++;
		}
		if (!form) {
			Toast.makeText(this, "please enter right email address!", Toast.LENGTH_LONG).show();
			
		} else {
			Toast.makeText(this, "this travel claim is sent", Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
