package com.example;

import android.icu.text.CaseMap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "events")
public class Event implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int ID = 0;
    @ColumnInfo(name = "title")
    String title = "";
    @ColumnInfo(name = "category")
    String category = "";
    @ColumnInfo(name = "location")
    String location = "";
    @ColumnInfo(name = "date")
    String date = "";
    @ColumnInfo(name = "time")
    String time = "";

   public int id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String Category) {
        this.category = Category;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return "";
    }
}









