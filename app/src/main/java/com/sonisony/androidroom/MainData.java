package com.sonisony.androidroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_name")
public class MainData {
    //Define Table name
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //create text column
    @ColumnInfo(name = "text")
    private String text;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
//generate Getter and setter
}
