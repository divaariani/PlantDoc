package com.capstonebangkit.plantdoc.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PenyakitTanaman

class HomePenyakitAdapter(private val penyakitList: List<PenyakitTanaman>) :
    RecyclerView.Adapter<HomePenyakitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val namePenyakitTextView: TextView = view.findViewById(R.id.namePenyakit)
        // val photoPenyakitImageView: ImageView = view.findViewById(R.id.imagePenyakit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popup_penyakit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val penyakit = penyakitList[position]

        holder.namePenyakitTextView.text = penyakit.name
        holder.itemView.setOnClickListener {
            // Handle item click here
        }
    }

    override fun getItemCount(): Int {
        return penyakitList.size
    }
}