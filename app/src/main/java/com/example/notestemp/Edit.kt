package com.example.notestemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.notestemp.Data.Notes
import com.example.notestemp.Data.NotesDB
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class Edit : AppCompatActivity() {

    lateinit var editTitle: EditText
    lateinit var editSubtitle: EditText
    lateinit var saveNotes: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editTitle = findViewById(R.id.editTitle)
        editSubtitle = findViewById(R.id.editSubtitle)
        saveNotes = findViewById(R.id.saveNotes)

        saveNotes.setOnClickListener(View.OnClickListener {
            val thread = Thread {
                insetData()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            thread.start()
        })

    }


    fun insetData() {
        val db = Room.databaseBuilder(
            applicationContext,
            NotesDB::class.java, "notesDB"
        ).build()
        var dataTitle: String = editTitle.text.toString()
        var dataSubtitle: String = editSubtitle.text.toString()

        if (dataTitle.isEmpty() && dataSubtitle.isEmpty()) {
            Toast.makeText(this, "Please insert some data", Toast.LENGTH_LONG).show()
        }

        val userDao = db.notesDao()
        userDao.insert(Notes(0, dataTitle, dataSubtitle))

    }
}