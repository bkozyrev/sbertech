package com.bkozyrev.sbertech.mvp.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bkozyrev.sbertech.R;
import com.bkozyrev.sbertech.mvp.model.entities.Category;
import com.bkozyrev.sbertech.mvp.model.entities.RssItem;

import java.util.ArrayList;

public class HubsRecyclerViewAdapter extends
        RecyclerView.Adapter<HubsRecyclerViewAdapter.HubsViewHolder> {

    private ArrayList<RssItem> items;

    public HubsRecyclerViewAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public HubsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HubsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_hub, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HubsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<RssItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class HubsViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textViewDate;
        private AppCompatTextView textViewCreator;
        private AppCompatTextView textViewTitle;
        private AppCompatTextView textViewCategories;

        public HubsViewHolder(View itemView) {
            super(itemView);

            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewCreator = itemView.findViewById(R.id.textViewCreator);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewCategories = itemView.findViewById(R.id.textViewCategories);
        }

        public void bind(RssItem item) {
            textViewDate.setText(item.getPubDate());
            textViewCreator.setText(item.getCreator());
            textViewTitle.setText(item.getTitle());
            StringBuilder builder = new StringBuilder();
            for (Category category: item.getCategories()) {
                builder.append(category.getCategory());
                builder.append(" ");
            }
            textViewCategories.setText(builder.toString());
        }
    }
}
