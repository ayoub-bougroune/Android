package com.example.barcelonetransport.Data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.barcelonetransport.WEB_SERVICE_BUS_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StationRepository(val app: Application) {

    val busData = MutableLiveData<List<Nearstation>>()
    private val stationDao = StationDatabase.getDatabase(app)
        .stationDao()
    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebServiceBus()

        }
    }

    @WorkerThread
    suspend fun callWebServiceBus() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_BUS_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(StationService::class.java)
            val serviceData = service.getBusData().body() ?: null
            stationDao.deleteAll()
            stationDao.insertNeartations(serviceData?.data?.nearstations?: emptyList())
            busData.postValue(stationDao.getAll())
        }
    }

    @SuppressLint("ServiceCast")
    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }


}