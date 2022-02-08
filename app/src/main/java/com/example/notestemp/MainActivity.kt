package com.example.notestemp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notestemp.Adapter.ListAdapter1
import com.example.notestemp.Data.Notes
import com.example.notestemp.Data.NotesDB
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var data: List<Notes>// =

    // arrayOf("Green","Red","Blue","Green","Red","Blue","Green","Red","Blue").toList()
    private lateinit var list: RecyclerView
    private lateinit var listAdapter: ListAdapter1
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        list.layoutManager = LinearLayoutManager(this)

        floatingActionButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Edit::class.java)
            startActivity(intent)
        })

        val thread = Thread {
            val db = Room.databaseBuilder(
                applicationContext,
                NotesDB::class.java, "notesDB"
            ).build()

            val userDao = db.notesDao()
            data = userDao.getAll()
            listAdapter = ListAdapter1(data)
            list.adapter = listAdapter

        }
        thread.start()
        list.invalidate()
    }
}



