package com.example.thepolicykart2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Calculator extends Fragment {
    Context context;
    Double rebate,tabular_prem,prem;
    Double princ;
    Spinner s1,s2,s3,s4;
    String term,amt,time;
    String age,plan;
    EditText e;
    TextView t;
    Button b;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        MyApplication myApplication= new MyApplication().getInstance();

        User userdetails = myApplication.getUserprofiledetails();
        int login = myApplication.getLoggedIn();
        Log.v("Calculator Login", Integer.toString(login));
        context=container.getContext();
		// TODO Auto-generated method stub

		View plans=inflater.inflate(R.layout.activity_calculator,container,false);
        EditText firstname = (EditText) plans.findViewById(R.id.first_cal);
        EditText lastname = (EditText) plans.findViewById(R.id.last_cal);
        EditText mobile = (EditText) plans.findViewById(R.id.mobile_cal);
        EditText email = (EditText) plans.findViewById(R.id.email_cal);
        final Button applyonline = (Button) plans.findViewById(R.id.apply);



        if(login == 1 || login==2) {
            String name = userdetails.name;
            int i = 0;
            for (char c : name.toCharArray()) {
                if (Character.isSpaceChar(c)) {
                    break;
                }
                i++;
            }

            String first = name.substring(0, i);
            String last = name.substring(i + 1);

            Log.v("first", first);

            firstname.setText(first);
            lastname.setText(last);
            Log.v("last", last);

            if (userdetails.mobile.equals("Null")) {
                mobile.setText("");
            } else {
                mobile.setText(userdetails.mobile);
            }

            if (userdetails.email.equals("Null")) {
                email.setText("");
            } else {
                email.setText(userdetails.email);
            }
        }

        s1=(Spinner)plans.findViewById(R.id.term_years);
        s2=(Spinner) plans.findViewById(R.id.select_age); //connect term plan to spinners
        s3=(Spinner) plans.findViewById(R.id.plan);
        s4=(Spinner) plans.findViewById(R.id.plan_time);

        e=(EditText)plans.findViewById(R.id.amount); //connect the principal amt given to edittext

        t=(TextView)plans.findViewById(R.id.return_money);//connect to the textview to show how much user will get back.

        b=(Button)plans.findViewById(R.id.submit_cal);


		b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                amt = e.getText().toString();
                term = s1.getSelectedItem().toString();
                if(term.equals("Select Term Year"))
                {
                    Toast.makeText(getActivity(),"Select Term Year",Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.v("term", term);
                    age = s2.getSelectedItem().toString();
                    if(age.equals("Select Age"))
                    {
                        Toast.makeText(getActivity(),"Select Age",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        Log.v("age", age);
                        plan = s3.getSelectedItem().toString();
                        if(plan.equals("SelectP lan"))
                        {
                            Toast.makeText(getActivity(),"Select Plan",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            plan = plan.replace(" ", "");
                            Log.v("plan", plan);

                            time = s4.getSelectedItem().toString();

                            getTabularPremium(term, age, plan, amt, time,applyonline);
                        }
                    }
                                        // tabular_prem=((MyApplication) getActivity().getApplication()).gettp();
                    //  Log.v("tabularatclick", Double.toString(tabular_prem));
                }

            }
        });

        applyonline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyApplication myApplication = new MyApplication().getInstance();
                int login=myApplication.getLoggedIn();
                if(login!=1&&login!=2)
                {
                    Toast.makeText(getContext(), "You need to first login", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getActivity(), Apply_online.class);
                    String p = myApplication.getP();
                    i.putExtra("premium",p);
                    startActivity(i);
                }
            }
        });
        return plans;
	}

    public void getTabularPremium(final String term,String age, String plan,final String amt,final String time,final Button applyonline)
    {
        ServerRequests serverRequests=new ServerRequests(context);
        serverRequests.fetchtabularpremium(term, age, plan, new GetTabularPremium() {
            @Override
            public Double done(String tabularpremium) {
                Log.v("tabularpremium", "Reached Here");


                MyApplication myApplication=new MyApplication().getInstance();
              //  myApplication.settp(tabular_prem);
                if(tabularpremium==null)
                {
                    t.setText("Kindly make changes in term plan" );
                }
                else
                {
                    princ=Double.parseDouble(amt);
                    tabular_prem = Double.parseDouble(tabularpremium);

                    Log.v("tabular_prem", Double.toString(tabular_prem));

                    if (time.equals("Yearly") && princ < 200000) //assign rebate in case of sum not assured.
                    {
                        rebate = 0.02;

                    } else if (time.equals("Half Yearly") && princ < 200000) {
                        rebate = 0.01;
                    } else if (princ >= 200000 && princ <= 495000)// assign rebate in case of sum assured.
                    {
                        rebate = 1.5 * tabular_prem;
                    } else if (princ >= 500000 && princ <= 995000) {
                        rebate = 2.5 * tabular_prem;
                    } else if (princ > 1000000) {
                        rebate = 3 * tabular_prem;
                    } else {
                        rebate=1.0;
                    }
                    if(rebate==1.0)
                    {
                        t.setText("WRONG INPUT");
                    }
                    else {
                        applyonline.setVisibility(View.VISIBLE);
                        prem = (princ * tabular_prem) / 1000;//Calculate premium and set it in textview
                        Log.v("prem", Double.toString(prem));
                        prem = prem - (rebate * prem);
                        prem = Math.floor(prem * 100) / 100d;
                        t.setText("The pemium is: " + prem);
                        myApplication.setP(prem.toString());
                    }
                }
                return tabular_prem;
            }
        });
    }
}


