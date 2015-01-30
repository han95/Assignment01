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

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TClaimListManager.initManager(this.getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.TravelClaimList);
		Collection<TClaim> tcs = TClaimListController.getTClaimList().getTClaims();
		final ArrayList<TClaim> list = new ArrayList<TClaim>(tcs);
		final ArrayAdapter<TClaim> tcAdapter = new ArrayAdapter<TClaim>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(tcAdapter);
		
		TClaimListController.getTClaimList().addListener(new Listener(){
			@Override
			public void update(){
				list.clear();
				Collection<TClaim> tcs = TClaimListController.getTClaimList().getTClaims();
				list.addAll(tcs);
				tcAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
				adb.setMessage("Delete "+list.get(position).toString()+"?");
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						TClaim tc = list.get(finalPosition);
						TClaimListController.getTClaimList().removeTClaim(tc);
					}
					
				});
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addTravelClaim(MenuItem menu){
		Toast.makeText(this, "add a new travel claim", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, TravelClaimActivity.class);
		startActivity(intent);
	}

}
