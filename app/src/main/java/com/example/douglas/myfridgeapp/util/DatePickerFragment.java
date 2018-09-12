package com.example.douglas.myfridgeapp.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.douglas.myfridgeapp.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private DateTime date;
    private DateTime setDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        date = new DateTime();
        int year = date.getYear();
        int month = date.getMonthOfYear();
        int day = date.getDayOfMonth();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        setDate = new DateTime().withDate(year, month, day);
        EditText editText = getActivity().findViewById(R.id.start_date_field);
        editText.setText(dateSetToString());
    }

    private String dateSetToString() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
        return setDate.toString(fmt);
    }

}
