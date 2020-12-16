package com.kotech.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        fab=findViewById(R.id.fab)
        recyclerView=findViewById(R.id.recyclerview)

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter= NoteAdapter(this,DataManager.notes)


        fab.setOnClickListener {
            val activityIntent= Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }


    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}