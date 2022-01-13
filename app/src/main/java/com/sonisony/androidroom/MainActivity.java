package com.sonisony.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sonisony.androidroom.Modelclass.PdfFileData;
import com.sonisony.androidroom.activtiyClass.PdfListActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button add_btn,reset_btn;
    private EditText enter_txt;
    private TextView read_pdf_txt,sync_data_btn;
    List<MainData> dataList = new ArrayList<>();
    PdfFileData pdfdata;

    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        add_btn = findViewById(R.id.add_btn);
        reset_btn = findViewById(R.id.reset_btn);
        enter_txt = findViewById(R.id.enter_txt_et);
        read_pdf_txt = findViewById(R.id.read_pdf_txt);
        sync_data_btn = findViewById(R.id.sync_data_btn);
        //Initalize database
        database = RoomDB.getInstance(this);

        //Stroe database value in data list
        dataList = database.mainDao().getAll();

        //Initialize linear layout manage
        linearLayoutManager = new LinearLayoutManager(this);
        //set layout manager in recycler
        recyclerView.setLayoutManager(linearLayoutManager);
        //set adapter
        mainAdapter =  new MainAdapter(MainActivity.this,dataList);
        recyclerView.setAdapter(mainAdapter);
        pdfdata = database.pdfSaveDao().getAll();
        Log.d("LLLLLLLLLLLLLLLLLLLLL", "onCreate: "+pdfdata);

        sync_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PdfListActivity.class);
                startActivity(i);
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Get string from edit text
                String sText = enter_txt.getText().toString().trim();
                //check  condition
                if (!sText.equals("")){
                    //when text is not empty
                    //initialize main data
                    MainData data = new MainData();
                    //set text on main data
                    data.setText(sText);
                    //Insert text in database
                    database.mainDao().insert(data);
                    //clear edit text
//                    Toast.makeText(MainActivity.this, "inserted successfully", Toast.LENGTH_SHORT).show();
                    enter_txt.setText("");
                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    mainAdapter.notifyDataSetChanged();
                }
            }
        });

        read_pdf_txt.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,PDFLoaderActivity.class);
            startActivity(i);
        });

reset_btn.setOnClickListener(v -> {
    //Delete all data from databaes
    database.mainDao().reset(dataList);
    dataList.clear();
    dataList.addAll(database.mainDao().getAll());
    mainAdapter.notifyDataSetChanged();

});
    }
}