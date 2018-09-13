package com.example.douglas.myfridgeapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.adapter.FridgeListAdapter;
import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.fridgeapi.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AddNewItemActivity.class);
            startActivity(intent, ActivityOptions.makeBasic().toBundle());
        });

        getAllItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_refresh) {
            getAllItems();
            //findViewById(R.id.progress_loader).setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAllItems() {
        ApiClient.getServices().getAllItems().enqueue(new Callback<List<FridgeItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<FridgeItem>> call, @NonNull Response<List<FridgeItem>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Fridge is empty", Toast.LENGTH_LONG).show();
                        return;
                    }
                    //findViewById(R.id.progress_loader).setVisibility(View.GONE);
                    RecyclerView mRecyclerView = findViewById(R.id.recycler_view);

                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    mRecyclerView.setHasFixedSize(false);

                    // use a linear layout manager
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    RecyclerView.Adapter mAdapter = new FridgeListAdapter(response.body());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Server Unavailable, try again later", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FridgeItem>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void deleteItem(Context context, String id){
        ApiClient.getServices().deleteItem(id).enqueue(new Callback<FridgeItem>() {
            @Override
            public void onResponse(@NonNull Call<FridgeItem> call, @NonNull Response<FridgeItem> response) {
                if (response.isSuccessful()){
                    Log.v("delete_request", "delete successful! id: " + id);
                    Toast.makeText(context, "Item removed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FridgeItem> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Something went wrong while deleting", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


