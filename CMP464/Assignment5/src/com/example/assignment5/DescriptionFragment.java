package com.example.assignment5;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class DescriptionFragment extends Fragment{
	WebView wv;
	String des="hello";
	View view;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.description, container,false);
		wv = (WebView)view.findViewById(R.id.webView1);
		return view;
	}
	public void setText(String description){
		
		wv.loadData(description, "text/html", null);
	}

}
