package com.example.task41pp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText taskTitleEditText;
    private EditText taskDescriptionEditText;
    private EditText taskDueDateEditText;
    private TaskManagerDB taskManagerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_activity);

        taskTitleEditText = findViewById(R.id.taskTitleEditText);
        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);
        taskDueDateEditText = findViewById(R.id.taskDueDateEditText);

        taskManagerDB = new TaskManagerDB(this);
    }

    private void saveTask() {
        String title = taskTitleEditText.getText().toString().trim();
        String description = taskDescriptionEditText.getText().toString().trim();
        String dueDate = taskDueDateEditText.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(null, title, description, dueDate);
        // Save the task in the database
        boolean isSaved = taskManagerDB.addTask(task);
        if (isSaved) {
            Toast.makeText(this, "Task saved successfully", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        } else {
            Toast.makeText(this, "Failed to save task", Toast.LENGTH_SHORT).show();
        }
    }
}
