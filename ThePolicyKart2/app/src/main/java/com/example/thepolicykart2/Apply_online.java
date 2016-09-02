  package com.example.thepolicykart2;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.MediaStore;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
public class Apply_online extends Activity {
    TextView tv;
    public User userdetails;
    public  String filePath;
    ProgressDialog prgDialog;
    String encodedString;
    RequestParams params =new RequestParams();
    String imgPath, fileName;
    Bitmap bitmap;
    private static int RESULT_LOAD_IMG = 1;
    private static final int PICKFILE_RESULT_CODE = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_online);



        MyApplication myApplication = new MyApplication().getInstance();
        int login=myApplication.getLoggedIn();
        final String premium=myApplication.getP();
        Log.v("Apply Online Login", Integer.toString(login));

        final EditText firstname = (EditText) findViewById(R.id.first);
        final EditText lastname = (EditText) findViewById(R.id.last);
        final EditText Mobile = (EditText) findViewById(R.id.mobile);
        final EditText Email = (EditText) findViewById(R.id.email);
        final EditText City = (EditText) findViewById(R.id.city);
        final EditText Address = (EditText) findViewById(R.id.address);
        final EditText Pincode = (EditText) findViewById(R.id.pincode);
        final EditText Age = (EditText) findViewById(R.id.age1);
        final Spinner Gender = (Spinner) findViewById(R.id.gender);
        final Spinner Plan = (Spinner) findViewById(R.id.plan);
        final CheckBox Receivenews = (CheckBox) findViewById(R.id.cb1);
        final CheckBox Acceptterms = (CheckBox) findViewById(R.id.cb2);
        final EditText Premium = (EditText) findViewById(R.id.premium);

        prgDialog = new ProgressDialog(this);
        // Set Cancelable as False
        prgDialog.setCancelable(false);



        userdetails = myApplication.getUserprofiledetails();

        if (userdetails != null) {
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

          //  Premium.setText(premium.toString());
            firstname.setText(first);
            lastname.setText(last);
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

            Button submit = (Button) findViewById(R.id.submit);
            Button addressbutton = (Button) findViewById(R.id.addproofbutton);
            Button idbutton = (Button) findViewById(R.id.idproofbutton);
            Button agebutton = (Button) findViewById(R.id.ageproofbutton);
            Button image = (Button) findViewById(R.id.uploadimagebutton);
            tv = (TextView) findViewById(R.id.addressproof);

            addressbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    RESULT_LOAD_IMG = 1;
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                }
            });

            idbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    RESULT_LOAD_IMG = 2;
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                }
            });

            agebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    RESULT_LOAD_IMG = 3;
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                }
            });

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    RESULT_LOAD_IMG = 4;
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String fname = firstname.getText().toString();
                    String lname = lastname.getText().toString();
                    String mobile = Mobile.getText().toString();
                    String email = Email.getText().toString();
                    String city = City.getText().toString();
                    String address = Address.getText().toString();
                    String pincode = Pincode.getText().toString();
                    String age = Age.getText().toString();
                    String gender = Gender.getSelectedItem().toString();
                    String plan = Plan.getSelectedItem().toString();
                    String premium = Premium.getText().toString();
                    String ifreceivenews = " ";
                    if (Receivenews.isChecked() == true) {
                        ifreceivenews = "true";
                    } else {
                        ifreceivenews = "false";
                    }
                    ApplyOnlineData applyOnlineData = new ApplyOnlineData(premium,fname, lname, mobile, email, city, address, pincode, age, gender,
                            plan, ifreceivenews);

                    if (Acceptterms.isChecked() == true) {
                        StoreApplyOnlineInDatabase(applyOnlineData);
                    } else {
                        Toast.makeText(getApplicationContext(), "you need to accept terms and conditions", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                //get the path of data
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgPath = cursor.getString(columnIndex);
                cursor.close();

                String fileNameSegments[] = imgPath.split("/");
                fileName = fileNameSegments[fileNameSegments.length - 1];
                // Put file name in Async Http Post Param which will used in Php web app
                params.put("filename", fileName);
                String s=userdetails.name;
                String name=s.replace(' ', '_');
                Log.v("replace",name);
                params.put("username", name);
                params.put("requestcode",RESULT_LOAD_IMG);

                uploadImage();

            } else if (requestCode == PICKFILE_RESULT_CODE && resultCode == RESULT_OK
                    && null != data){
                Uri FilePath = data.getData();
                filePath = FilePath.getPath();

                String fileNameSegments[] = filePath.split("/");
                fileName = fileNameSegments[fileNameSegments.length - 1];
                // Put file name in Async Http Post Param which will used in Php web app
                params.put("filename", fileName);
                String s=userdetails.name;
                String name=s.replace(' ', '_');
                Log.v("replace",name);
                params.put("username",name);
                uploadFile();
                Log.v("Finish","Done here");
            }
            else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void uploadImage() {
        // When Image is selected from Gallery
        if (imgPath != null && !imgPath.isEmpty()) {
            prgDialog.setMessage("Converting Image to Binary Data");
            prgDialog.show();
            // Convert image to String using Base64
            encodeImagetoString();
            // When Image is not selected from Gallery
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "You must select image from gallery before you try to upload",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void uploadFile() {
        // When Image is selected from Gallery
        if (filePath != null && !filePath.isEmpty()) {
            prgDialog.setMessage("Converting File to Binary Data");
            prgDialog.show();
            // Convert image to String using Base64
            encodeFiletoString();
            // When Image is not selected from Gallery
        }
    }

    public void encodeFiletoString() {
        new AsyncTask<Void, Void, String>() {

            protected void onPreExecute() {

            };

            @Override
            protected String doInBackground(Void... params) {
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Log.v("Filepath", filePath);
                bitmap = BitmapFactory.decodeFile(filePath,
                        options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                Log.v("1","Here");
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                Log.v("2","Here");
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String
                encodedString = android.util.Base64.encodeToString(byte_arr, 0);
                return "";
            }

            @Override
            protected void onPostExecute(String msg) {
                prgDialog.setMessage("Calling Upload");
                // Put converted Image string into Async Http Post param
                params.put("image", encodedString);
                // Trigger Image upload
                triggerImageUpload();
            }
        }.execute(null, null, null);
    }

    // AsyncTask - To convert Image to String
    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {

            protected void onPreExecute() {

            };

            @Override
            protected String doInBackground(Void... params) {
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Log.v("Imgpath", imgPath);
                bitmap = BitmapFactory.decodeFile(imgPath,
                        options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                Log.v("1","Here");
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                Log.v("2","Here");
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String
                encodedString = android.util.Base64.encodeToString(byte_arr, 0);
                return "";
            }

            @Override
            protected void onPostExecute(String msg) {
                prgDialog.setMessage("Calling Upload");
                // Put converted Image string into Async Http Post param
                params.put("image", encodedString);
                // Trigger Image upload
                triggerImageUpload();
            }
        }.execute(null, null, null);
    }

    public void triggerImageUpload() {
        makeHTTPCall();
    }

    // Make Http call to upload Image to Php server
    public void makeHTTPCall() {
        prgDialog.setMessage("Invoking Php");
        AsyncHttpClient client = new AsyncHttpClient();
        Log.v("params",params.toString());
        // Don't forget to change the IP address to your LAN address. Port no as well.
        client.post("http://www.shraddha.comlu.com/Upload_Image/uploadimage.php",
                params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        prgDialog.hide();
                        Toast.makeText(getApplicationContext(), "Success",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        prgDialog.hide();
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(),
                                    "Requested resource not found",
                                    Toast.LENGTH_LONG).show();
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(),
                                    "Something went wrong at server end",
                                    Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Error Occured \n Most Common Error: \n1. Device not connected to Internet\n2. Web App is not deployed in App server\n3. App server is not running\n HTTP Status code : "
                                            + statusCode, Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // Dismiss the progress bar when application is closed
        if (prgDialog != null) {
            prgDialog.dismiss();
        }
    }

    private void StoreApplyOnlineInDatabase(ApplyOnlineData applyOnlineData)
    {
        ServerRequests serverRequests =new ServerRequests(this);
        serverRequests.storeapplyonlinedetails(applyOnlineData, new ReturnApplyNowDetails() {
            @Override
            public void done(ApplyOnlineData returnapplynowdetails) {
                Toast.makeText(getApplicationContext(),"You have applied successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
	

