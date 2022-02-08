package com.example.notestemp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getAll(): List<Notes>

    @Insert
    fun insert(vararg users: Notes)

    @Delete
    fun delete(user: Notes)
}