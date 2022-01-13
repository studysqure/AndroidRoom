package com.sonisony.androidroom.Modelclass;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.ToString;

@Entity(tableName = "pdf_file_tbl")
@ToString
public class PdfFileData {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "pdf_file")
    String pdf_file;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPdf_file() {
        return pdf_file;
    }

    public void setPdf_file(String pdf_file) {
        this.pdf_file = pdf_file;
    }
}
