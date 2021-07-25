package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        val detailTaskViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        val edTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val edDescription = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val edDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val btnDelete = findViewById<Button>(R.id.btn_delete_task)

        val extras = intent.extras
        val taskId = extras?.getInt(TASK_ID)
        detailTaskViewModel.setTaskId(taskId)
        detailTaskViewModel.task.observe(this, {task ->
            if (task != null) {
                edTitle.setText(task.title)
                edDescription.setText(task.description)
                edDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
            }
        })
        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }
    }
}