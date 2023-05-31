package com.capstonebangkit.plantdoc.ui.pupuk

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.Pupuk
import com.squareup.picasso.Picasso

class PupukAdapter(private val pupukList: List<Pupuk>) : RecyclerView.Adapter<PupukAdapter.PupukViewHolder>() {

    class PupukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.imagePupuk)
        val tvName: TextView = itemView.findViewById(R.id.textName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PupukViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pupuk, parent, false)
        return PupukViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PupukViewHolder, position: Int) {
        val pupuk = pupukList[position]
        Picasso.get().load(pupuk.photoUrl).into(holder.ivPhoto)
        holder.tvName.text = pupuk.name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PupukDetailActivity::class.java)
            intent.putExtra("pupukId", pupuk.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pupukList.size
    }
}