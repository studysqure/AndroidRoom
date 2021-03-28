package com.sonisony.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button add_btn,reset_btn;
    private EditText enter_txt;

    List<MainData> dataList = new ArrayList<>();
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
reset_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Delete all data from databaes
        database.mainDao().reset(dataList);
        dataList.clear();
        dataList.addAll(database.mainDao().getAll());
        mainAdapter.notifyDataSetChanged();

    }
});
    }
}