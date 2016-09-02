package com.example.thepolicykart2;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ServerRequests {
    String tabularpremium;

	ProgressDialog progressdialog;
	
	public static final int CONNECTION_TIMEOUT=1000*15;
	public static final String SERVER_ADDRESS="http://shraddha.comlu.com/";
	
	public ServerRequests(Context context)
	{
		progressdialog=new ProgressDialog(context);
		progressdialog.setCancelable(false);
		progressdialog.setTitle("Processing");
		progressdialog.setMessage("Please Wait");
	}
    public void setreminder(String email,ReturnApplyNowDetails returnApplyNowDetails)
    {
        progressdialog.show();
        new setreminderAsyncTask(email,returnApplyNowDetails).execute();
    }
    public void updatedetails(User user,GetUserCallBack getUserCallBack)
    {
        progressdialog.show();
        new updatedetailsAsyncTask(user,getUserCallBack).execute();
    }
	public void fetchtabularpremium(String term,String age, String plan,GetTabularPremium getTabularPremium)
	{
		progressdialog.show();
		new fetchtabularpremiumAsyncTask(term,age,plan,getTabularPremium).execute();
	}
	public void storeapplyonlinedetails(ApplyOnlineData applyOnlineData, ReturnApplyNowDetails returnApplyNowDetails)
	{
		progressdialog.show();
		new storeapplyonlinesetailsAsyncTask(applyOnlineData,returnApplyNowDetails).execute();
	}
	public void storequerynowdetails(QueryNowDetails queryNowDetails,GetQueryNowDetails getQueryNowDetails)
	{
		progressdialog.show();
		new storequerynowdetailsAsyncTask(queryNowDetails, getQueryNowDetails).execute();
	}
	public void storeuserdatainbackground(User user,GetUserCallBack usercallback)
	{
		progressdialog.show();
		new storeuserdataAsyncTask(user, usercallback).execute();
    }
	
	public void fetchuserdatainbackground(User user,GetUserCallBack usercallback)
	{
		progressdialog.show();
		new fetchuserdataAsyncTask(user, usercallback).execute();
	}

	public void fetchemail(User user,GetUserCallBack usercallback)
	{
		progressdialog.show();
		new fetchemailAsyncTask(user, usercallback).execute();
	}

	public void changepasswordindatabase(User user, GetUserCallBack usercallback)
	{
		progressdialog.show();
		new changepasswordindatabaseAsyncTask(user, usercallback).execute();
	}

    public void checknameindatabase(User user,GetUserCallBack userCallBack)
    {
        progressdialog.show();
        new checknameAsyncTask(user,userCallBack).execute();
    }

    public class updatedetailsAsyncTask extends AsyncTask<Void,Void,Void>
    {
        User user;
        GetUserCallBack userCallBack;

        public updatedetailsAsyncTask(User user, GetUserCallBack userCallBack)
        {
            this.user=user;
            this.userCallBack=userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();
            datatosend.add(new BasicNameValuePair("email", user.email));
            datatosend.add(new BasicNameValuePair("mobile", user.mobile));
            datatosend.add(new BasicNameValuePair("username", user.username));

            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS + "updatedetails.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(datatosend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            progressdialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(result);

        }
    }

	public class storeapplyonlinesetailsAsyncTask extends AsyncTask<Void,Void,Void>
	{
		ApplyOnlineData applyOnlineData;
		ReturnApplyNowDetails returnApplyNowDetails;

		public storeapplyonlinesetailsAsyncTask(ApplyOnlineData applyOnlineData,ReturnApplyNowDetails returnApplyNowDetails)
		{
			this.applyOnlineData=applyOnlineData;
			this.returnApplyNowDetails=returnApplyNowDetails;
		}

		@Override
		protected Void doInBackground(Void... params) {
			ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();
			datatosend.add(new BasicNameValuePair("premium", applyOnlineData.premium));
			datatosend.add(new BasicNameValuePair("firstname", applyOnlineData.firstname));
			datatosend.add(new BasicNameValuePair("lastname", applyOnlineData.lastname));
			datatosend.add(new BasicNameValuePair("mobile", applyOnlineData.mobile));
			datatosend.add(new BasicNameValuePair("email", applyOnlineData.email));
			datatosend.add(new BasicNameValuePair("city", applyOnlineData.city));
			datatosend.add(new BasicNameValuePair("address", applyOnlineData.address));
			datatosend.add(new BasicNameValuePair("pincode", applyOnlineData.pincode));
			datatosend.add(new BasicNameValuePair("age", applyOnlineData.age));
			datatosend.add(new BasicNameValuePair("gender", applyOnlineData.gender));
			datatosend.add(new BasicNameValuePair("plan", applyOnlineData.plan));
			datatosend.add(new BasicNameValuePair("ifreceivenews", applyOnlineData.ifreceivenews));


			HttpParams httpRequestParams=new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

			HttpClient client=new DefaultHttpClient(httpRequestParams);
			HttpPost post=new HttpPost(SERVER_ADDRESS + "applyonline.php");

			try{
				post.setEntity(new UrlEncodedFormEntity(datatosend));
				client.execute(post);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void aVoid) {

			progressdialog.dismiss();
			returnApplyNowDetails.done(null);
			super.onPostExecute(aVoid);
		}
	}

	public class changepasswordindatabaseAsyncTask extends AsyncTask<Void,Void,Void>
	{
        User user;
        GetUserCallBack userCallBack;

        public changepasswordindatabaseAsyncTask(User user, GetUserCallBack userCallBack)
        {
            this.user=user;
            this.userCallBack=userCallBack;
        }
		@Override
		protected Void doInBackground(Void... params) {

            ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();
			Log.v("email", user.username);
			Log.v("password", user.password);
            datatosend.add(new BasicNameValuePair("email", user.username));
            datatosend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS + "editpassword.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(datatosend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }

			return null;
		}

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            progressdialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(result);

        }
	}

    public class checknameAsyncTask extends AsyncTask<Void,Void,User>
    {
        User user;
        GetUserCallBack usercallback;

        public checknameAsyncTask(User user,GetUserCallBack usercallback) {
            this.user=user;
            this.usercallback=usercallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();

            datatosend.add(new BasicNameValuePair("username", user.username));
			datatosend.add(new BasicNameValuePair("email",user.email));

            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS + "checkname.php");

            User returneduser=null;
            try{
                post.setEntity(new UrlEncodedFormEntity(datatosend));
                HttpResponse httpresponse=client.execute(post);

                HttpEntity entity=httpresponse.getEntity();
                String result=EntityUtils.toString(entity);
                JSONObject jobject=new JSONObject(result);

                if(jobject.length()==0)
                {
                    returneduser=null;
                }else{
                    String name=jobject.getString("name");
                    String mobile=jobject.getString("mobile");
                    String email=jobject.getString("email");

                    returneduser=new User(name,user.username,user.password,mobile,email);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            // TODO Auto-generated method stub
            return returneduser;
        }
        protected void onPostExecute(User returnedUser) {
            // TODO Auto-generated method stub
            progressdialog.dismiss();
            usercallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
	public class storequerynowdetailsAsyncTask extends AsyncTask<Void,Void,Void>
	{
		QueryNowDetails queryNowDetails;
		GetQueryNowDetails getQueryNowDetails;

		public storequerynowdetailsAsyncTask(QueryNowDetails queryNowDetails,GetQueryNowDetails getQueryNowDetails)
		{
			this.queryNowDetails=queryNowDetails;
			this.getQueryNowDetails=getQueryNowDetails;
		}

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();
            datatosend.add(new BasicNameValuePair("firstname", queryNowDetails.firstname));
            datatosend.add(new BasicNameValuePair("lastname", queryNowDetails.lastname));
            datatosend.add(new BasicNameValuePair("mobile", queryNowDetails.mobile));
            datatosend.add(new BasicNameValuePair("email", queryNowDetails.email));
            datatosend.add(new BasicNameValuePair("policyn", queryNowDetails.policyn));
            datatosend.add(new BasicNameValuePair("comments", queryNowDetails.comments));
            datatosend.add(new BasicNameValuePair("ifreceivenews", queryNowDetails.ifreceivenews));


            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS + "querynow.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(datatosend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressdialog.dismiss();
            getQueryNowDetails.done(null);
            super.onPostExecute(aVoid);
        }
    }
	
	public class storeuserdataAsyncTask extends AsyncTask<Void,Void,Void>
	{
		User user;
		GetUserCallBack usercallback;
		
		public storeuserdataAsyncTask(User user,GetUserCallBack usercallback) {
			this.user=user;
			this.usercallback=usercallback;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();
			datatosend.add(new BasicNameValuePair("name", user.name));
			datatosend.add(new BasicNameValuePair("username", user.username));
			datatosend.add(new BasicNameValuePair("password", user.password));
			datatosend.add(new BasicNameValuePair("email", user.email));
			datatosend.add(new BasicNameValuePair("mobile", user.mobile));

			
			HttpParams httpRequestParams=new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client=new DefaultHttpClient(httpRequestParams);
			HttpPost post=new HttpPost(SERVER_ADDRESS + "register.php");
			    
			
			try{
				post.setEntity(new UrlEncodedFormEntity(datatosend));
				client.execute(post);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			progressdialog.dismiss();
			usercallback.done(null);
			super.onPostExecute(result);
		}
	}

    public class setreminderAsyncTask extends AsyncTask<Void,Void,ApplyOnlineData>
    {
        String email;
        ReturnApplyNowDetails returnApplyNowDetails;
        public setreminderAsyncTask(String email,ReturnApplyNowDetails returnApplyNowDetails)
        {
            this.email=email;
            this.returnApplyNowDetails=returnApplyNowDetails;
        }

        @Override
        protected ApplyOnlineData doInBackground(Void... params) {

            ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();

            datatosend.add(new BasicNameValuePair("email",email));
            Log.v("email",email);
            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS + "fetchapplyonline.php");
            ApplyOnlineData apd = null;//new ApplyOnlineData("null","null","null"
                    //,"null","null","null","null","null","null","null","null","null");
            Log.v("Reached","1");
            try{
                post.setEntity(new UrlEncodedFormEntity(datatosend));
                HttpResponse httpresponse=client.execute(post);
                Log.v("Reached","2");
                HttpEntity entity=httpresponse.getEntity();
                Log.v("Reached",entity.toString());
                String result=EntityUtils.toString(entity);
                Log.v("Result",result);
                JSONObject jobject=new JSONObject(result);
                String plan = null;
                if(jobject.length()==0)
                {
                    plan=null;
                    Log.v("value","null");
                }else{
                    plan=jobject.getString("selectplan");
                    String prem=jobject.getString("premium");
                    apd = new ApplyOnlineData(prem,null,null,null,null,null,null,null,null,null,plan,null);
                }
            }catch(Exception e){
                e.printStackTrace();
                Log.v("error","in exception");
            }
            return apd;
        }
        protected void onPostExecute(ApplyOnlineData apd) {
            // TODO Auto-generated method stub
            progressdialog.dismiss();
            returnApplyNowDetails.done(apd);
            super.onPostExecute(apd);
        }
    }

	public class fetchtabularpremiumAsyncTask extends AsyncTask<Void,Void,String>
	{
        String term,age,plan;
        GetTabularPremium getTabularPremium;
        public fetchtabularpremiumAsyncTask(String term,String age, String plan,GetTabularPremium getTabularPremium)
        {
            this.term=term;
            this.plan=plan;
            this.age=age;
            this.getTabularPremium=getTabularPremium;
        }
		@Override
		protected String doInBackground(Void... params) {
            ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();

			datatosend.add(new BasicNameValuePair("plan", plan));
            datatosend.add(new BasicNameValuePair("term", term));
            datatosend.add(new BasicNameValuePair("age", age));

            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS + "fetchtabularpremium.php");

            tabularpremium=null;

            try{
                post.setEntity(new UrlEncodedFormEntity(datatosend));
                HttpResponse httpresponse=client.execute(post);

                HttpEntity entity=httpresponse.getEntity();
                String result=EntityUtils.toString(entity);
			//	Log.v("result",result);
                JSONObject jobject=new JSONObject(result);

                if(jobject.length()==0)
                {
                    tabularpremium=null;
                    Log.v("value","null");
                }else{
                    tabularpremium=jobject.getString("tabularpremium");
					Log.v("value","-");
                    if(tabularpremium.equals("-")) {
                        tabularpremium = null;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                Log.v("error","in exception");
            }
			return tabularpremium;
		}
		protected void onPostExecute(String tabularpremium) {
			progressdialog.dismiss();
//            Log.v("Value in onPost",tabularpremium);
            getTabularPremium.done(tabularpremium);
            super.onPostExecute(tabularpremium);
		}
	}
	
	public class fetchuserdataAsyncTask extends AsyncTask<Void,Void,User>
	{
		User user;
		GetUserCallBack usercallback;
		
		public fetchuserdataAsyncTask(User user,GetUserCallBack usercallback) {
			this.user=user;
			this.usercallback=usercallback;
		}

		@Override
		protected User doInBackground(Void... params) {
			ArrayList<NameValuePair> datatosend=new ArrayList<NameValuePair>();
			
			datatosend.add(new BasicNameValuePair("username", user.username));
			datatosend.add(new BasicNameValuePair("password", user.password));
			
			
			HttpParams httpRequestParams=new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client=new DefaultHttpClient(httpRequestParams);
			HttpPost post=new HttpPost(SERVER_ADDRESS + "FetchUserData.php");
			
			User returneduser=null;
			try{
				post.setEntity(new UrlEncodedFormEntity(datatosend));
				HttpResponse httpresponse=client.execute(post);
				
				HttpEntity entity=httpresponse.getEntity();

				String result=EntityUtils.toString(entity);
                Log.v("Result",result);
				JSONObject jobject=new JSONObject(result);
				
				if(jobject.length()==0)
				{
					returneduser=null;
				}else{
					String name=jobject.getString("name");
					String mobile=jobject.getString("mobile");
					String email=jobject.getString("email");
					
					returneduser=new User(name,user.username,user.password,mobile,email);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return returneduser;
		}
		protected void onPostExecute(User returnedUser) {
			// TODO Auto-generated method stub
			progressdialog.dismiss();
			usercallback.done(returnedUser);
			super.onPostExecute(returnedUser);
		}
	}

	public class fetchemailAsyncTask extends AsyncTask<Void,Void,User> {
		User user;
		GetUserCallBack usercallback;

		public fetchemailAsyncTask(User user, GetUserCallBack usercallback) {
			this.user = user;
			this.usercallback = usercallback;
		}

		@Override
		protected User doInBackground(Void... params) {
			ArrayList<NameValuePair> datatosend = new ArrayList<NameValuePair>();

			datatosend.add(new BasicNameValuePair("email", user.email));
            Log.v("email",user.email);

			HttpParams httpRequestParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(SERVER_ADDRESS + "email.php");

			User returneduser = null;
			try {
				post.setEntity(new UrlEncodedFormEntity(datatosend));
				HttpResponse httpresponse = client.execute(post);

				HttpEntity entity = httpresponse.getEntity();
				String result = EntityUtils.toString(entity);
				JSONObject jobject = new JSONObject(result);

				if (jobject.length() == 0) {
					returneduser = null;
				} else {
					String name = jobject.getString("name");
					String mobile = jobject.getString("mobile");
					String username = jobject.getString("username");
					String password=jobject.getString("password");

					returneduser = new User(name,username, password, mobile, user.email);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return returneduser;
		}
        protected void onPostExecute(User returnedUser) {
            // TODO Auto-generated method stub
            progressdialog.dismiss();
            usercallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
	}
}
