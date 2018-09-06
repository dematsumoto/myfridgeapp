package com.example.douglas.myfridgeapp;


import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.douglas.myfridgeapp.util.DatePickerFragment;

import java.util.Objects;


public class AddNewItemActivity extends AppCompatActivity {

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

    public void showSubmitionToast(View v){
        Toast.makeText(getApplicationContext(), "Adding item...[not implemented]", Toast.LENGTH_SHORT).show();
    }

}