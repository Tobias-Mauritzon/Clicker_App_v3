package com.exampleTest123.clicker_app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
        String query = "CREATE TABLE "+TABLE_NAME+
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

    void addScore(int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SCORE, score);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Failed insert ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getTopTenScores(){

        Cursor cursor = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+COLUMN_SCORE+ " FROM " + TABLE_NAME+ " ORDER BY "+ COLUMN_SCORE+" DESC LIMIT 10 ";
        //db.execSQL(query);

        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
