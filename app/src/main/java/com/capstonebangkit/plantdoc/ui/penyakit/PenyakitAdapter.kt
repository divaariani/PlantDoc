package com.capstonebangkit.plantdoc.ui.penyakit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PenyakitTanaman
import com.squareup.picasso.Picasso

class PenyakitAdapter(private val penyakitList: List<PenyakitTanaman>) :
    RecyclerView.Adapter<PenyakitAdapter.ViewHolder>() {

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
        // Load the photo using an image loading library like Picasso or Glide
        // For example, using Picasso:
        // Picasso.get().load(penyakit.photoUrl).into(holder.photoPenyakitImageView)

        // Handle item click listener if needed
        holder.itemView.setOnClickListener {
            // Handle item click here
        }
    }

    override fun getItemCount(): Int {
        return penyakitList.size
    }
}