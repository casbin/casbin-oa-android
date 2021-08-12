package com.casbin.casdoor;

import androidx.fragment.app.FragmentActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CasdoorBackend {
    public static void SetAccessToken(FragmentActivity activity, String code) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "authorization_code")
                .addFormDataPart("client_id", "0ba528121ea87b3eb54d")
                .addFormDataPart("client_secret", "04f4ca22101529a3503d5a653a877b4e8403edf0")
                .addFormDataPart("code", code)
                .build();

        Request request = new Request.Builder()
                .url("https://door.casbin.com/api/login/oauth/access_token")
                .post(requestBody)
                .build();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    assert response.body() != null;
                    String res = response.body().string();
                    JSONObject jsonObject = new JSONObject(res);
                    CasdoorUserToken.SetUserToken(activity, jsonObject.getString("access_token"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
