package com.androidproject.noteapplication.repository

import androidx.lifecycle.LiveData
import com.androidproject.noteapplication.dao.NotesDao
import com.androidproject.noteapplication.entity.Note

class NoteRepository(private val notesDao: NotesDao) {


    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }
}