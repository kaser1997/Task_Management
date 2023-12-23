package com.example.task_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

     private Context context;
     private static final String DATABASE_NAME = "Task.db";
     private static final int DATABASE_VERSION = 1;
     private static final String TABLE_NAME  =   "Tasks";

     private static final String COLUM_ID  =   "_id";

     private static final String GATEGORY_NAME_COL  =   "CategoryName";


     public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        GATEGORY_NAME_COL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addGategory (String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(GATEGORY_NAME_COL, title);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Fauls", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show();
        }
    }

     Cursor readAllData(){
         String query = "SELECT * FROM " + TABLE_NAME;
         SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = null;
         if(db != null){
             cursor = db.rawQuery(query, null);
         }
         return cursor;
     }

    void updateData(String row_id, String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GATEGORY_NAME_COL, title);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
 }


