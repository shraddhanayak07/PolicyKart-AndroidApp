package com.example.thepolicykart2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserProfileDetails extends Activity {

   // public User userdetailsforall =(User)getIntent().getSerializableExtra("User Details");
    User userdetailsforall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        final User loggin=(User)getIntent().getSerializableExtra("LoggedIn");

       final EditText Name=(EditText)findViewById(R.id.name);

       final EditText Mobile=(EditText)findViewById(R.id.mobile);
       final EditText Email=(EditText)findViewById(R.id.email);
      //  final EditText Username=(EditText)findViewById(R.id.username);
        Button logout=(Button)findViewById(R.id.logout);
        Button changepassword=(Button)findViewById(R.id.change_password);
      //  Button editchanges=(Button)findViewById(R.id.edit_details);
        TextView account = (TextView)findViewById(R.id.myaccounts);

        account.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProfileDetails.this,MyAccounts.class);
                startActivity(i);
            }
        });

        account.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MyAccounts.class);
                startActivity(i);
            }
        });


        userdetailsforall=((MyApplication) this.getApplication()).getUserprofiledetails();
        final int login = ((MyApplication) this.getApplication()).getLoggedIn();

        Name.setText(userdetailsforall.name);
        Log.v("ValuePlease",userdetailsforall.name);
        Mobile.setText(userdetailsforall.mobile);
        Email.setText(userdetailsforall.email);
     //   Username.setText(userdetailsforall.username);

     /*   editchanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile=Mobile.getText().toString();
                String email=Email.getText().toString();
                User user=new User(userdetailsforall.name,userdetailsforall.username,
                        userdetailsforall.password,email,mobile);
               EditDetails(user);
            }
        }); */

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login == 1) {

                   // SaveSharedPreference.executex();
                    SaveSharedPreference.ClearUserData(UserProfileDetails.this);
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);

                } else if (login == 2) {
                    SaveSharedPreference.ClearUserData(UserProfileDetails.this);
                    Intent i = new Intent(getApplicationContext(), framelayout.class);
                    i.putExtra("caller", "UserProfileDetails");
                    startActivity(i);
                }
                SaveSharedPreference.ClearUserData(UserProfileDetails.this);
                MyApplication myApplication= new MyApplication().getInstance();

                myApplication.setLoggedIn(0);
             //   myApplication.clearUserprofiledetails();
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),changepassword.class);
                i.putExtra("username", userdetailsforall.username.toString());
                i.putExtra("email",Email.getText().toString());
                startActivity(i);
            }
        });
    }
    /*private void EditDetails(final User user)
    {
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.updatedetails(user,new GetUserCallBack(){
            @Override
            public void done(User returnedUser) {
                MyApplication myApplication=new MyApplication().getInstance();
                myApplication.setUserprofiledetails(user);
            }
        });
    }*/
}