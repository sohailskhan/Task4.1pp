package com.example.task41pp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMain;
    private VerticalAdapter vAdapter;
    private TaskManagerDB taskManagerDB;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_activity);
        recyclerViewMain = findViewById(R.id.recyclerView);
        taskManagerDB = new TaskManagerDB(this);
        createTaskList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createTaskList();
    }

    private void createTaskList() {
        taskList = taskManagerDB.getAllTasks();
        // You can use taskList to populate your RecyclerView adapter
    }
}
