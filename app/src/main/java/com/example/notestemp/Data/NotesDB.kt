package com.example.notestemp.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDB:RoomDatabase() {
    abstract fun notesDao():NotesDAO
}