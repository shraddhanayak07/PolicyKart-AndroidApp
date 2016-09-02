package com.example.thepolicykart2;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Jeevan_labh extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View plans=inflater.inflate(R.layout.activity_jeevan_labh,container,false);
		((Button)plans.findViewById(R.id.apply_labh)).setOnClickListener(this);
		return plans;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MyApplication myApplication = new MyApplication().getInstance();
		int login=myApplication.getLoggedIn();
		if(login!=1&&login!=2)
		{
			Toast.makeText(getContext(), "You need to first login", Toast.LENGTH_SHORT).show();
		}
		else {
			Intent i = new Intent(getActivity(), Apply_online.class);
			startActivity(i);
		}
	}
	

}