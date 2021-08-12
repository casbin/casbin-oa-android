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

    static void SetUserToken (FragmentActivity activity, String userToken) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(
                "user_token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_token", userToken);
        editor.apply();
    }
}
