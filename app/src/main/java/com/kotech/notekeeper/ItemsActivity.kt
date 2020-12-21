package com.kotech.notekeeper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private val noteAdapter by lazy { NoteAdapter(this, DataManager.notes) }
    private val courseAdapter by lazy { CourseRecyclerAdapter(this, DataManager.courses.values.toList()) }
    private val gridLayoutManager by lazy { GridLayoutManager(this,2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView=findViewById(R.id.recyclerview)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
        }

        drawerLayout=findViewById(R.id.drawer_layout)
        navigationView= findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val drawerToggle= ActionBarDrawerToggle(this, drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        displayNotes()

    }

    private fun displayNotes() {
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = noteAdapter

        navigationView.menu.findItem(R.id.nav_notes).isChecked=true
    }

    private fun displayCourses(){
        recyclerView.layoutManager=gridLayoutManager
        recyclerView.adapter=courseAdapter

        navigationView.menu.findItem(R.id.nav_courses)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_courses -> {
                displayCourses()
            }
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            R.id.nav_notes -> {
                displayNotes()
            }

            R.id.nav_send -> {
                handleSelection("Send")
            }

            R.id.nav_share -> {
                handleSelection("Don't you think you share enough ?")
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(recyclerView,message,Snackbar.LENGTH_LONG).show()
    }

}