package com.example.assignment5;

import java.util.ArrayList;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment {
	ArrayAdapter<String> adapter;
	private String[] des;
	View prev = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,new String[]{"Loding"});
		setListAdapter(adapter);
		return inflater.inflate(R.layout.my_list, container,false);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		DescriptionFragment fragB = (DescriptionFragment) getFragmentManager().findFragmentByTag("desFrag");
		fragB.setText(des[position]);
		
	}

	public void chageAdapter(ArrayList<String> title) {
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,title);
		setListAdapter(adapter);
		
	}

	public void pushDescriptionList(String[] description) {
		des = description;
		
	}


}