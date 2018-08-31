package com.example.douglas.myfridgeapp.fridgeapi;

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
        fridgeapiService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(FridgeapiService.class);
    }
}
