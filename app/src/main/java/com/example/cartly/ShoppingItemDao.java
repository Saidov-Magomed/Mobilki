package com.example.cartly;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_items")
    List<ShoppingItem> getAll();

    @Insert
    void insert(ShoppingItem item);

    @Delete
    void delete(ShoppingItem item);
}