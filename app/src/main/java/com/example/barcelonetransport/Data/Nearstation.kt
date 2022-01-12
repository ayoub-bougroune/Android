package com.example.barcelonetransport.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "nearstations")
data class Nearstation(
    @PrimaryKey
    val id: String,
    val buses: String,
    val city: String,
    val distance: String,
    val furniture: String,

    val lat: String,
    val lon: String,
    val street_name: String,
    val utm_x: String,
    val utm_y: String
): Serializable