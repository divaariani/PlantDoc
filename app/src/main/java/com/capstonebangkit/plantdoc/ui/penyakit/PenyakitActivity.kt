package com.capstonebangkit.plantdoc.ui.penyakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PenyakitTanaman
import com.capstonebangkit.plantdoc.data.PenyakitTanamanData

class PenyakitActivity : AppCompatActivity() {
    private lateinit var adapter : PenyakitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penyakit)
        val rvListPenyakit: RecyclerView = findViewById(R.id.rvListPenyakit)
        adapter = PenyakitAdapter(PenyakitTanamanData.penyakit)
        rvListPenyakit.layoutManager = LinearLayoutManager(this)
        rvListPenyakit.adapter = adapter
        adapter.setOnItemClickCallback(object : PenyakitAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PenyakitTanaman) {
                Intent(this@PenyakitActivity, PenyakitDetailActivity::class.java).also {
                    it.putExtra(PenyakitDetailActivity.EXTRA_ID, data.id)
                    it.putExtra(PenyakitDetailActivity.EXTRA_NAME, data.name)
                    it.putExtra(PenyakitDetailActivity.EXTRA_DESCRIPTION, data.description)
                    startActivity(it)
                }
            }
        })
    }
}