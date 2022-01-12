package com.example.barcelonetransport.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface    NearstationDAO {
    @Query("SELECT * from nearstations")
    fun getAll(): List<Nearstation>
    @Insert
    suspend fun insertNearstation(nearstation: Nearstation)
    @Insert
    suspend fun insertNeartations(nearstations: List<Nearstation>)
    @Query("DELETE from nearstations")
    suspend fun deleteAll()
}
