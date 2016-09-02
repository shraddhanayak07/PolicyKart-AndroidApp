package com.example.thepolicykart2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Query_Now extends Fragment {

    Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        context=container.getContext();
		// TODO Auto-generated method stub
		View plans=inflater.inflate(R.layout.activity_query__now,container,false);
		return plans;
	}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication myApplication= new MyApplication().getInstance();

        User userdetails = myApplication.getUserprofiledetails();
        int login1 = myApplication.getLoggedIn();
        Log.v("Query Now Login", Integer.toString(login1));

        final EditText Firstname=(EditText) view.findViewById(R.id.qfirst);
        final EditText Lastname=(EditText) view.findViewById(R.id.qlast);
        final EditText Mobile=(EditText) view.findViewById(R.id.qmobile);
        final EditText Email=(EditText) view.findViewById(R.id.qemail);
        final EditText Policynumber=(EditText) view.findViewById(R.id.policy);
        final EditText Comment=(EditText) view.findViewById(R.id.comment);

        final CheckBox Receivenews=(CheckBox) view.findViewById(R.id.checkBox1);
        final CheckBox Acceptterms=(CheckBox) view.findViewById(R.id.checkBox2);

        if(login1==1 || login1==2)
        {
            String name=userdetails.name;
            Log.v("name",name);
            int i=0;
            for (char c : name.toCharArray()) {
                if (Character.isSpaceChar(c)) {
                    break;
                }
                i++;
            }

            String First = name.substring(0, i);
            String Last = name.substring(i + 1);


            Firstname.setText(First);
            Lastname.setText(Last);

            if (userdetails.mobile.equals("Null")) {
                    Mobile.setText("");
                } else {
                    Mobile.setText(userdetails.mobile);
                }

                if (userdetails.email.equals("Null")) {
                    Email.setText("");
                } else {
                    Email.setText(userdetails.email);
                }

                if (Receivenews.isChecked() == true) {

                }
        }


        Button submit=(Button) view.findViewById(R.id.qsubmit);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Acceptterms.isChecked()==false)
                {
                    Toast.makeText(getContext(),"Please accept terms and conditions",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String firstname=Firstname.getText().toString();
                    String lastname=Lastname.getText().toString();
                    String mobile=Mobile.getText().toString();
                    String email=Email.getText().toString();
                    String policyN=Policynumber.getText().toString();
                    String comments=Comment.getText().toString();
                    String Ifreceivenews=new String();
                    if(Receivenews.isChecked()==true)
                    {
                        Ifreceivenews="yes";
                    }
                    else
                    {
                        Ifreceivenews="no";
                    }
                    QueryNowDetails queryNowDetails=new QueryNowDetails(firstname,lastname,mobile,email,policyN,comments,Ifreceivenews);
                    QueryNowDetailsIntoDatabase(queryNowDetails);
                }
            }
        });

    }

    private void QueryNowDetailsIntoDatabase(final QueryNowDetails queryNowDetails)
    {
        ServerRequests serverRequests=new ServerRequests(context);
        serverRequests.storequerynowdetails(queryNowDetails,new GetQueryNowDetails(){
            @Override
            public void done(QueryNowDetails returnedquerydetails) {
                Toast.makeText(getContext(),"Your query has been submitted successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}