package com.casbin.casdoor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CasdoorBackend {
    static void SetSession(FragmentActivity activity, String cookie) {
        if (cookie!=null && !cookie.equals("") && cookie.contains("=")) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("web", Context.MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("session", cookie.split("=")[1]);
            editor.apply();
        }
    }

    static void SetCookie(FragmentActivity activity, String cookie) {
        if (cookie != null && !cookie.equals("")) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("web", Context.MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookie", cookie);
            editor.apply();
        }
    }

    static String GetSession(FragmentActivity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("web", Context.MODE_PRIVATE);
        return sharedPreferences.getString("session", "");
    }

    static String GetCookie(FragmentActivity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("web", Context.MODE_PRIVATE);
        return sharedPreferences.getString("cookie", "");
    }

    static void SetAccessToken(FragmentActivity activity, String code) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", CasdoorConfig.TOKENGRANTTYPE)
                .addFormDataPart("client_id", CasdoorConfig.GetConfig(activity).ClientID)
                .addFormDataPart("client_secret", CasdoorConfig.GetConfig(activity).ClientSecret)
                .addFormDataPart("code", code)
                .build();

        Request request = new Request.Builder()
                .url(CasdoorConfig.GetConfig(activity).Endpoint + "/api/login/oauth/access_token")
                .post(requestBody)
                .build();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    assert response.body() != null;
                    String res = response.body().string();
                    JSONObject jsonObject = new JSONObject(res);

                    CasdoorUserToken.SetUserToken(activity, jsonObject.get("access_token").toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    static void SetUsers(FragmentActivity activity) throws InterruptedException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
//                .addHeader("cookie", GetCookie(activity))
                .addHeader("Cookie", GetCookie(activity))
                .url(CasdoorConfig.GetConfig(activity).Endpoint
                        + "/api/get-users?owner="
                        + CasdoorConfig.GetConfig(activity).OrganizationName
                        + "&clientId="
                        + CasdoorConfig.GetConfig(activity).ClientID
                        + "&clientSecret="
                        + CasdoorConfig.GetConfig(activity).ClientSecret
                )
                .get()
                .build();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    assert response.body() != null;
                    String res = response.body().string();
                    JSONArray jsonArray = new JSONArray(res);

                    // set users
                    CasdoorUser.SetUsers(activity, jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    static void SetUser(FragmentActivity activity, String name) throws InterruptedException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(CasdoorConfig.GetConfig(activity).Endpoint
                        + "/api/get-user?owner="
                        + CasdoorConfig.GetConfig(activity).OrganizationName
                        + "&id="
                        + CasdoorConfig.GetConfig(activity).OrganizationName + "/" + name
                        + "&clientId="
                        + CasdoorConfig.GetConfig(activity).ClientID
                        + "&clientSecret="
                        + CasdoorConfig.GetConfig(activity).ClientSecret
                )
                .get()
                .build();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    assert response.body() != null;
                    String res = response.body().string();
                    JSONObject jsonObject = new JSONObject(res);

                    // set user
                    CasdoorUser.SetUser(activity, jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }
}
