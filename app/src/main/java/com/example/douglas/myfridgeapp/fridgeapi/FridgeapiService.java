package com.example.douglas.myfridgeapp.fridgeapi;

import com.example.douglas.myfridgeapp.domain.FridgeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FridgeapiService {

    @GET("fridge/")
    Call<List<FridgeItem>> getAllItems();
}
