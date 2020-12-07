package com.kotech.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    var notePosition= POSITION_NOTE_SET
    lateinit var spinner: Spinner
    lateinit var textNoteTitle: EditText
    lateinit var textNoteText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner=findViewById(R.id.spinnerCourses)
        textNoteTitle=findViewById(R.id.textNoteTitle)
        textNoteText=findViewById(R.id.textNoteText)

        val coursesAdapter= ArrayAdapter(this, android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter=coursesAdapter
        notePosition=intent.getIntExtra(NOTE_POSITION, POSITION_NOTE_SET)
        if(notePosition!= POSITION_NOTE_SET)
            displayNote()
    }

    private fun displayNote() {
        val note=DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition= DataManager.courses.values.indexOf(note.course)
        spinner.setSelection(coursePosition)


    }

}