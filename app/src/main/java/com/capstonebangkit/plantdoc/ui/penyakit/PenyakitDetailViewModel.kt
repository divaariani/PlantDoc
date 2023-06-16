package com.capstonebangkit.plantdoc.ui.penyakit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.capstonebangkit.plantdoc.data.PenyakitTanaman
import com.capstonebangkit.plantdoc.data.PenyakitTanamanDao
import com.capstonebangkit.plantdoc.data.PenyakitTanamanDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PenyakitDetailViewModel(application: Application): AndroidViewModel(application) {

    private var penyakitDao : PenyakitTanamanDao?
    private var penyakitDb : PenyakitTanamanDatabase?

    init{
        penyakitDb = PenyakitTanamanDatabase.getDatabase(application)
        penyakitDao = penyakitDb?.penyakitTanamanDao()
    }

    fun addToBookmark(id: Int, name: String, description: String, photoUrl: String){
        CoroutineScope(Dispatchers.IO).launch{
            var penyakit = PenyakitTanaman(
                id,
                name,
                description,
                photoUrl
            )
            penyakitDao?.addToBookmark(penyakit)
        }
    }

    suspend fun checkPenyakit(id: Int) = penyakitDao?.checkPenyakitTanaman(id)

    fun removeFromBookmark(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            penyakitDao?.removeFromBookmark(id)
        }
    }
}