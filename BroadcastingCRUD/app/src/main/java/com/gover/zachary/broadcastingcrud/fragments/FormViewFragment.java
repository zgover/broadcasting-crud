// Zachary Gover
// MDF3 - 1610
// FormViewFragment

package com.gover.zachary.broadcastingcrud.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gover.zachary.broadcastingcrud.R;

public class FormViewFragment extends Fragment {

	public static final String TAG = "FormViewFragment.TAG";

	public FormViewFragment(){}

	public static FormViewFragment newInstance() {
		return new FormViewFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the view
		return inflater.inflate(R.layout.form_view_fragment, container, false);
	}

}
