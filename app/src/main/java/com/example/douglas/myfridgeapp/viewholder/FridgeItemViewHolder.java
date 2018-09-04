package com.example.douglas.myfridgeapp.viewholder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.FridgeItem;

public class FridgeItemViewHolder extends ParentViewHolder {

    private TextView mFridgeItemTextView;

    public FridgeItemViewHolder(View itemView) {
        super(itemView);
        mFridgeItemTextView = itemView.findViewById(R.id.exp_recycle_view);
    }

    public void bind(FridgeItem fridgeItem) {
        mFridgeItemTextView.setText(fridgeItem.getName());
    }
}
