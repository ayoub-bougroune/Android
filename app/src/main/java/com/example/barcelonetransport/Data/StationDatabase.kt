package com.example.barcelonetransport.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nearstation::class,Images::class], version = 1, exportSchema = false)
abstract class StationDatabase: RoomDatabase() {
    abstract fun stationDao(): NearstationDAO
    abstract fun imagesDAO():ImagesDAO
    companion object {
        @Volatile
        private var INSTANCE: StationDatabase? = null
        fun getDatabase(context: Context): StationDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StationDatabase::class.java,
                        "station.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
