package com.capstonebangkit.plantdoc.ui.penyakit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PenyakitTanamanData
import com.capstonebangkit.plantdoc.databinding.ActivityPenyakitDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PenyakitDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPenyakitDetailBinding
    private lateinit var viewModel: PenyakitDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPenyakitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PenyakitDetailViewModel::class.java)

        val penyakitId = intent.getIntExtra("penyakitId", -1)
        val penyakit = PenyakitTanamanData.penyakit.find { it.id == penyakitId }
        if (penyakit != null) {
            val photoPenyakitImageView = findViewById<ImageView>(R.id.imageDetailPenyakit)
            val namePenyakitTextView = findViewById<TextView>(R.id.nameDetailPenyakit)
            val descriptionPenyakitTextView = findViewById<TextView>(R.id.descriptionDetailPenyakit)

            Picasso.get().load(penyakit.photoUrl).into(photoPenyakitImageView)
            namePenyakitTextView.text = penyakit.name
            descriptionPenyakitTextView.text = penyakit.description
        }

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkPenyakit(penyakitId)
            withContext(Dispatchers.Main){
                if(count != null){
                    if(count > 0){
                        binding.toogleBookmark.isChecked = true
                        _isChecked = true
                    }else{
                        binding.toogleBookmark.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toogleBookmark.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
                penyakit?.let {
                    viewModel.addToBookmark(it.id, it.name, it.description, it.photoUrl)
                }
            } else {
                penyakit?.let {
                    viewModel.removeFromBookmark(it.id)
                }
            }
            binding.toogleBookmark.isChecked = _isChecked
        }
    }

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
    }
}