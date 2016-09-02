package com.example.thepolicykart2;

import android.R.string;

import java.io.Serializable;

public class User implements Serializable {

	String name,username,password,mobile,email;
	int LoggedIn;

	public User(int LoggedIn)
	{
		this.LoggedIn=LoggedIn;
	}
	
	public User(String name,String username, String password, String email, String mobile)
	{
		this.name=name;
		this.username=username;
		this.password=password;
		this.mobile =mobile;
		this.email=email;
	}
	
	public User(String username, String password)
	{
		this.username=username;
		this.password=password;
	}

	public User(String email)
	{
		this.email=email;
	}

}
