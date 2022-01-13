package com.sonisony.androidroom.DaoFile;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sonisony.androidroom.MainData;
import com.sonisony.androidroom.Modelclass.PdfFileData;

import java.util.List;

@Dao
public interface PdfSaveDao {
    @Insert(onConflict = REPLACE)
    void insert(PdfFileData pdfFileData);
/*
    @Query("SELECT *FROM pdf_file_tbl")
    List<PdfFileData> getAll();*/
    @Query("SELECT *FROM pdf_file_tbl")
    PdfFileData getAll();
}
