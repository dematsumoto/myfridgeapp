package com.example.douglas.myfridgeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.douglas.myfridgeapp.activity.EditItemActivity;
import com.example.douglas.myfridgeapp.activity.MainActivity;
import com.example.douglas.myfridgeapp.R;
import com.example.douglas.myfridgeapp.domain.FridgeItem;
import com.google.gson.Gson;

import java.util.List;

public class FridgeListAdapter extends RecyclerView.Adapter<FridgeListAdapter.MyViewHolder>{
    public static final String EXPIRED_STATUS = "Expired";
    public static final String GOOD_STATUS = "Good";
    private List<FridgeItem> mDataset;
    private int mExpandedPosition = -1;

    private final String ADDED_ON = "Added on: ";
    private final String EXPIRE_DATE = "Expire: ";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView fridgeItem;
        TextView startDate;

        TextView expire_date;
        ImageButton deleteButton;
        ImageButton editButton;

        MyViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            fridgeItem = itemView.findViewById(R.id.fridge_item);
            startDate = itemView.findViewById(R.id.start_date);

            expire_date = itemView.findViewById(R.id.expire_date);
            deleteButton = itemView.findViewById(R.id.delete_btn);
            editButton = itemView.findViewById(R.id.edit_btn);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FridgeListAdapter(List<FridgeItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public FridgeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        FridgeItem curItem = mDataset.get(position);
        String startDate = ADDED_ON + curItem.getStartDate();
        String expireDate = EXPIRE_DATE + curItem.getValidUntilDate();

        holder.fridgeItem.setText(curItem.getName());
        holder.startDate.setText(startDate);
        holder.expire_date.setText(expireDate);

        setCardColorByStatus(holder, curItem.getStatus());
        handleExpandItem(holder, position);
        handleDeleteButton(holder, position);
        handleEditButton(holder, position);
    }

    private void setCardColorByStatus(@NonNull MyViewHolder holder, String status) {
        if (EXPIRED_STATUS.equalsIgnoreCase(status)){
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.status_expired));
        }
        else if (GOOD_STATUS.equalsIgnoreCase(status)) {
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.status_good));
        }
        else {
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.status_expire_soon));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    private void handleExpandItem(@NonNull MyViewHolder holder, int position) {
        final boolean isExpanded = position==mExpandedPosition;
        holder.deleteButton.setVisibility(isExpanded? View.VISIBLE:View.GONE);
        holder.editButton.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.startDate.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(v -> {
            mExpandedPosition = isExpanded ? -1:position;
            notifyItemChanged(position);
        });
    }

    private void handleDeleteButton(@NonNull MyViewHolder holder, int position) {
        holder.deleteButton.setOnClickListener(v -> {
            String itemId =  mDataset.get(position).getId();
            MainActivity.deleteItem(holder.itemView.getContext(), itemId);
            mDataset.remove(position);
            notifyDataSetChanged();
        });
    }

    private void handleEditButton(@NonNull MyViewHolder holder, int position) {
        holder.editButton.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, EditItemActivity.class);

            Gson gson = new Gson();
            intent.putExtra("item", gson.toJson(mDataset.get(position)));
            context.startActivity(intent);
        });
    }

}
