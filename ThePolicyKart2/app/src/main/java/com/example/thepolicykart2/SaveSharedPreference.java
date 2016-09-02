package com.example.thepolicykart2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SaveSharedPreference
{
    static Boolean reminder = false;
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, User user)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("username", user.username);
        editor.putString("email", user.email);
        editor.putString("mobile", user.mobile);
        editor.putString("name", user.name);
        editor.putString("password", user.password);
        editor.commit();
    }

    public static void setcheckbox(Context ctx, Boolean r)
    {
        reminder = r;
        //SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        //editor.putBoolean("reminder", true);
        //editor.commit();
    }

    public static User getUserName(Context ctx)
    {
        User storedUser;
            String name = getSharedPreferences(ctx).getString("name", null);
            String username = getSharedPreferences(ctx).getString("username", null);
            String password = getSharedPreferences(ctx).getString("password", null);
            String email = getSharedPreferences(ctx).getString("email", "");
            String mobile = getSharedPreferences(ctx).getString("mobile", null);

            storedUser = new User(name, username, password, mobile, email);
//            Log.v("storeduser", storedUser.username);
        //}
        return storedUser;
    }

    public static Boolean getcheckbox(Context ctx)
    {
       // Boolean reminder = getSharedPreferences(ctx).getBoolean("reminder", false);
        return reminder;
    }

    public static void ClearUserData(Context ctx)
    {
        SharedPreferences.Editor editor=getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}