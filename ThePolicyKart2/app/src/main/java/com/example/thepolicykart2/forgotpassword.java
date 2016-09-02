package com.example.thepolicykart2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class forgotpassword extends Activity {

    private static final String username = "policykart@gmail.com";
    private static final String password = "ThePolicyKart1994";


    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        final EditText et=(EditText)findViewById(R.id.enteremail);
        Button b=(Button)findViewById(R.id.forgotpwdbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et.getText().toString();
                User user = new User(email);
                FindEmailInDatabase(user, email);

            }
        });
    }

    private void FindEmailInDatabase(User user, final String email)
    {
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.fetchemail(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    Toast.makeText(getApplicationContext(), "This EmailId is not registered in the database", Toast.LENGTH_SHORT).show();
                } else {
                    sendMail(email);
                }
            }
        });
    }

    private void sendMail(String email) {
        Session session = createSessionObject();
        try {
            Message message = createMessage(email, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(String email, Session session) throws MessagingException, UnsupportedEncodingException {

        String str=getRandomString(6);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("policykart@gmail.com", "The Policy Kart"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject("Reset Password");
        message.setText("New Password is " + str);
        editdatabase(email, str);
        return message;
    }

    private void editdatabase(String email,String password)
    {
        ServerRequests serverRequests=new ServerRequests(this);
        User user=new User(email,password);
        serverRequests.changepasswordindatabase(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                Toast.makeText(getApplicationContext(),"An email has been sent",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }

        });
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(forgotpassword.this, "Please wait", "Sending mail", true, false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}

