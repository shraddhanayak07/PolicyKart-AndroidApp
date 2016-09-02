package com.example.thepolicykart2;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;


public class Login extends Activity{

    Context ctx=this;
    int buttonclick=0;
    User user1;
    public String caller;
    int pressed=0;

   /* public String getValue()
    {
        return caller;
    }*/

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

        final RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.scrollView1);
		final EditText Username=(EditText)findViewById(R.id.username);
		final EditText Password=(EditText)findViewById(R.id.password);
		final Button b=(Button) findViewById(R.id.submit);
        final Button fb=(Button) findViewById(R.id.loginwithfacebook);
        final Button google=(Button) findViewById(R.id.loginwithgoogle);
		final TextView reg=(TextView)findViewById(R.id.reg);
        final FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame);
        final ImageView imageView=(ImageView)findViewById(R.id.imageView1);
        final TextView forgotpassword=(TextView)findViewById(R.id.forgot_password);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),GooglePlus.class);
                startActivity(i);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),forgotpassword.class);
                startActivity(i);
            }
        });

       fb.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {


            Intent i=new Intent(Login.this,framelayout.class);
               i.putExtra("caller","Login");
               startActivity(i);
                pressed=1;
           }
       });

		b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pressed = 1;
                String username = Username.getText().toString();
                String password = Password.getText().toString();

                if (username.equals("")) {
                    Username.setText("");
                    Username.setHintTextColor(getResources().getColor(R.color.red));
                } else if (password.equals("")) {
                    Password.setText("");
                    Password.setHintTextColor(getResources().getColor(R.color.red));
                } else {
                    User user = new User(username, password);
                    authenticate(user);
                }
            }
        });

		reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);
            }
        });
	}

    private void authenticate(User user)
    {
        ServerRequests serverrequests=new ServerRequests(this);
        serverrequests.fetchuserdatainbackground(user, new GetUserCallBack() {

             @Override
             public void done(User returnedUser) {
             // TODO Auto-generated method stub
             if (returnedUser == null) {

                     Toast.makeText(getApplicationContext(), "Invalid Username/Password",
                             Toast.LENGTH_SHORT).show();
                 } else {
                    SaveSharedPreference.setUserName(Login.this,returnedUser);
                    user1=new User(1);
                    Intent i = new Intent(getApplicationContext(), UserProfileDetails.class);
                    MyApplication myApplication=new MyApplication().getInstance();
                    myApplication.setUserprofiledetails(returnedUser);
                    myApplication.setLoggedIn(1);
                    i.putExtra("User Details",returnedUser);
                    i.putExtra("LoggedIn", user1);
                    i.putExtra("caller","Login");
                    startActivity(i);
                 }
             }
         });
	 }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
           // SaveSharedPreference.executex();
    }
}
