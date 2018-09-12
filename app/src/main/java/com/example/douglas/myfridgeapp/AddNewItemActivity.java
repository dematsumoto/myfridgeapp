package com.example.douglas.myfridgeapp;


import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.fridgeapi.ApiClient;
import com.example.douglas.myfridgeapp.util.DatePickerFragment;
import com.example.douglas.myfridgeapp.util.DateTimeConverter;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewItemActivity extends AppCompatActivity implements Callback<FridgeItem> {

    public static final String ACTIVE = "true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void submitNewItem(View v){
        //Toast.makeText(getApplicationContext(), "Adding item...[not implemented]", Toast.LENGTH_SHORT).show();
        EditText nameEditText = findViewById(R.id.item_name);
        String itemName = nameEditText.getText().toString();

        EditText startDateEditText = findViewById(R.id.start_date_field);
        String startDate = startDateEditText.getText().toString();

        EditText validForEditText = findViewById(R.id.valid_days_number_field);
        String validForDays = validForEditText.getText().toString();

        String validUntilDate = DateTimeConverter.addDaysToDate(startDate, Integer.parseInt(validForDays));

        FridgeItem fridgeItem = new FridgeItem(itemName, startDate, validUntilDate, ACTIVE);
        ApiClient.getServices().addNewItem(fridgeItem).enqueue(this);
    }

    @Override
    public void onResponse(Call<FridgeItem> call, Response<FridgeItem> response) {
        int statusCode = response.code();
        if (response.isSuccessful()){
            Toast.makeText(getApplicationContext(), "Item successfully added!", Toast.LENGTH_SHORT).show();
        }
        else if(statusCode == 400){
            Toast.makeText(getApplicationContext(), "[Bad Request]", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<FridgeItem> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Something went wrong with the request", Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
}