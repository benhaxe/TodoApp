package com.example.android.todolist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class) //Make room know that it should use a a type converter
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static AppDatabase sInstance;

    // Singleton Pattern
    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        // call allowMainThreadQueries before building the instance
                        // This will be temporary
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    // To add the TaskDAO, create an abstract method that return the [Dao class]
    public abstract TaskDao taskDao();

}

/**
 * This class must be abstract and must extend from [Room database]
 * [Singleton Pattern]
 * It is a software deign pattern that restricts the instantiation of a class to one object, used to ensure that only only
 * one object of a class is created
 *
 *
 * First time getInstance is called sInstance is null, then the object will created
 * and assigned to the sInstance variable.
 * */