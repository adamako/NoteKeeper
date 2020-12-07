package com.kotech.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner=findViewById(R.id.spinnerCourses)

        val coursesAdapter= ArrayAdapter(this, android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter=coursesAdapter

    }

}