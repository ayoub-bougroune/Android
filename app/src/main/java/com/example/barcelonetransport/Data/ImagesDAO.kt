package com.example.barcelonetransport.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
@Dao
interface ImagesDAO {
    @Transaction
    @Query("SELECT * FROM images")
    fun getAll(): List<Images>
    @Query("SELECT * FROM images where idStation = :idStation")
    fun getImagesByIdStation(idStation :String): LiveData<List<Images>>
    @Insert
    suspend fun insertimages(images: Images)
    @Insert
    suspend fun insertimagess(imagess: List<Images>)
    @Query("DELETE from images")
    suspend fun deleteAll()

    @Query("DELETE from images where id =:idImage")
    suspend fun deleteById(idImage:Long)
}