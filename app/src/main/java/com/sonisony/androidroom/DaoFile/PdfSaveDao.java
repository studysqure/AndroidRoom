package com.sonisony.androidroom.DaoFile;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;

import com.sonisony.androidroom.MainData;
import com.sonisony.androidroom.Modelclass.PdfFileData;

@Dao
public interface PdfSaveDao {
    @Insert(onConflict = REPLACE)
    void insert(PdfFileData pdfFileData);
}
