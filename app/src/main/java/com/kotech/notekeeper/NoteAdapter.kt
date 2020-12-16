package com.kotech.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val notes:List<NoteInfo>):RecyclerView.Adapter<NoteAdapter.NoteViewHoler>() {

    private val layoutInflater: LayoutInflater =LayoutInflater.from(context)

    inner class NoteViewHoler(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val textCourse= itemView?.findViewById<TextView>(R.id.textCourse)
        val textTitle= itemView?.findViewById<TextView>(R.id.textTitle)
        var notePosition=0

        init {
            itemView?.setOnClickListener{
                val intent= Intent(context,NoteActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHoler {
        val view= layoutInflater.inflate(R.layout.item_note,parent,false)
        return NoteViewHoler(view)
    }

    override fun onBindViewHolder(holder: NoteViewHoler, position: Int) {
        val note= notes[position]
        holder.textCourse?.text=note.course?.title
        holder.textTitle?.text=note.title
        holder.notePosition=position
    }

    override fun getItemCount(): Int= notes.size
}