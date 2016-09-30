// Zachary Gover
// MDF3 - 1610
// BroadcastingReceiver

package com.gover.zachary.broadcastingcrud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gover.zachary.broadcastingcrud.models.Person;
import com.gover.zachary.broadcastingcrud.models.Storage;

import java.util.ArrayList;

public class BroadcastingReceiver extends BroadcastReceiver {

	private Storage storage;

	@Override
	public void onReceive(Context context, Intent intent) {
		// Setup the storage
		storage = Storage.newInstance(context);

		// Determine the intent action
		String action = intent.getAction();

		switch(action) {
			case FormActivity.SAVE_DATA:
				saveData(context, intent);
				break;
			case DetailActivity.DELETE_DATA:
				deletePerson(context, intent);
				break;
			default:
				break;
		}
	}

	private Person buildPerson(Intent intent) {
		// Build a new person from the extras and save it to the device
		String fname = intent.getStringExtra(Person.FNAME_KEY);
		String lname = intent.getStringExtra(Person.LNAME_KEY);
		int age = intent.getIntExtra(Person.AGE_KEY, 0);

		Person person = new Person();

		person.setFname(fname);
		person.setLname(lname);
		person.setAge(age);

		return person;
	}

	private void saveData(Context context, Intent intent) {
		// Build a new person from the extras and save it to the device
		Person person = buildPerson(intent);
		storage.addPerson(person);

		updateList(context);
	}

	private void deletePerson(Context context, Intent intent) {
		// Build a new person from the extras check against the rest
		Person person = buildPerson(intent);
		ArrayList<Person> people = storage.getPeople();

		for (int i = 0; i < people.size(); i++) {
			if (person.equals(people.get(i))) {
				storage.deletePerson(i);
			}
		}

		updateList(context);
	}

	private void updateList(Context context) {
		// Update the list view
		Intent broadcast = new Intent(MainActivity.UPDATE_LIST);
		context.sendBroadcast(broadcast);
	}
}
