package com.englishwise.naaticclenglishwise.storage;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context mCtx;
    private static  final String SHARED_PREF_NAME="my_shared_preff";


    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized  SharedPrefManager getInstance(Context mCtx)
    {
        if( mInstance==null)
        {
            mInstance=new SharedPrefManager(mCtx);

        }
        return  mInstance;
    }
    public  void saveuser(User_login user_login)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("loginid",user_login.getLoginid());

        editor.putString("id",user_login.getId());
        editor.putString("fullname",user_login.getFullname());
        editor.putString("phone",user_login.getPhone());
        editor.putString("email",user_login.getEmail());
        editor.putString("profileImage",user_login.getProfileImage());
        editor.putString("language",user_login.getLanguage());
        editor.apply();

    }
    public static synchronized SharedPrefManager getInstans(Context context){
        if (mInstance==null){
            mInstance=new SharedPrefManager(context);
        }
        return mInstance;
    }
    public String getUserId(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("id",null);

    }

    public String getNumber(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone",null);

    }
    public String getfullname(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("fullname",null);

    }
    public String getemail(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("email",null);

    }
    public String getprofileImage(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("profileImage",null);

    }



    public String getlanguage(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("language",null);

    }

    public  boolean isLoggedin()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("loginid",-1 )!=-1;
    }

    public   void clear()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
