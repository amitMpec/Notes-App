package com.example.notestemp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.notestemp.Adapter.ListAdapter1
import com.example.notestemp.Data.Notes
import com.example.notestemp.Data.NotesDB
import com.example.notestemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var data: List<Notes>
    private lateinit var listAdapter: ListAdapter1
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.floatingActionButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Edit::class.java)
            startActivity(intent)
        })

        val thread = Thread {
            val db = Room.databaseBuilder(applicationContext, NotesDB::class.java, "notesDB").build()

            val userDao = db.notesDao()
            data = userDao.getAll()
            listAdapter = ListAdapter1(data)
            binding.recyclerView.adapter = listAdapter

        }
        thread.start()
        binding.recyclerView.invalidate()
    }
}



