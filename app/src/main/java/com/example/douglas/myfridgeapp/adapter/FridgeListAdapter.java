package com.example.douglas.myfridgeapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.FridgeItem;

import java.util.List;

public class FridgeListAdapter extends RecyclerView.Adapter<FridgeListAdapter.MyViewHolder> {
    private List<FridgeItem> mDataset;
    private int mExpandedPosition = -1;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView mCardView;
        TextView fridgeItem;
        TextView itemOptions;
        //public TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            fridgeItem = itemView.findViewById(R.id.fridge_item);
            itemOptions = itemView.findViewById(R.id.item_options);
            //mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FridgeListAdapter(List<FridgeItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FridgeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.fridgeItem.setText(mDataset.get(position).getName());
        holder.itemOptions.setText(mDataset.get(position).getValidUntilDate());

        // Expand/collapse items
        final boolean isExpanded = position==mExpandedPosition;
        holder.itemOptions.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(v -> {
            mExpandedPosition = isExpanded ? -1:position;
            notifyItemChanged(position);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
