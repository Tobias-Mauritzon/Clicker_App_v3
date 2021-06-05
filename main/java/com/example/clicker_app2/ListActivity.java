package com.example.clicker_app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ArrayList<Integer> scores;
    TextView textViewRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.res_List);
        textViewRes = findViewById(R.id.textViewRes);
        populateArray();
        populateView();
    }

    private void populateArray(){
        dbHelper = new DatabaseHelper(ListActivity.this);
        scores = new ArrayList<>();

        Cursor cur = dbHelper.getTopTenScores();

        if(cur.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cur.moveToNext()){
                scores.add(cur.getInt(0));
            }
        }
    }

    private void populateView(){
        for(int num : scores){
            textViewRes.setText(textViewRes.getText() + "\n"+num);
        }

        //textViewRes.setText(scores.toString());

    }
}