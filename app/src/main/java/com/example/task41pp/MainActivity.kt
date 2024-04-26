package com.example.task41pp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var createTaskButton: Button
    private lateinit var viewTasksButton: Button
    private lateinit var viewTaskDetailsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        createTaskButton = findViewById(R.id.createTaskButton)
        viewTasksButton = findViewById(R.id.viewTasksButton)
        viewTaskDetailsButton = findViewById(R.id.viewTaskDetailsButton)

        // Set click listeners
        createTaskButton.setOnClickListener {
            // Open CreateTaskActivity
            val intent = Intent(this@MainActivity, CreateTaskActivity::class.java)
            startActivity(intent)
        }

        viewTasksButton.setOnClickListener {
            // Open TaskListActivity
            val intent = Intent(this@MainActivity, TaskListActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for viewing task details (optional)
        viewTaskDetailsButton.setOnClickListener {
            // Open TaskDetailsActivity for a specific task (replace "taskId" with actual task ID)
            val taskId = "taskId" // Replace "taskId" with actual task ID
            val intent = Intent(this@MainActivity, TaskDetailsActivity::class.java)
            intent.putExtra("id", taskId)
            startActivity(intent)
        }
    }
}
