// Zachary Gover
// MDF3 - 1610
// DetailViewFragment

package com.gover.zachary.broadcastingcrud.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gover.zachary.broadcastingcrud.R;
import com.gover.zachary.broadcastingcrud.models.Person;

public class DetailViewFragment extends Fragment {

	public static final String TAG = "DetailViewFragment.TAG";

	public DetailViewFragment(){}

	public static DetailViewFragment newInstance() {
		return new DetailViewFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the view
		View view = inflater.inflate(R.layout.detail_view_fragment, container, false);

		// Get values and setup view
		Bundle args = getArguments();

		String fname = args.getString(Person.FNAME_KEY);
		String lname = args.getString(Person.LNAME_KEY);
		int age = args.getInt(Person.AGE_KEY, 0);

		((TextView) view.findViewById(R.id.fNameLbl)).setText(fname);
		((TextView) view.findViewById(R.id.lNameLbl)).setText(lname);
		((TextView) view.findViewById(R.id.ageLbl)).setText(Integer.toString(age));

		return view;
	}
}
