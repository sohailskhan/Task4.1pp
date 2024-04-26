package com.example.task41pp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.scheduling.Task;

public class TaskManagerDB extends SQLiteOpenHelper {

    public TaskManagerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TASKS_TABLE = "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " ("
                + TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskContract.TaskEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + TaskContract.TaskEntry.COLUMN_DESCRIPTION + " TEXT, "
                + TaskContract.TaskEntry.COLUMN_DUE_DATE + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_TASKS_TABLE = "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_TASKS_TABLE);
        onCreate(db);
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_DESCRIPTION,
                TaskContract.TaskEntry.COLUMN_DUE_DATE
        };

        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                TaskContract.TaskEntry.COLUMN_DUE_DATE + " ASC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION));
                String dueDate = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DUE_DATE));

                tasks.add(new Task(id, title, description, dueDate));
            }
            cursor.close();
        }

        return tasks;
    }

    public Task getTaskById(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_DESCRIPTION,
                TaskContract.TaskEntry.COLUMN_DUE_DATE
        };

        String selection = TaskContract.TaskEntry._ID + "=?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION));
                String dueDate = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DUE_DATE));

                return new Task(id, title, description, dueDate);
            }
            cursor.close();
        }

        return null;
    }

    public boolean addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TITLE, task.getTitle());
        values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.getDescription());
        values.put(TaskContract.TaskEntry.COLUMN_DUE_DATE, task.getDueDate());

        long newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        return newRowId != -1;
    }

    public boolean updateTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TITLE, task.getTitle());
        values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.getDescription());
        values.put(TaskContract.TaskEntry.COLUMN_DUE_DATE, task.getDueDate());

        String selection = TaskContract.TaskEntry._ID + "=?";
        String[] selectionArgs = { String.valueOf(task.getId()) };

        int rowsAffected = db.update(
                TaskContract.TaskEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return rowsAffected > 0;
    }

    public boolean deleteTask(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = TaskContract.TaskEntry._ID + "=?";
        String[] selectionArgs = { String.valueOf(id) };

        int rowsAffected = db.delete(
                TaskContract.TaskEntry.TABLE_NAME,
                selection,
                selectionArgs
        );

        return rowsAffected > 0;
    }
}
