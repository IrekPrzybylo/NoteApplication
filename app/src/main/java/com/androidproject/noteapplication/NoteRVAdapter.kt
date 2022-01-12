package com.androidproject.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidproject.noteapplication.entity.Note

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    //creating a variable for our all notes list.
    private val allNotes = ArrayList<Note>()

    //creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //creating an initializing all our variables which we have added in layout file.
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflating layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //setting data to item of recycler view.
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText("Last Updated : "+allNotes.get(position).timeStamp)
        //adding click listner to delete image view icon.
        holder.deleteIV.setOnClickListener {
            //calling a note click interface and passing a position to it.
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        //adding click listner to recycler view item.
        holder.itemView.setOnClickListener {
            //calling a note click interface and passing a position to it.
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        //returning our list size.
        return allNotes.size
    }

    //update our list of notes.
    fun updateList(newList: List<Note>) {
        //clearing our notes array list
        allNotes.clear()
        //adding a new list to our all notes list.
        allNotes.addAll(newList)
        //calling notify data change method to notify our adapter.
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface {
    //creating a method for click action on delete image view.
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    //creating a method for click action on recycler view item for updating it.
    fun onNoteClick(note: Note)
}