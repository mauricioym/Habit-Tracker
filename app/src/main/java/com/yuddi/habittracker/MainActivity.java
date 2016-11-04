package com.yuddi.habittracker;

import android.database.Cursor;
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
        mDbHelper.insertNewHabit("Exercise", 30);
        mDbHelper.insertNewHabit("Wake up at 7:00 am", 21);
        Cursor cursor = mDbHelper.getDbQuery();
        try {
            logDatabaseQuery(cursor);
        } finally {
            cursor.close();
        }

    }

    private void logDatabaseQuery(Cursor cursor) {
        String result = "";

        result += "Tracking " + cursor.getCount() + " habits.\n\n";
        result += HabitEntry._ID + " - "
                + HabitEntry.COLUMN_HABIT_NAME + " - "
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


        Log.i(LOG_TAG, result);
    }

}
