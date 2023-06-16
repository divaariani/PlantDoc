package com.capstonebangkit.plantdoc.ui.penyakit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PenyakitTanaman
import com.squareup.picasso.Picasso

class PenyakitAdapter(private val penyakitList: List<PenyakitTanaman>) : RecyclerView.Adapter<PenyakitAdapter.PenyakitViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    class PenyakitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagePenyakit: ImageView = itemView.findViewById(R.id.imagePenyakit)
        val namePenyakit: TextView = itemView.findViewById(R.id.namePenyakit)
        // val descriptionPenyakit: TextView = itemView.findViewById(R.id.descriptionPenyakit)
    }

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: PenyakitTanaman)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenyakitViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_penyakit, parent, false)
        return PenyakitViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PenyakitViewHolder, position: Int) {
        val penyakit = penyakitList[position]
        Picasso.get().load(penyakit.photoUrl).into(holder.imagePenyakit)
        holder.namePenyakit.text = penyakit.name
        // holder.descriptionPenyakit.text = penyakit.description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PenyakitDetailActivity::class.java)
            intent.putExtra("penyakitId", penyakit.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return penyakitList.size
    }
}