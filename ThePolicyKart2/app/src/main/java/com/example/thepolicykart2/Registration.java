package com.example.thepolicykart2;

import com.example.thepolicykart2.R.string;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity {

	Context ctx=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		final EditText Name=(EditText)findViewById(R.id.name);
		final EditText Username=(EditText)findViewById(R.id.username);
		final EditText Password=(EditText)findViewById(R.id.password);
		final EditText Repassword=(EditText)findViewById(R.id.renter);
		final EditText Email=(EditText)findViewById(R.id.email);
		final EditText Mobile=(EditText)findViewById(R.id.mobile);
		Button submit=(Button)findViewById(R.id.sub);
		final UserLocalStore userlocaldata;
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=Name.getText().toString();
				String username=Username.getText().toString();
				String password=Password.getText().toString();
				String repassword=Repassword.getText().toString();
				String email=Email.getText().toString();
				String mobile=Mobile.getText().toString();

				if(name.equals("")||username.equals("")||password.equals("")||repassword.equals("")||email.equals("")||
						mobile.equals(""))
				{
					Toast.makeText(getApplicationContext(),"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
					if(name.equals(""))
					{
						Name.setHintTextColor(getResources().getColor(R.color.red));
					}
					if(username.equals(""))
					{
						Username.setHintTextColor(getResources().getColor(R.color.red));
					}
					if(password.equals(""))
					{
						Password.setHintTextColor(getResources().getColor(R.color.red));
					}
					if(repassword.equals(""))
					{
						Repassword.setHintTextColor(getResources().getColor(R.color.red));
					}

				}
                else if(isValidEmail(email)==false)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                    Email.setHintTextColor(getResources().getColor(R.color.red));

                }
                else if (isValidMobile(mobile)==false)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Mobile Number",Toast.LENGTH_SHORT).show();
                    Mobile.setHintTextColor(getResources().getColor(R.color.red));

                }
				else if(password.equalsIgnoreCase(repassword))
				{
				//	DatabaseOperations DO =new DatabaseOperations(ctx);
				//	DO.putInformation(DO, name, username, password, mobile, email);
				//	Toast.makeText(getBaseContext(), "Suucessful", Toast.LENGTH_SHORT).show();
				//	finish();
					
					User user=new User(name,username,password,email,mobile);
					
					RegisteredUser(user);
				}
				else
				{
					Password.setText("");
					Repassword.setText("");
					Toast.makeText(getApplicationContext(), "Password and Re-enter Password do not match", 
							Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
	}
	
	private void RegisteredUser(final User user)
	{
		final ServerRequests serverrequest=new ServerRequests(this);
        serverrequest.checknameindatabase(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    serverrequest.storeuserdatainbackground(user, new GetUserCallBack() {

                        @Override
                        public void done(User returnedUser) {
                            // TODO Auto-generated method stub
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Username or Email Exists", Toast.LENGTH_SHORT).show();

                }
            }

        });

	}
    private final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private final static boolean isValidMobile(String target) {
        if (target == null) {
            return false;
        } else if(target.length()!=10){
            return false;
        }
        else
        {
            return true;
        }
    }

	@Override
	public void onBackPressed() {
		Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
	}
}
