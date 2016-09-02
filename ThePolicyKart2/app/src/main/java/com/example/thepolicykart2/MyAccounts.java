package com.example.thepolicykart2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyAccounts extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccounts);

        MyApplication myApplication = new MyApplication().getInstance();
        final User user = myApplication.getUserprofiledetails();

        final TextView tname = (TextView) findViewById(R.id.auname);
        final CheckBox tick = (CheckBox) findViewById(R.id.set);
        final EditText ename = (EditText) findViewById(R.id.username);
        final TextView tprem = (TextView) findViewById(R.id.prem_amt);
        final EditText eprem = (EditText) findViewById(R.id.amt);
        final TextView tplan = (TextView) findViewById(R.id.accplan);
        final EditText eplan = (EditText) findViewById(R.id.eamt);
        Boolean b = SaveSharedPreference.getcheckbox(this);
        if(b==true)
        {
            tick.setChecked(true);
        }
        else {
            tick.setChecked(false);
            tprem.setVisibility(View.INVISIBLE);
            eprem.setVisibility(View.INVISIBLE);
            tplan.setVisibility(View.INVISIBLE);
            eplan.setVisibility(View.INVISIBLE);
        }

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tick.isChecked()==true)
                {
                    SaveSharedPreference.setcheckbox(getApplicationContext(), true);
                    ename.setHint("Enter email id and then click set reminder");
                    String mail = ename.getText().toString();
                    tprem.setVisibility(View.VISIBLE);
                    eprem.setVisibility(View.VISIBLE);
                    tplan.setVisibility(View.VISIBLE);
                    eplan.setVisibility(View.VISIBLE);

                    Intent i = new Intent(getApplicationContext(), MyTestService.class);
                    // Add extras to the bundle
                    i.putExtra("foo", "bar");
                    // Start the service
                    startService(i);

                    setreminder(mail, ename, eprem, eplan);
                }
                else
                {
                    SaveSharedPreference.setcheckbox(getApplicationContext(),false);
                    tprem.setVisibility(View.INVISIBLE);
                    eprem.setVisibility(View.INVISIBLE);
                    tplan.setVisibility(View.INVISIBLE);
                    eplan.setVisibility(View.INVISIBLE);
                }
                }
            });
    }
    private void setreminder(final String email, final EditText ename, final EditText eprem, final EditText eplan)
    {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.setreminder(email,new ReturnApplyNowDetails(){
            @Override
            public void done(ApplyOnlineData returnapplynowdetails) {
                ename.setText(email);
                Log.v("plan",returnapplynowdetails.premium);
                eplan.setText(returnapplynowdetails.plan);
                eprem.setText(returnapplynowdetails.premium);
            }
        });
    }
}
