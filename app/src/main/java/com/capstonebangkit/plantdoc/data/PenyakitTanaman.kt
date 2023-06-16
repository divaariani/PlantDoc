package com.capstonebangkit.plantdoc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bookmark_penyakit")
data class PenyakitTanaman(
    @PrimaryKey
    val id: Int,
    val photoUrl: String,
    val name: String,
    val description: String,
): Serializable