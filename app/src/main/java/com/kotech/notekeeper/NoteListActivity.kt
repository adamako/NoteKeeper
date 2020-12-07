package com.kotech.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class NoteListActivity : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    lateinit var noteList:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        fab=findViewById(R.id.fab)
        noteList=findViewById(R.id.noteList)

        val courseAdapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.courses.values.toList())
        noteList.adapter=courseAdapter

        fab.setOnClickListener {
            val activityIntent= Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }
    }
}