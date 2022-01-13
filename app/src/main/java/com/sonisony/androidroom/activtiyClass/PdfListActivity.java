package com.sonisony.androidroom.activtiyClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sonisony.androidroom.MainActivity;
import com.sonisony.androidroom.MainData;
import com.sonisony.androidroom.Modelclass.PdfFileData;
import com.sonisony.androidroom.R;
import com.sonisony.androidroom.RoomDB;

public class PdfListActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView Sync_now;
    RecyclerView rv_file_loader;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_list);

        Sync_now =  findViewById(R.id.Sync_now);
        progressBar = findViewById(R.id.progressbar);
        rv_file_loader = findViewById(R.id.rv_file_loader);
        //Initalize database
        database = RoomDB.getInstance(this);

        //Stroe database value in data list
//        dataList = database.mainDao().getAll();
        Sync_now.setOnClickListener(v -> {
            PdfFileData data = new PdfFileData();
            //set text on main data
           data.setPdf_file("http://africau.edu/images/default/sample.pdf");
            //Insert text in database
//            MainActivity.().insert(data);
              database.pdfSaveDao().insert(data);
        });
    }
}