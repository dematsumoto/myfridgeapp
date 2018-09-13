package com.example.douglas.myfridgeapp;


import android.support.annotation.NonNull;
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


public class AddNewItemActivity extends AppCompatActivity {

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

        EditText nameEditText = findViewById(R.id.item_name);
        EditText startDateEditText = findViewById(R.id.start_date_field);
        EditText validForEditText = findViewById(R.id.valid_days_number_field);

        String itemName = nameEditText.getText().toString();
        String startDate = startDateEditText.getText().toString();
        String validForDays = validForEditText.getText().toString();

        if (isFormValid(nameEditText, startDateEditText, validForEditText)){
            String validUntilDate = DateTimeConverter.addDaysToDate(startDate, Integer.parseInt(validForDays));
            FridgeItem fridgeItem = new FridgeItem(itemName, startDate, validUntilDate, ACTIVE);
            addNewItem(fridgeItem);
        }

    }

    private boolean isFormValid(EditText nameEditText, EditText startDateEditText, EditText validForEditText) {
        if (nameEditText.getText().toString().trim().equalsIgnoreCase("")) {
            nameEditText.setError("This field can not be blank");
            return false;
        }

        if (startDateEditText.getText().toString().trim().equalsIgnoreCase("")) {
            startDateEditText.setError("This field can not be blank");
            return  false;
        }
        else {
            startDateEditText.setError(null);
        }

        if (validForEditText.getText().toString().trim().equalsIgnoreCase("")) {
            validForEditText.setError("This field can not be blank");
            return false;
        }

        return true;
    }

    public void addNewItem(FridgeItem fridgeItem){
        ApiClient.getServices().addNewItem(fridgeItem).enqueue(new Callback<FridgeItem>() {
            @Override
            public void onResponse(@NonNull Call<FridgeItem> call, @NonNull Response<FridgeItem> response) {
                int statusCode = response.code();
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Item successfully added!", Toast.LENGTH_SHORT).show();
                    cleanFields();
                }
                else if(statusCode == 400){
                    Toast.makeText(getApplicationContext(), "[Bad Request]", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FridgeItem> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong with the request", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    private void cleanFields() {
        EditText nameEditText = findViewById(R.id.item_name);
        EditText startDateEditText = findViewById(R.id.start_date_field);
        EditText validForEditText = findViewById(R.id.valid_days_number_field);

        nameEditText.getText().clear();
        startDateEditText.getText().clear();
        validForEditText.getText().clear();
    }
}