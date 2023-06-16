package com.capstonebangkit.plantdoc.ui.berita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.BeritaData

class BeritaActivity : AppCompatActivity() {

    private lateinit var beritaAdapter: BeritaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)
        val rvListBerita: RecyclerView = findViewById(R.id.rvListBerita)

        beritaAdapter = BeritaAdapter(BeritaData.berita)

        rvListBerita.layoutManager = LinearLayoutManager(this)
        rvListBerita.adapter = beritaAdapter
    }
}