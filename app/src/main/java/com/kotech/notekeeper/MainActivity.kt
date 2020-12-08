package com.kotech.notekeeper

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private var notePosition= POSITION_NOTE_SET
    private lateinit var spinner: Spinner
    private lateinit var textNoteTitle: EditText
    private lateinit var textNoteText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner=findViewById(R.id.spinnerCourses)
        textNoteTitle=findViewById(R.id.textNoteTitle)
        textNoteText=findViewById(R.id.textNoteText)

        val coursesAdapter= ArrayAdapter(this, android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter=coursesAdapter
        notePosition=savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOTE_SET)?:intent.getIntExtra(NOTE_POSITION, POSITION_NOTE_SET)
        if(notePosition!= POSITION_NOTE_SET)
            displayNote()
        else{
          val note=NoteInfo()
            DataManager.notes.add(note)
            notePosition=DataManager.notes.lastIndex
        }
    }

    private fun displayNote() {
        val note=DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition= DataManager.courses.values.indexOf(note.course)
        spinner.setSelection(coursePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_next -> {
                nextNote()
                true
            }
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun nextNote() {
        notePosition++
        displayNote()
        invalidateOptionsMenu()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition>=DataManager.notes.lastIndex){
            val menuItem=menu?.findItem(R.id.action_next)
            if(menuItem!=null){
                menuItem.icon=getDrawable(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled=false

            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note= DataManager.notes[notePosition]
        note.text=textNoteText.text.toString()
        note.title=textNoteTitle.text.toString()
        note.course= spinner.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }

}