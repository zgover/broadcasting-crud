// Zachary Gover
// MDF3 - 1610
// DetailActivity

package com.gover.zachary.broadcastingcrud;

import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.gover.zachary.broadcastingcrud.fragments.DetailViewFragment;
import com.gover.zachary.broadcastingcrud.models.Person;

public class DetailActivity extends AppCompatActivity {

	public static final String DELETE_DATA = "com.fullsail.android.ACTION_DELETE_DATA";
	public static final String VIEW_DATA = "com.fullsail.android.ACTION_VIEW_DATA";

	private String fname;
	private String lname;
	private int age;
	private BroadcastReceiver bReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Setup the fragment
		if(savedInstanceState == null) {
			DetailViewFragment frag = DetailViewFragment.newInstance();

			// Get values and setup view
			Intent intent = getIntent();
			Bundle args = new Bundle();

			fname = intent.getStringExtra(Person.FNAME_KEY);
			lname = intent.getStringExtra(Person.LNAME_KEY);
			age = intent.getIntExtra(Person.AGE_KEY, 0);

			// Setup the arguments for the fragment
			args.putString(Person.FNAME_KEY, fname);
			args.putString(Person.LNAME_KEY, lname);
			args.putInt(Person.AGE_KEY, age);
			frag.setArguments(args);

			getFragmentManager().beginTransaction()
				.replace(R.id.container, frag, DetailViewFragment.TAG).commit();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		bReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				closeActivity();
			}
		};

		// Register each receiver
		IntentFilter filter = new IntentFilter();

		filter.addAction(MainActivity.UPDATE_LIST);

		registerReceiver(bReceiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(bReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Setup Action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.detail_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Determine selection
		switch(item.getItemId()) {
			case R.id.action_delete:
				deletePerson();
				break;
			default:
				break;
		}

		return true;
	}

	private void closeActivity() {
		finish();
	}

	private void deletePerson() {
		// Confirm with the user before deleting
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Are you sure?");
		builder.setMessage("Are you sure you would like to delete this person?");
		builder.setPositiveButton("Cancel", null);
		builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				// Create the broadcast to send
				Intent broadcast = new Intent(DELETE_DATA);

				broadcast.putExtra(Person.FNAME_KEY, fname);
				broadcast.putExtra(Person.LNAME_KEY, lname);
				broadcast.putExtra(Person.AGE_KEY, age);

				sendBroadcast(broadcast);
			}
		}).show();
	}
}
