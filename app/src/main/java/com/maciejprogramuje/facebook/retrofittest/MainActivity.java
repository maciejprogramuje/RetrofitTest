package com.maciejprogramuje.facebook.retrofittest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String username = "";
    String password = "";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://parseapi.back4app.com/");
        builder.client(httpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();

        VectorApi vectorApi = retrofit.create(VectorApi.class);

        Call<VectorResponse> call = vectorApi.getLogin(username, password);
        call.enqueue(new Callback<VectorResponse>() {
            @Override
            public void onResponse(@NonNull Call<VectorResponse> call, @NonNull Response<VectorResponse> response) {
                if (response.isSuccessful()) {
                    Log.w("UWAGA", "Response -> " + response);
                    //userStorage.save(response.body());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[]{});
                    try {
                        assert responseBody != null;
                        ErrorResponse errorResponse = converter.convert(responseBody);
                        Log.w("UWAGA", "ErrorResponse -> " + errorResponse);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<VectorResponse> call, @NonNull Throwable t) {
                Log.w("UWAGA", "onFailure -> " + t.getLocalizedMessage());
            }
        });
    }
}
