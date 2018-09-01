package com.example.douglas.myfridgeapp.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.douglas.myfridgeapp.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private Calendar c;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        c.set(year, month, day);
        EditText editText = getActivity().findViewById(R.id.start_date_field);
        editText.setText(dateSetToString());
    }

    private String dateSetToString() {
        String myFormat = DATE_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        return sdf.format(c.getTime());
    }

}
