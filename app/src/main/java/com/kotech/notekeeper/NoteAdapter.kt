package com.kotech.notekeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val notes:List<NoteInfo>):RecyclerView.Adapter<NoteAdapter.NoteViewHoler>() {

    class NoteViewHoler(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val textCourse= itemView?.findViewById<TextView>(R.id.textCourse)
        val textTitle= itemView?.findViewById<TextView>(R.id.textTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHoler {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return NoteViewHoler(view)
    }

    override fun onBindViewHolder(holder: NoteViewHoler, position: Int) {
        val note= notes[position]
        holder.textCourse?.text=note.course?.title
        holder.textTitle?.text=note.title
    }

    override fun getItemCount(): Int= notes.size
}