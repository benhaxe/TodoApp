package com.example.android.todolist.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

// The Dao class must be an interface, with a DAO annotation
@Dao
public interface TaskDao {

    // Fetch data from the db [The first constructor @TaskEntry class will be used here]
    @Query("SELECT * FROM task ORDER BY priority")
    LiveData<List<TaskEntry>> loadAllTasks();

    // Add new data to the database [The second constructor @TaskEntry class will be used here]
    @Insert
    void insertTask(TaskEntry taskEntry);

    // Update a pre existing table in the db
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    // Delete a table in the db
    @Delete
    void deleteTask(TaskEntry taskEntry);

    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<TaskEntry> loadTaskById(int id);
}
