package com.yuddi.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yuddi.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Mauricio on 11/4/2016.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "habit_tracker.db";
    
    private static final int DATABASE_VERSION = 1;
    
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_STREAK + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_HABIT_MAX_STREAK + " INTEGER NOT NULL DEFAULT 0,"
                + HabitEntry.COLUMN_HABIT_STREAK_TARGET + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
    }
    
}
