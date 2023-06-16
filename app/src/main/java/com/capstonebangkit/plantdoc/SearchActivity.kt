package com.capstonebangkit.plantdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SearchActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            performSearch(query)
        }
    }

    private fun performSearch(query: String) {
        // Perform your search logic here based on the entered query
        // You can query a database, make API requests, or search local data
        // Retrieve the search results and update the UI accordingly
    }
}