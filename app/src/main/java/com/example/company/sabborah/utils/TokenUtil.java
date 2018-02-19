package com.example.company.sabborah.utils;

import android.util.Base64;
import android.util.Log;

import com.example.company.sabborah.responses.UserInformation;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mohamed Sayed on 2/9/2018.
 */

public class TokenUtil {
    private static TokenUtil mInstance;

    public static synchronized TokenUtil getReference() {
        if (mInstance == null) {
            mInstance = new TokenUtil();
        }
        return mInstance;
    }

    private TokenUtil() {
    }

    public UserInformation getUserId(String JWTEncoded) {
        try {
            String[] split = JWTEncoded.split("\\.");
            JSONObject jsonObject = new JSONObject(getJson(split[1]));
            String uid = jsonObject.get("uid") != null ? jsonObject.get("uid").toString() : "";
            String email = jsonObject.get("email") != null ? jsonObject.get("email").toString() : "";
            String isTutor = jsonObject.get("istutor") != null ? jsonObject.get("istutor").toString() : "0";
            return new UserInformation(uid, isTutor.equals("1") ? true : false, email);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        return null;
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
