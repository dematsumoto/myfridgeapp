package com.example.douglas.myfridgeapp.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private DateTime setDate;
    EditText selectedView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String ADD_START_DATE_FIELD = getResources().getResourceEntryName(R.id.add_start_date_field);
        String START_DATE_FIELD = getResources().getResourceEntryName(R.id.edit_start_date_field);
        String EXP_DATE_FIELD = getResources().getResourceEntryName(R.id.edit_exp_date_field);

        assert bundle != null;
        if ((START_DATE_FIELD).equals(bundle.getString("DATE"))){
            selectedView = getActivity().findViewById(R.id.edit_start_date_field);
        }
        else if ((EXP_DATE_FIELD).equals(bundle.getString("DATE"))){
            selectedView = getActivity().findViewById(R.id.edit_exp_date_field);
        }
        else if ((ADD_START_DATE_FIELD).equals(bundle.getString("DATE"))){
            selectedView = getActivity().findViewById(R.id.add_start_date_field);
        }

        // Use the current date as the default date in the picker
        DateTime date = new DateTime();
        int year = date.getYear();
        int month = date.getMonthOfYear() - 1;
        int day = date.getDayOfMonth();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        setDate = new DateTime().withDate(year, month + 1, day);
        selectedView.setText(dateSetToString());
    }

    private String dateSetToString() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
        return setDate.toString(fmt);
    }

}
