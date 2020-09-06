package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.PrintStream
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validateExistence()
        val task = intent.getStringExtra("TASK")
        if (task != null) {
            Log.v("Consola", task)
            saveNewActivity(task)
            validateExistence()
        } else {
            Log.v("Consola", "task null")
        }

        listaDeActividades.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                "Se borró la actividad  ${listaDeActividades.getItemAtPosition(position)}",
                Toast.LENGTH_SHORT
            ).show()

            val input = Scanner(openFileInput("activity_list.txt"))
            var temp = arrayListOf<String>()
            while (input.hasNextLine()) {
                val line = input.nextLine()
                temp.add(line)
                if (listaDeActividades.getItemAtPosition(position) == line) {
                    Log.v(
                        "Archivo",
                        "coincide ${listaDeActividades.getItemAtPosition(position)} , $line"

                    )
                    var file: File = File("/data/data/com.example.todolist/files/activity_list.txt")
                    file.delete()
                    temp.remove(line)
                    Log.v("Archivo", "borrado")

                }
            }
            input.close()
            for (num in temp) {
                Log.v("Archivo", "for para saber que estan $num")
                saveNewActivity(num)
            }
            validateExistence()
        }
    }


    fun onClickCreateNewTask(v: View) {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveNewActivity(task: String) {
        val out = PrintStream(openFileOutput("activity_list.txt", MODE_APPEND))
        out.println(task.toUpperCase())
        out.close()
    }

    private fun readFile() {
        val input = Scanner(openFileInput("activity_list.txt"))
        val activityList: ArrayList<String> = arrayListOf<String>()
        while (input.hasNextLine()) {
            val line = input.nextLine()
            activityList.add(line.toUpperCase())
        }
        input.close()
        //agregar contenido a la vista
        Log.v("Consola", activityList.joinToString(","))
        val adapter = ActivityListAdapter(this, R.layout.activity_list, activityList)
        listaDeActividades.adapter = adapter
    }

    private fun validateExistence() {
        var file: File = File("/data/data/com.example.todolist/files/activity_list.txt")
        if (file.exists()) {
            Log.v("Consola", "Exists $file")
            readFile()
        } else {
            Log.v("Consola", "No exists $file")
            val out = PrintStream(openFileOutput("activity_list.txt", MODE_APPEND))
            readFile()
            out.close()
        }
    }
}