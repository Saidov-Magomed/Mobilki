package com.example.cartly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private List<ShoppingItem> items;
    private ShoppingItemDao dao;

    public ShoppingListAdapter(List<ShoppingItem> items, ShoppingItemDao dao) {
        this.items = items;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingItem item = items.get(position);
        holder.textView.setText(item.getName());

        holder.deleteButton.setOnClickListener(v -> {
            dao.delete(item);
            items.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_text);
            deleteButton = view.findViewById(R.id.delete_button);
        }
    }
}