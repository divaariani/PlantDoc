package com.capstonebangkit.plantdoc.ui.pupuk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PupukData
import com.squareup.picasso.Picasso

class PupukDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pupuk_detail)

        val pupukId = intent.getIntExtra("pupukId", -1)

        val pupuk = PupukData.pupuk.find { it.id == pupukId }
        if (pupuk != null) {
            val photoPupukImageView = findViewById<ImageView>(R.id.photoPupuk)
            val namePupukTextView = findViewById<TextView>(R.id.namePupuk)
            val specificationPupukTextView = findViewById<TextView>(R.id.specificationPupuk)
            val benefitPupukTextView = findViewById<TextView>(R.id.benefitPupuk)

            Picasso.get().load(pupuk.photoUrl).into(photoPupukImageView)
            namePupukTextView.text = pupuk.name
            specificationPupukTextView.text = pupuk.getFormattedSpecification()
            benefitPupukTextView.text = pupuk.getFormattedBenefit()
        }
    }
}