package com.example.douglas.myfridgeapp.activity;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.fridgeapi.ApiClient;
import com.example.douglas.myfridgeapp.fragment.DatePickerFragment;
import com.example.douglas.myfridgeapp.util.DateTimeConverter;
import com.example.douglas.myfridgeapp.util.FormValidator;

import java.util.Objects;
import java.util.stream.Stream;

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
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(getBaseContext(), R.color.action_bar_color)));
    }

    public void showDatePickerDialog(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("DATE", getResources().getResourceEntryName(v.getId()));
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "datePicker");

        hideKeyboard(this);
    }

    public void submitNewItem(View v){
        EditText nameEditText = findViewById(R.id.add_item_name_field);
        EditText startDateEditText = findViewById(R.id.add_start_date_field);
        EditText validForEditText = findViewById(R.id.valid_days_number_field);

        String itemName = nameEditText.getText().toString();
        String startDate = startDateEditText.getText().toString();
        String validForDays = validForEditText.getText().toString();

        if (FormValidator.isFormFilled(nameEditText, startDateEditText, validForEditText)){
            String validUntilDate = DateTimeConverter.addDaysToDate(startDate, Integer.parseInt(validForDays));
            FridgeItem fridgeItem = new FridgeItem(itemName, startDate, validUntilDate, ACTIVE);
            addNewItem(fridgeItem);
        }
    }

    public void addNewItem(FridgeItem fridgeItem){
        ApiClient.getServices().addNewItem(fridgeItem).enqueue(new Callback<FridgeItem>() {
            @Override
            public void onResponse(@NonNull Call<FridgeItem> call, @NonNull Response<FridgeItem> response) {
                int statusCode = response.code();
                if (response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Item successfully added!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0 ,128);
                    toast.show();
                    cleanFields();
                    findViewById(R.id.return_to_fridge_btn).setVisibility(View.VISIBLE);
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
        EditText nameEditText = findViewById(R.id.add_item_name_field);
        EditText startDateEditText = findViewById(R.id.add_start_date_field);
        EditText validForEditText = findViewById(R.id.valid_days_number_field);

        Stream.of(nameEditText, startDateEditText, validForEditText)
                .forEach(f -> f.getText().clear());
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void returnToFridge(View view){
        finish();
    }
}