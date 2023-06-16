package com.capstonebangkit.plantdoc.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.Pupuk

class HomeAdapter(private val pupukList: List<Pupuk>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_popup_pupuk, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pupuk = pupukList[position]
        holder.bind(pupuk)
    }

    override fun getItemCount(): Int {
        return pupukList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePupuk: ImageView = itemView.findViewById(R.id.imagePupuk)
        private val namePupuk: TextView = itemView.findViewById(R.id.namePupuk)

        fun bind(pupuk: Pupuk) {
            Glide.with(itemView)
                .load(pupuk.photoUrl)
                .into(imagePupuk)
            namePupuk.text = "Pupuk " + pupuk.name
        }
    }
}