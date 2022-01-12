package com.example.barcelonetransport.Data

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepository(val app: Application) {

        private val ImagesDAO = StationDatabase.getDatabase(app)
        .imagesDAO()

    fun getImagesByIdStation(idStation: String):LiveData<List<Images>> = ImagesDAO.getImagesByIdStation(idStation)


    suspend fun insertImage(images: Images) {
        withContext(Dispatchers.IO){
            ImagesDAO?.insertimages(images)
        }
        }
    suspend fun deleteImage(id:Long) {
        withContext(Dispatchers.IO){
            ImagesDAO?.deleteById(id)
        }
    }
}

