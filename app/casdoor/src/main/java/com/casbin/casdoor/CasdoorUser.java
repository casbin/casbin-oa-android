package com.casbin.casdoor;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class CasdoorUser {
    public String getOwner() {
        return Owner;
    }

    public String getName() {
        return Name;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public String getUpdatedTime() {
        return UpdatedTime;
    }

    public String getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public String getPassword() {
        return Password;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String[] getAddress() {
        return Address;
    }

    public String getAffiliation() {
        return Affiliation;
    }

    public String getTag() {
        return Tag;
    }

    public String getLanguage() {
        return Language;
    }

    public int getScore() {
        return Score;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public boolean isGlobalAdmin() {
        return IsGlobalAdmin;
    }

    public boolean isForbidden() {
        return IsForbidden;
    }

    public String getSignupApplication() {
        return SignupApplication;
    }

    public String getHash() {
        return Hash;
    }

    public String getPreHash() {
        return PreHash;
    }

    public String getGithub() {
        return Github;
    }

    public String getGoogle() {
        return Google;
    }

    public String getQQ() {
        return QQ;
    }

    public String getWeChat() {
        return WeChat;
    }

    public String getFacebook() {
        return Facebook;
    }

    public String getDingTalk() {
        return DingTalk;
    }

    public String getWeibo() {
        return Weibo;
    }

    public String getGitee() {
        return Gitee;
    }

    public String getLinkedIn() {
        return LinkedIn;
    }

    public Map<String, String> getProperties() {
        return Properties;
    }

    String Owner;

    String Name;
    String CreatedTime;
    String UpdatedTime;

    String Id;
    String Type;
    String Password;
    String DisplayName;
    String Avatar;
    String Email;
    String Phone;
    String[] Address;
    String Affiliation;
    String Tag;
    String Language;
    int Score;
    boolean IsAdmin;
    boolean IsGlobalAdmin;
    boolean IsForbidden;
    String SignupApplication;
    String Hash;
    String PreHash;

    String Github;
    String Google;
    String QQ;
    String WeChat;
    String Facebook;
    String DingTalk;
    String Weibo;
    String Gitee;
    String LinkedIn;

    Map<String, String> Properties;

    public CasdoorUser(String owner, String name, String displayName, String email, String phone, boolean isAdmin, boolean isGlobalAdmin, boolean isForbidden) {
        Owner = owner;
        Name = name;
        DisplayName = displayName;
        Email = email;
        Phone = phone;
        IsAdmin = isAdmin;
        IsGlobalAdmin = isGlobalAdmin;
        IsForbidden = isForbidden;
    }

    // set users through the type of JSONObject
    static void SetUsers(FragmentActivity activity, JSONArray userJsonArray) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("users", userJsonArray.toString());
        editor.apply();
    }


    public static ArrayList<CasdoorUser> GetUsers(FragmentActivity activity) throws JSONException, InterruptedException {
        CasdoorBackend.SetUsers(activity);
        if (activity != null) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences(
                    "user", Context.MODE_PRIVATE
            );
            String userJsonArrayString = sharedPreferences.getString("users", "");
            if (!userJsonArrayString.equals("")) {
                JSONArray jsonArray = new JSONArray(userJsonArrayString);
                ArrayList<CasdoorUser> arrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    arrayList.add(new CasdoorUser(
                            jsonObject.getString("owner"),
                            jsonObject.getString("name"),
                            jsonObject.getString("displayName"),
                            jsonObject.getString("email"),
                            jsonObject.getString("phone"),
                            jsonObject.getBoolean("isAdmin"),
                            jsonObject.getBoolean("isGlobalAdmin"),
                            jsonObject.getBoolean("isForbidden")

                    ));
                }
                return arrayList;
            }
            return null;
        } else {
            return null;
        }
    }


    // set user through the type of JSONObject
    static void SetUser(FragmentActivity activity, JSONObject userJsonObj) throws JSONException {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user/" + userJsonObj.getString("name"), userJsonObj.toString());
        editor.apply();
    }
}