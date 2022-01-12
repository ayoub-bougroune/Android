package com.example.barcelonetransport.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.barcelonetransport.Data.StationRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private val dataRepo = StationRepository(app)
    val busData = dataRepo.busData
}