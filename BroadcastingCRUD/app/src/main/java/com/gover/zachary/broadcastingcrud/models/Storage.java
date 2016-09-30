// Zachary Gover
// MDF3 - 1610
// Storage

package com.gover.zachary.broadcastingcrud.models;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;

public class Storage {

	/**
	 * MARK: Global Properties
	 */

	private static final String FILE_NAME = "people_list";
	private static Storage instance;
	private Context context;
	private ArrayList<Person> people = new ArrayList<>();

	/**
	 * MARK: Class Initializer
	 */

	private Storage(Context _context){ context = _context; readPeople(); }

	public static Storage newInstance(Context _context) {
		if (instance != null) {
			return instance;
		}

		instance = new Storage(_context);
		return instance;
	}

	/**
	 * MARK: Custom Methods
	 */

	public void addPerson(Person _person) {
		people.add(_person);
		savePeople();
	}

	public ArrayList<Person> readPeople() {
		// Get the input stream
		FileInputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		people.clear();

		try {
			// Open the devices input streams so we may read the file
			inputStream = context.openFileInput(FILE_NAME);
			objectInputStream = new ObjectInputStream(inputStream);

			// Cast the object to its correct type
			Object person = objectInputStream.readObject();
			people = (ArrayList<Person>) person;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close the input streams
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return people;
	}

	private void savePeople() {
		// Get the file output stream
		FileOutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			// Open the devices output stream so we may save to it
			outputStream = context.openFileOutput(FILE_NAME, context.MODE_PRIVATE);
			objectOutputStream = new ObjectOutputStream(outputStream);

			// Add the new person the list
			objectOutputStream.writeObject(people);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the output streams
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deletePerson(int position) {
		people.remove(position);
		savePeople();
	}

	/**
	 * MARK: Getter and Setters
	 */

	public ArrayList<Person> getPeople() {
		return people;
	}

	public ArrayList<String> getStringResults() {
		ArrayList<String> stringResults = new ArrayList<>();

		for (Person person: people) {
			stringResults.add(person.toString());
		}

		return stringResults;
	}
}
