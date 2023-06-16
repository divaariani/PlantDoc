package com.capstonebangkit.plantdoc.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PenyakitTanamanDao {
    @Insert
    suspend fun addToBookmark(penyakitTanaman: PenyakitTanaman)

    @Query("SELECT * FROM bookmark_penyakit")
    fun getPenyakitTanaman(): LiveData<List<PenyakitTanaman>>

    @Query("SELECT count(*) FROM bookmark_penyakit WHERE bookmark_penyakit.id = :id")
    suspend fun checkPenyakitTanaman(id: Int): Int

    @Query("DELETE FROM bookmark_penyakit WHERE bookmark_penyakit.id = :id")
    suspend fun removeFromBookmark(id: Int): Int
}