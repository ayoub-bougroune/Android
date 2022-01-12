package com.example.barcelonetransport.Data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface StationService {

    @GET("/bus/nearstation/latlon/41.3985182/2.1917991/1.json")
     suspend fun getBusData(): Response<Result>

    @GET("/bus/nearstation/latlon/41.3985182/2.1917991/1.json")
    fun getBusDataForMap(): Call<Result>
}