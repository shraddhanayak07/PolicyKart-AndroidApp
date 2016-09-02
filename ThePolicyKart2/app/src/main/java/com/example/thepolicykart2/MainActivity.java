package com.example.thepolicykart2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* User getUser = SaveSharedPreference.getUserName(MainActivity.this);

                if(getUser!=null)
                {
                    Log.v("value of getUser",getUser.name);
                }
                if(getUser == null)
                {
                    Intent it=new Intent(getApplicationContext(),Login.class);
                    startActivity(it);
                }
                else
                {
                    MyApplication myApplication=new MyApplication().getInstance();
                    myApplication.setUserprofiledetails(getUser);
                    Intent i =new Intent(getApplicationContext(),Home.class);
                    startActivity(i);
                }*/
                SaveSharedPreference.ClearUserData(MainActivity.this);
                MyApplication myApplication= new MyApplication().getInstance();

                myApplication.setLoggedIn(0);

                Intent i =new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });
    }

    public void launchTestService() {
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, MyTestService.class);
        // Add extras to the bundle
        i.putExtra("foo", "bar");
        // Start the service
        startService(i);
    }
}
