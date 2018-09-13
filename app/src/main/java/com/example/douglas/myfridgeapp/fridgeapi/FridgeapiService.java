package com.example.douglas.myfridgeapp.fridgeapi;

import com.example.douglas.myfridgeapp.domain.FridgeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FridgeapiService {

    @GET("fridge/")
    Call<List<FridgeItem>> getAllItems();

    @Headers("Content-Type: application/json")
    @POST("fridge/addItem")
    Call<FridgeItem> addNewItem(@Body FridgeItem fridgeItem);

    @DELETE("fridge/item/{id}/delete")
    Call<FridgeItem> deleteItem(@Path("id") String id);
}
