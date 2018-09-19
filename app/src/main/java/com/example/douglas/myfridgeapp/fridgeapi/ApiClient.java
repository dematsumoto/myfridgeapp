package com.example.douglas.myfridgeapp.fridgeapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://fridge-tracker-d.herokuapp.com";

    public static FridgeapiService fridgeapiService;

    public static FridgeapiService getServices(){
        if (fridgeapiService == null){
            buildService();
        }
        return fridgeapiService;
    }

    private static void buildService(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        fridgeapiService = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(FridgeapiService.class);
    }
}
