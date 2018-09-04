package com.example.douglas.myfridgeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.example.douglas.myfridgeapp.domain.ItemOptions;
import com.example.douglas.myfridgeapp.viewholder.FridgeItemViewHolder;
import com.example.douglas.myfridgeapp.viewholder.ItemOptionsViewHolder;

import java.util.List;

public class FridgeItemAdapter extends ExpandableRecyclerAdapter<FridgeItem, ItemOptions,
        FridgeItemViewHolder, ItemOptionsViewHolder> {

    private LayoutInflater mInflater;

    public FridgeItemAdapter(Context context, @NonNull List<FridgeItem> fridgeList) {
        super(fridgeList);
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public FridgeItemViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View fridgeItemView = mInflater.inflate(R.layout.content_main, parentViewGroup, false);
        return new FridgeItemViewHolder(fridgeItemView);
    }

    @Override
    public ItemOptionsViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View itemOptionsView = mInflater.inflate(R.layout.content_main, childViewGroup, false);
        return new ItemOptionsViewHolder(itemOptionsView);
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(@NonNull FridgeItemViewHolder fridgeItemViewHolder, int parentPosition, @NonNull FridgeItem fridgeItem) {
        fridgeItemViewHolder.bind(fridgeItem);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ItemOptionsViewHolder itemOptionsViewHolder, int parentPosition, int childPosition, @NonNull ItemOptions itemOptions) {
        itemOptionsViewHolder.bind(itemOptions);
    }
}
