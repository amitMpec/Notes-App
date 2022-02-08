package com.example.notestemp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notestemp.Data.Notes
import com.example.notestemp.R

class ListAdapter1(private val dataSet: List<Notes>) :
    RecyclerView.Adapter<ListAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter1.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter1.ViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.subTitle.text = dataSet[position].subtitle

        holder.card_item.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                holder.title.context,
                dataSet[position].title + "\n" + dataSet[position].subtitle,
                Toast.LENGTH_LONG
            )
                .show()
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val subTitle: TextView
        var card_item: ConstraintLayout

        init {
            title = view.findViewById(R.id.title)
            subTitle = view.findViewById(R.id.subtitle)
            card_item = view.findViewById(R.id.card_item)
        }
    }
}