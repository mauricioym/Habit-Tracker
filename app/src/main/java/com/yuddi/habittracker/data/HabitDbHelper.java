package com.yuddi.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yuddi.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Mauricio on 11/4/2016.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = HabitDbHelper.class.getName();

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
        // The database is still at version 1, so the database won't be upgraded.
    }

    public void insertNewHabit(String name, int target) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_STREAK_TARGET, target);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        db.close();

        if (newRowId == -1) {
            Log.e(LOG_TAG, "Error on database insertion");
        } else {
            Log.i(LOG_TAG, "Added new habit with id " + newRowId);
        }

    }

    public Cursor getDbQuery() {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[]{
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_STREAK,
                HabitEntry.COLUMN_HABIT_MAX_STREAK,
                HabitEntry.COLUMN_HABIT_STREAK_TARGET
        };

        return db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

    }

}
