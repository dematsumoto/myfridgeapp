package com.example.douglas.myfridgeapp.fridgeapi;

import com.example.douglas.myfridgeapp.domain.FridgeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FridgeapiService {

    @GET("fridge/")
    Call<List<FridgeItem>> getAllItems();

    @Headers("Content-Type: application/json")
    @POST("fridge/addItem")
    Call<FridgeItem> addNewItem(@Body FridgeItem fridgeItem);
}
