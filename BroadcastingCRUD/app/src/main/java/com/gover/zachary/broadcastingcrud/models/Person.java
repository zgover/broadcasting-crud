// Zachary Gover
// MDF3 - 1610
// Person

package com.gover.zachary.broadcastingcrud.models;

import java.io.Serializable;

public class Person implements Serializable {

	public static final String FNAME_KEY = "com.fullsail.android.EXTRA_FIRST_NAME";
	public static final String LNAME_KEY = "com.fullsail.android.EXTRA_LAST_NAME";
	public static final String AGE_KEY = "com.fullsail.android.EXTRA_AGE";

	private String fname;
	private String lname;
	private int age;

	public Person(){}

	@Override
	public String toString() {
		return fname + " " + lname;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person person = (Person) obj;

			// Check against all three data points
			if (person.getFname().equals(fname) ||
				person.getLname().equals(lname) ||
				person.getAge() == age) {

				return true;
			}
		}

		return false;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
