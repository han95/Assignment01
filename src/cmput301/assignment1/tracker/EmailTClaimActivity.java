package cmput301.assignment1.tracker;
/*
 * This is email activity
 * It is created when the user click "email this claim" menu
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmailTClaimActivity extends Activity {

	// create activity
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

	// This method is called when the user click "email this item" menu
	public void sendEmail(View v) {
		// check whether the format of email address is legal
		int i = 0;
		int countAt = 0;
		int countDot = 0;
		String addr = ((EditText) findViewById(R.id.emailAddressEdit)).getText().toString();
		while (i < addr.length()) {
			// if the email address contains @ character, the application considers it as legal.
			if (addr.charAt(i) == '@') {
				countAt++;
			}
			if (addr.charAt(i) == '.') {
				countDot++;
			}
			i++;
		}
		// if the format is wrong, ask the user to input again
		if (countAt!=1 | countDot!=1) {
			Toast.makeText(this, "please enter right email address!", Toast.LENGTH_LONG).show();
			
		// if it is right, go back to last activity
		} else {
			Toast.makeText(this, "this travel claim is sent", Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
