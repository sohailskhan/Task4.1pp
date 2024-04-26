package com.example.task41pp;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView dueDateTextView;
    private TaskManagerDB taskManagerDB;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details_activity);

        titleTextView = findViewById(R.id.titleDetailTextView);
        descriptionTextView = findViewById(R.id.descriptionDetailTextView);
        dueDateTextView = findViewById(R.id.dueDateDetailTextView);

        taskManagerDB = new TaskManagerDB(this);
        String taskId = getIntent().getStringExtra("id");
        if (taskId != null) {
            task = taskManagerDB.getTask(taskId);
            if (task != null) {
                titleTextView.setText(task.getTitle());
                descriptionTextView.setText(task.getDescription());
                dueDateTextView.setText(task.getDueDate());
            }
        }
    }
}
