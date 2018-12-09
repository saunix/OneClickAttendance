package com.example.anamika.androidqrcodescanner;

import android.content.Context;
import android.content.SharedPreferences;
public class CustomSharedPreference {
    private SharedPreferences sharedPref;
    public CustomSharedPreference(Context context) {
        sharedPref = context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
    }
    public SharedPreferences getInstanceOfSharedPreference(){
        return sharedPref;
    }
    //Save user information
    public void setUserData(String userData){
        sharedPref.edit().putString("USER", userData).apply();
    }
    public String getUserData(){
        return sharedPref.getString("USER", "");
    }
    public void setFacutyData(String userData){
        sharedPref.edit().putString("Faculty", userData).apply();
    }
    public String getFacultyData(){
        return sharedPref.getString("Faculty", "");
    }
}