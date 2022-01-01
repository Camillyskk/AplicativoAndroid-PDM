package com.example.projetopdm.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setID(Integer id) {
        prefs.edit().putInt("id", id).commit();
    }

    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public Integer getID() {
        Integer id = prefs.getInt("id", -1);
        return id;
    }
    public String getEmail(){
        String email = prefs.getString("email", "");
        return  email;
    }
}