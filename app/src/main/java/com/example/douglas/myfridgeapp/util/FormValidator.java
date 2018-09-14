package com.example.douglas.myfridgeapp.util;

import android.widget.TextView;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FormValidator {
    public static final String BLANK_FIELD_ERROR_MSG = "This field can not be blank";

    public static boolean isFormFilled (TextView... fields){
        Arrays.stream(fields)
                .filter(f -> f.getText().toString().trim().equalsIgnoreCase(""))
                .forEach(f -> f.setError(BLANK_FIELD_ERROR_MSG));

        return Arrays.stream(fields)
                .filter(f -> f.getText().toString().trim().equalsIgnoreCase(""))
                .collect(Collectors.toList()).isEmpty();
    }
}
