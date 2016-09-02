package com.example.thepolicykart2;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

	public static final String SP_NAME="userdetails";
	SharedPreferences userLocalDatabase;
	
	public UserLocalStore(Context context)
	{
		userLocalDatabase=context.getSharedPreferences(SP_NAME, 0);
	}
	
	public void StoreUserData(User user)
	{
		SharedPreferences.Editor speditor=userLocalDatabase.edit();
		speditor.putString("name", user.name);
		speditor.putString("username", user.username);
		speditor.putString("password", user.password);
		speditor.putString("mobile", user.mobile);
		speditor.putString("email", user.email);
		speditor.commit();
	}
	
	public User getLoggedInUser()
	{
		String name=userLocalDatabase.getString("name", "");
		String username=userLocalDatabase.getString("username", "");
		String password=userLocalDatabase.getString("password", "");
		String email=userLocalDatabase.getString("email", "");
		String mobile=userLocalDatabase.getString("mobile", "");
		
		User storedUser=new User(name,username,password,mobile,email);
		return storedUser;
	}
	
	public void setuserLoggedIn(Boolean LoggedIn)
	{
		SharedPreferences.Editor speditor=userLocalDatabase.edit();
		speditor.putBoolean("loggedin", LoggedIn);
		speditor.commit();
	}

	public boolean getuserLoggedIn(){
		if(userLocalDatabase.getBoolean("loggedin",false)==true){
			return true;
		}
		else {
			return false;
		}


	}
	
	public void ClearUserData()
	{
		SharedPreferences.Editor speditor=userLocalDatabase.edit();
		speditor.clear();
		speditor.commit();
	}

}
