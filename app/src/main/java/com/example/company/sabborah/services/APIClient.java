package com.example.company.sabborah.services;

import android.app.Application;
import android.content.Context;

import com.example.company.sabborah.BuildConfig;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.utils.MySharedPreferences;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Sayed on 1/4/2018.
 */

public class APIClient extends Application {
    public static final String connectionLost = "No internet connection!";
    private static Retrofit retrofit = null;
    private static APIClient mInstance;
    static File cacheFile;

    @Override
    public void onCreate() {
        super.onCreate();
        cacheFile = getApplicationContext().getCacheDir();
    }

    private APIClient() {
    }

    public static synchronized APIClient getInstance() {
        if (mInstance == null) {
            mInstance = new APIClient();
        }
        return mInstance;
    }

    public Retrofit getClient() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .build();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public CommonResponse getResponse(Response response, Context context) {
        Gson converter = new Gson();
        CommonResponse commonResponse = new CommonResponse();
        if (response.isSuccessful()) {
            commonResponse = (CommonResponse) response.body();
            MySharedPreferences.getReference(context).setToken(commonResponse.getResult().getToken());
        } else {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                List<String> errors = converter.fromJson(jObjError.getJSONArray("errors").toString(), ArrayList.class);
                commonResponse.setErrors(errors);
                commonResponse.setSuccess(response.isSuccessful());
                commonResponse.setStatusCode(response.code());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return commonResponse;
    }

    public CommonResponse getErrorBody(Response<?> response) {
        Gson converter = new Gson();
        CommonResponse commonResponse = new CommonResponse();
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            List<String> errors = new ArrayList<>();
            if (jObjError.opt("message") != null) {
                String error = jObjError.getString("message");
                errors.add(error);
            } else {
                errors = converter.fromJson(jObjError.getJSONArray("errors").toString(), ArrayList.class);
            }
            commonResponse.setErrors(errors);
            commonResponse.setSuccess(response.isSuccessful());
            commonResponse.setStatusCode(response.code());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commonResponse;
    }
}
