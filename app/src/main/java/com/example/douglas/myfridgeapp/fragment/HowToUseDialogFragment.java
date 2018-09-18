package com.example.douglas.myfridgeapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.douglas.myfridgeapp.R;

public class HowToUseDialogFragment extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.how_to_use_description)
                .setTitle(R.string.how_to_title)
                .setPositiveButton(R.string.got_it, (dialog, id) -> {
                });
        return builder.create();
    }
}
