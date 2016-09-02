package com.example.thepolicykart2;



import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Plans extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View plans=inflater.inflate(R.layout.activity_plans,container,false);
		Button inv=(Button)plans.findViewById(R.id.inv);
		inv.setOnClickListener(this);
		((Button)plans.findViewById(R.id.pension)).setOnClickListener(this);
		((Button)plans.findViewById(R.id.child)).setOnClickListener(this);
		((Button)plans.findViewById(R.id.term)).setOnClickListener(this);
		((Button)plans.findViewById(R.id.money)).setOnClickListener(this);
		return plans;
	}
	@Override
	public void onClick(View v) {
		Intent i;
		// TODO Auto-generated method stub
		switch(v.getId())
				{
			case R.id.inv:
				i=new Intent(getActivity(), Investemnt_plan.class);
				startActivity(i);
				break;
			case R.id.child:
				i=new Intent(getActivity(), Child_plan.class);
				startActivity(i);
				break;
			case R.id.money:
				i=new Intent(getActivity(), Moneyback_plan.class);
				startActivity(i);
				break;
			case R.id.term:
				i=new Intent(getActivity(), Term_plan.class);
				startActivity(i);
				break;
			case R.id.pension:
				i=new Intent(getActivity(), Pension_plan.class);
				startActivity(i);
				break;
				
				
				}
		
	}
	

}
	