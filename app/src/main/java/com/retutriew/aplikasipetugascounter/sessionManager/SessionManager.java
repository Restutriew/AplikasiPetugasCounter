package com.retutriew.aplikasipetugascounter.sessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.retutriew.aplikasipetugascounter.main.mainActivity;
import com.retutriew.aplikasipetugascounter.login.login;
import com.retutriew.aplikasipetugascounter.pilihan.pilihan;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE=0;

    private  static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String USERNAME = "USERNAME";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor=sharedPreferences.edit();

    }

    public  void createSession(String username, String name){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(USERNAME,username);
        editor.apply();
    }

    public  boolean isLoggin (){
     return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggin()){
            Intent i = new Intent(context, login.class);
            context.startActivity(i);
            ((pilihan)context).finish();
        }
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String , String > user = new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME,null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, login.class);
        context.startActivity(i);
        ((pilihan)context).finish();
    }
}
