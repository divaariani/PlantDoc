package com.capstonebangkit.plantdoc.ui.berita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.Berita

class BeritaAdapter(private val beritaList: List<Berita>) :
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameBerita: TextView = view.findViewById(R.id.nameBerita)
        val descriptionBerita: TextView = view.findViewById(R.id.descriptionBerita)
        val imageBerita: ImageView = view.findViewById(R.id.imageBerita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val berita = beritaList[position]

        holder.nameBerita.text = berita.headline
        holder.descriptionBerita.text = berita.description
        Glide.with(holder.itemView)
            .load(berita.photoUrl)
            .into(holder.imageBerita)

        holder.itemView.setOnClickListener {
            // Handle item click here
        }
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }
}