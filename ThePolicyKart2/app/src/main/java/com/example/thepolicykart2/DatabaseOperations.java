package com.example.thepolicykart2;
import java.util.Currency;

import com.example.thepolicykart2.TableData.TableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperations extends SQLiteOpenHelper {

	public static final int databaseversion=1;
	public static final String Create_Query="CREATE TABLE IF NOT EXISTS "+TableInfo.TableName+"("+TableInfo.name+" TEXT,"+
	TableInfo.username+" TEXT,"+TableInfo.password+" TEXT,"+TableInfo.mobile+" TEXT,"+TableInfo.email+" TEXT);";
	
	public DatabaseOperations(Context context) {
		super(context, TableInfo.DatabaseName, null, databaseversion);
		Log.d("Database Operations", "Database created");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sdb) {
		
		sdb.execSQL(Create_Query);
		Log.d("Table Operations", "Table created");
	}
	
	public void putInformation(DatabaseOperations dop, String name,String username,String password,
			String mobile,String email)
	{
		SQLiteDatabase sd=dop.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(TableInfo.name, name);
		cv.put(TableInfo.username, username);
		cv.put(TableInfo.password, password);
		cv.put(TableInfo.mobile, mobile);
		cv.put(TableInfo.email, email);
		long k=sd.insert(TableInfo.TableName, null, cv);
		Log.d("Table Operations", "1 row inserted");
	}

	public Cursor getInformation(DatabaseOperations dop)
	{
		SQLiteDatabase sd=dop.getReadableDatabase();
		String[] columns={TableInfo.name,TableInfo.username,TableInfo.password,TableInfo.mobile,TableInfo.email};
		Cursor CR=sd.query(TableInfo.TableName, columns, null, null, null, null, null);
		return CR;
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
