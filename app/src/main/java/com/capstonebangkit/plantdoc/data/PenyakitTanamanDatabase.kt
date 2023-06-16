package com.capstonebangkit.plantdoc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PenyakitTanaman::class],
    version = 1
)
abstract class PenyakitTanamanDatabase: RoomDatabase() {
    companion object{
        var INSTANCE : PenyakitTanamanDatabase? = null

        fun getDatabase(context: Context): PenyakitTanamanDatabase?{
            if (INSTANCE==null){
                synchronized(PenyakitTanamanDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, PenyakitTanamanDatabase::class.java, "penyakit_database").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun penyakitTanamanDao(): PenyakitTanamanDao
}