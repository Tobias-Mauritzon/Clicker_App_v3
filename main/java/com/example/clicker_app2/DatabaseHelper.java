package com.example.clicker_app2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TapperScore";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "highscore";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_TIME = "time";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+DATABASE_NAME+
                " ("+ COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_SCORE+ " INTEGER, "+
                COLUMN_TIME+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
