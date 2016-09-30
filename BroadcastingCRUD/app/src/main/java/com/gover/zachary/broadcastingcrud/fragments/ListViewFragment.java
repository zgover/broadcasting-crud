// Zachary Gover
// MDF3 - 1610
// ListViewFragment

package com.gover.zachary.broadcastingcrud.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.gover.zachary.broadcastingcrud.R;

public class ListViewFragment extends ListFragment {

	private static final String TAG = "ListViewFragment.TAG";
	private ClickListener listener;

	public ListViewFragment(){}

	public static ListViewFragment newInstance() {
		return new ListViewFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the view
		return inflater.inflate(R.layout.list_view_fragment, container, false);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Set listener to MainActivity
		this.listener = (ClickListener) activity;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		listener.listItemClick(position);
	}

	public interface ClickListener {
		void listItemClick(int position);
	}
}
