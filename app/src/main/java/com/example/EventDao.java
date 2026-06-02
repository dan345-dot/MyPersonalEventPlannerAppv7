package com.example;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);
    @Query("delete from events where id=:id")
    void delete(int id);

    @Query("SELECT * FROM events ORDER BY date ASC")
    List<Event> getAllEvents();

}
