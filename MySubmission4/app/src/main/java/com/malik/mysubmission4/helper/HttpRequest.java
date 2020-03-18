package com.malik.mysubmission4.helper;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.malik.mysubmission4.config.UrlAPI;
import com.malik.mysubmission4.interfaces.ResponseObject;

import org.json.JSONObject;

public class HttpRequest {
    public void getRequest(final Context context, String urlApi, String apiKey, String language, final ResponseObject responseObject){
        AndroidNetworking.get(urlApi)
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("language", language)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseObject.jsonResponse(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Periksa kembali koneksi anda", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
