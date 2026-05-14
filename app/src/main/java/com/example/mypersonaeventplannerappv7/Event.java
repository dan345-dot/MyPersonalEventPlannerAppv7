package com.example.mypersonaeventplannerappv7;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event")
public class Event {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "event_text")
    public  String eventText;

    public Event(String eventText){
        this.eventText = eventText;
    }
}
