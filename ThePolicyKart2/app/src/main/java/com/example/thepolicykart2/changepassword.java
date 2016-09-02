package com.example.thepolicykart2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changepassword extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        final String username=(String)getIntent().getSerializableExtra("username");
        final String email=(String)getIntent().getSerializableExtra("email");
        final EditText oldpassword=(EditText)findViewById(R.id.oldpassword);
        final EditText newpassword=(EditText)findViewById(R.id.newpassword);
        final EditText confirmpassword=(EditText)findViewById(R.id.confirmpassword);

        Button b=(Button)findViewById(R.id.submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newp=newpassword.getText().toString();
                String confirm=confirmpassword.getText().toString();
                String password=oldpassword.getText().toString();
                User user=new User(username,password);

                authenticate(user,newp,confirm,email);


            }
        });


    }

    private void authenticate(final User user,final String newp, final String confirm,final String email)
    {
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.fetchuserdatainbackground(user, new GetUserCallBack() {

            @Override
            public void done(User returnedUser) {
                // TODO Auto-generated method stub
                if (returnedUser == null) {

                    Toast.makeText(getApplicationContext(), "Old password is invalid",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (newp.equals(confirm)) {
                        editdatabase(email, newp);
                    } else {
                        Toast.makeText(getApplicationContext(), "New and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void editdatabase(String email,String password)
    {
        ServerRequests serverRequests=new ServerRequests(this);
        User user=new User(email,password);
        serverRequests.changepasswordindatabase(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
              /*  User user1=new User(1);
                Log.v("name",returnedUser.name);
                Intent i = new Intent(changepassword.this, Home.class);
                i.putExtra("User Details",returnedUser);
                i.putExtra("LoggedIn",user1);
                i.putExtra("caller","Login");
                startActivity(i);*/
                Toast.makeText(getApplicationContext(),"Password Changed",Toast.LENGTH_SHORT).show();
            }

        });
    }
}
