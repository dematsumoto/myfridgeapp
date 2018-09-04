package com.example.douglas.myfridgeapp.viewholder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.ItemOptions;

public class ItemOptionsViewHolder extends ChildViewHolder {
    private TextView mIngredientTextView;

    public ItemOptionsViewHolder(View itemView) {
        super(itemView);
        mIngredientTextView = itemView.findViewById(R.id.exp_recycle_view);
    }

    public void bind(ItemOptions itemOptions) {
        mIngredientTextView.setText(itemOptions.getValidUntilDate());
    }
}
