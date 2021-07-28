package com.casbin.casdoor;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

public class CasdoorUserToken {
    public static String GetUserToken(FragmentActivity activity) {
        if (activity != null) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences(
                    "user_token", Context.MODE_PRIVATE
            );
            return sharedPreferences.getString("user_token", "");
        } else {
            return "";
        }
    }
}
