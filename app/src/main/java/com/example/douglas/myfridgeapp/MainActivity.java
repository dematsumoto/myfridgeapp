package com.example.douglas.myfridgeapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity implements Callback<List<FridgeItem>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AddNewItemActivity.class);
            startActivity(intent,  ActivityOptions.makeBasic().toBundle());
        });

        ApiClient.getServices().getAllItems().enqueue(this);
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
            //findViewById(R.id.progress_loader).setVisibility(View.VISIBLE);
            ApiClient.getServices().getAllItems().enqueue(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(@NonNull Call<List<FridgeItem>> call, @NonNull Response<List<FridgeItem>> response) {
        if (!response.isSuccessful()){
            Toast.makeText(getApplicationContext(), "Server Unavailable, try again later", Toast.LENGTH_LONG).show();
        }
        else {
            //findViewById(R.id.progress_loader).setVisibility(View.GONE);
            RecyclerView mRecyclerView = findViewById(R.id.recycler_view);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            RecyclerView.Adapter mAdapter = new FridgeListAdapter(response.body());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<FridgeItem>> call, Throwable t) {
        t.printStackTrace();
    }
}


