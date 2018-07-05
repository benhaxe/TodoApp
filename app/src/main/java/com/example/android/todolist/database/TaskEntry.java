package com.example.android.todolist.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Using @Entity turn this pojo to an Entity
 * By default table name will be class name
 * Annotate the class with Entity. Use "task" for the table name
 * So assign a specific table name
 * */
@Entity (tableName = "task")
public class TaskEntry {

    // Annotate the id as PrimaryKey. Set autoGenerate to true. //Member Variable
    /**
     * Primary key will be id of the task,
     * to make the database handle the uniqueness set it auto generate value to true
     * */
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String description;
    private int priority;

    @ColumnInfo (name = "updated_at")
    private Date updatedAt;

    // Use the Ignore annotation so Room knows that it has to use the other constructor instead
    // This constructor will be used to retrieve data from the db
    @Ignore
    public TaskEntry(String description, int priority, Date updatedAt) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    /*
    * Room can not use more than one constructor so we ignored the first one
    * The second constructor will be used to add new Task  to the database
    * */
    public TaskEntry(int id, String description, int priority, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
