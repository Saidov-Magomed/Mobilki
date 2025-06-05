package com.example.cartly;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextItem;
    private Button addButton;
    private RecyclerView recyclerView;
    private com.example.cartly.ShoppingListAdapter adapter;
    private AppDatabase db;
    private ShoppingItemDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItem = findViewById(R.id.edit_text_item);
        addButton = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.recycler_view);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "shopping-db")
                .allowMainThreadQueries()
                .build();
        dao = db.shoppingItemDao();

        List<ShoppingItem> items = dao.getAll();
        adapter = new com.example.cartly.ShoppingListAdapter(items, dao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String itemName = editTextItem.getText().toString().trim();
            if (!itemName.isEmpty()) {
                ShoppingItem newItem = new ShoppingItem(itemName);
                dao.insert(newItem);
                items.add(newItem);
                adapter.notifyItemInserted(items.size());
                editTextItem.setText("");
            }
        });
    }
}