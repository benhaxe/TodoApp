package com.example.android.todolist.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
     /**
      * Receive a time stamp and and convert it to a [Date Object]
      * Room will use this method when reading from the database
     **/

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    /**
     * Receive a Date Object and and convert it to a [Time stamp long]
     * Room will use this method when writing into the database
     **/
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
