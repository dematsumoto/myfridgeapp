package com.example.douglas.myfridgeapp.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.adapter.FridgeListAdapter;
import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.fragment.HowToUseDialogFragment;
import com.example.douglas.myfridgeapp.fridgeapi.ApiClient;

import java.net.SocketTimeoutException;
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

        if (id == R.id.how_to_action){
            showHelpDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllItems();
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
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(getApplicationContext(), "Server Timeout..Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void deleteItem(Context context, String id){
        ApiClient.getServices().deleteItem(id).enqueue(new Callback<FridgeItem>() {
            @Override
            public void onResponse(@NonNull Call<FridgeItem> call, @NonNull Response<FridgeItem> response) {
                if (response.isSuccessful()){
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

    public void showHelpDialog(){
        DialogFragment helpDialog = new HowToUseDialogFragment();
        helpDialog.show(getSupportFragmentManager(), "how to");
    }
}


