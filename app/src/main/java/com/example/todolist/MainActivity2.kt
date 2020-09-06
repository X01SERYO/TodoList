package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        addTask.setOnClickListener { v -> goToMainActivity(v) }
    }

    private fun goToMainActivity(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("TASK", newTask.text.toString())
        if (newTask.text.toString().isEmpty()) {
            Toast.makeText(this, "INGRESE TAREA", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(intent)
        }

    }
}