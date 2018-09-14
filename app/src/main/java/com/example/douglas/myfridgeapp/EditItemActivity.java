package com.example.douglas.myfridgeapp;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.util.DatePickerFragment;
import com.example.douglas.myfridgeapp.util.FormValidator;
import com.google.gson.Gson;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Gson gson = new Gson();
        FridgeItem item = gson.fromJson(getIntent().getStringExtra("item"), FridgeItem.class);

        setInitialData(item);
    }

    public void saveChanges(View view){
        EditText nameEditText = findViewById(R.id.edit_item_name);
        EditText startDateEditText = findViewById(R.id.edit_start_date_field);
        EditText expireDateEditText = findViewById(R.id.edit_exp_date_field);

        if (FormValidator.isFormFilled(nameEditText, startDateEditText, expireDateEditText)){
            Toast.makeText(getApplicationContext(), "[not implemented]", Toast.LENGTH_SHORT).show();
        }
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
