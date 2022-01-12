package com.example.barcelonetransport.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.barcelonetransport.Data.Images
import com.example.barcelonetransport.Data.ImagesRepository
import com.example.barcelonetransport.Data.StationRepository

class ImagesViewModel(app: Application) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private val imageRepo = ImagesRepository(app)

    fun getImagesByIdStation(idStation:String): LiveData<List<Images>>{
        return imageRepo.getImagesByIdStation(idStation)
    }
    suspend fun insert(images: Images){
        imageRepo.insertImage(images)
    }

    suspend fun deleteImage(id:Long){
        imageRepo.deleteImage(id)
    }


}