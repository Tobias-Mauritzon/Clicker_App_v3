package com.exampleTest123.clicker_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button resultButton, scoreButton;
    FloatingActionButton resultFloatButton;
    TextView clicker;
    int globalNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultButton = findViewById(R.id.button);
        scoreButton = findViewById(R.id.button_score);
        resultFloatButton = findViewById(R.id.floatingActionButton);
        clicker = findViewById(R.id.Text_Clicker);

        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        resultFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void add(){
        String text = clicker.getText().toString();
        int num = Integer.parseInt(text);
        num = num + 1;
        globalNum = num;
        clicker.setText(Integer.toString(num));
    }

    private void submit(){
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
        dbHelper.addScore(globalNum);
    }
}