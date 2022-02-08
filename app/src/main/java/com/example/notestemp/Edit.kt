package com.example.notestemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.notestemp.Data.Notes
import com.example.notestemp.Data.NotesDB
import com.example.notestemp.databinding.ActivityEditBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class Edit : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

        binding.saveNotes.setOnClickListener(View.OnClickListener {
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

        val dataTitle: String = binding.editTitle.text.toString()
        val dataSubtitle: String = binding.editSubtitle.text.toString()

        if (dataTitle.isEmpty() && dataSubtitle.isEmpty()) {
            Toast.makeText(this, "Please insert some data", Toast.LENGTH_LONG).show()
        }

        val userDao = db.notesDao()
        userDao.insert(Notes(0, dataTitle, dataSubtitle))

    }
}