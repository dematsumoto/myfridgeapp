package com.example.douglas.myfridgeapp.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.fridgeapi.ApiClient;
import com.example.douglas.myfridgeapp.fragment.DatePickerFragment;
import com.example.douglas.myfridgeapp.util.FormValidator;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditItemActivity extends AppCompatActivity {

    public static final String ACTIVE = "true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Gson gson = new Gson();
        FridgeItem item = gson.fromJson(getIntent().getStringExtra("item"), FridgeItem.class);

        setInitialData(item);
    }

    public void saveChanges(View view){
        Gson gson = new Gson();
        FridgeItem item = gson.fromJson(getIntent().getStringExtra("item"), FridgeItem.class);

        EditText nameEditText = findViewById(R.id.edit_item_name);
        EditText startDateEditText = findViewById(R.id.edit_start_date_field);
        EditText expireDateEditText = findViewById(R.id.edit_exp_date_field);

        String itemId = item.getId();
        String itemName = nameEditText.getText().toString();
        String startDate = startDateEditText.getText().toString();
        String validUntilDate = expireDateEditText.getText().toString();

        if (FormValidator.isFormFilled(nameEditText, startDateEditText, expireDateEditText)){
            FridgeItem fridgeItem = new FridgeItem(itemId, itemName, startDate, validUntilDate, ACTIVE);
            updateItem(fridgeItem);
        }
    }

    private void updateItem(FridgeItem fridgeItem) {
        ApiClient.getServices().updateItem(fridgeItem).enqueue(new Callback<FridgeItem>() {
            @Override
            public void onResponse(@NonNull Call<FridgeItem> call, @NonNull Response<FridgeItem> response) {
                int statusCode = response.code();
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Item successfully updated!", Toast.LENGTH_SHORT).show();
                    finish();
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

    private void setInitialData(FridgeItem item) {
        TextView itemNameTextView = findViewById(R.id.edit_item_name);
        itemNameTextView.setText(item.getName());

        TextView itemStartDateTextView = findViewById(R.id.edit_start_date_field);
        itemStartDateTextView.setText(item.getStartDate());

        TextView itemExpireDateTextView = findViewById(R.id.edit_exp_date_field);
        itemExpireDateTextView.setText(item.getValidUntilDate());
    }

    public void showStartDatePickerDialog(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("DATE", getResources().getResourceEntryName(v.getId()));
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
