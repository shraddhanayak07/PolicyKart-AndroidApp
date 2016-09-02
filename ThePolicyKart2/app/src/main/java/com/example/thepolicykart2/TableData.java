package com.example.thepolicykart2;

import android.provider.BaseColumns;

public class TableData {

	public TableData()
	{
		
	}
	
	public static abstract class TableInfo implements BaseColumns
	{
		public static final String name="name";
		public static final String username="username";
		public static final String password="password";
		public static final String mobile="mobile";
		public static final String email="email";
		
		public static final String DatabaseName="userinfo";
		public static final String TableName="reginfo";
	}
}
