package com.example.thepolicykart2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainFragment extends Fragment {

    private Context context;
    public String name;
    private CallbackManager callbackmanager;
    private AccessTokenTracker tracker;
    private ProfileTracker profileTracker;
    private FacebookCallback<LoginResult> callback=new FacebookCallback<LoginResult>() {
        private ProfileTracker mProfileTracker;
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.v("1","onsuccess");
            AccessToken accesstoken=loginResult.getAccessToken();
            if(Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        // profile2 is the new profile
                        Log.v("facebook - profile", profile2.getFirstName());
                        gotologinpage(profile2);
                        mProfileTracker.stopTracking();
                    }
                };
                mProfileTracker.startTracking();
            }
            else {
                Profile profile = Profile.getCurrentProfile();
                Log.v("facebook - profile", profile.getFirstName());
                gotologinpage(profile);
            }

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    public MainFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackmanager = CallbackManager.Factory.create();
       tracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken old, AccessToken newtoken) {

                Log.v("1", "onCurrentacccess token changed");
                Intent i=new Intent(getActivity(),Login.class);
                if(name.equals("UserProfileDetails"))
                {
                    SaveSharedPreference.ClearUserData(context);
                    startActivity(i);
                }
            }
        };

        tracker.startTracking();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     //   RelativeLayout relativeLayout=(RelativeLayout) inflater.inflate(R.layout.fragment_main,container,false);
       // TextView text=(TextView) relativeLayout.findViewById(R.id.text);
        //LoginButton loginButton=(LoginButton) relativeLayout.findViewById(R.id.loginwithfacebook);
        Log.v("1", "onCreateView");
        name = ((framelayout)getActivity()).getValue();
        context=container.getContext();
        return inflater.inflate(R.layout.fragment_main, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginbutton=(LoginButton) view.findViewById(R.id.loginwithfacebook);
        loginbutton.setFragment(this);
        Log.v("1", "onViewCreated");
        loginbutton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        loginbutton.registerCallback(callbackmanager, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("1", "onActivityResult");
        callbackmanager.onActivityResult(requestCode, resultCode, data);
    }

   @Override
    public void onResume() {
        super.onResume();
       Profile profile=Profile.getCurrentProfile();
       Log.v("1", "onresume");
        if(name.equals("Login")) {
            gotologinpage(profile);
        }
   }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("1", "onstop");
        tracker.stopTracking();

    }

    private void gotologinpage(Profile profile)
    {
        Log.v("1", "gotologinpage");
        if(profile!=null)
        {
            User user=new User(profile.getName(),"Logged as Facebook User","Null","Null","Null");
            SaveSharedPreference.setUserName(context,user);
            MyApplication myApplication=new MyApplication().getInstance();
            myApplication.setUserprofiledetails(user);
            myApplication.setLoggedIn(2);
            User user1=new User(2);
            Intent i=new Intent(getActivity(),Home.class);
            i.putExtra("User Details", user);
            i.putExtra("LoggedIn",user1);
            startActivity(i);
        }
    }
}
