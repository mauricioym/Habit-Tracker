package com.yuddi.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Mauricio on 11/4/2016.
 */

public final class HabitContract {

    private HabitContract() {
        throw new AssertionError("No HabitContract instances for you!");
    }

    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_STREAK = "streak";
        public static final String COLUMN_HABIT_MAX_STREAK = "max_streak";
        public static final String COLUMN_HABIT_STREAK_TARGET = "target";

    }
}
