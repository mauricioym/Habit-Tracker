package com.yuddi.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yuddi.habittracker.data.HabitContract.HabitEntry;
import com.yuddi.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        // Insert and log habits examples
        insertNewHabit("Exercise", 30);
        insertNewHabit("Wake up at 7:00 am", 21);
        logDatabaseInfo();

    }

    private void insertNewHabit(String name, int target) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_STREAK_TARGET, target);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId > 0) {
            Log.i(LOG_TAG, "Added new habit with id " + newRowId);
        } else if (newRowId == -1) {
            Log.e(LOG_TAG, "Error on database insertion");
        }

    }

    private void logDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        String result = "";

        try {

            result += "Tracking " + cursor.getCount() + " habits.\n\n";
            result += HabitEntry._ID + " - "
                    + HabitEntry.COLUMN_HABIT_STREAK + " - "
                    + HabitEntry.COLUMN_HABIT_MAX_STREAK + " - "
                    + HabitEntry.COLUMN_HABIT_STREAK_TARGET + "\n";

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int streakColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STREAK);
            int maxStreakColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_MAX_STREAK);
            int targetColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STREAK_TARGET);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentStreak = cursor.getInt(streakColumnIndex);
                int currentMaxStreak = cursor.getInt(maxStreakColumnIndex);
                int currentTarget = cursor.getInt(targetColumnIndex);

                result += "\n"
                        + currentId + " - "
                        + currentName + " - "
                        + currentStreak + " - "
                        + currentMaxStreak + " - "
                        + currentTarget;
            }

        } finally {
            cursor.close();
        }

        Log.i(LOG_TAG, result);

    }

}
