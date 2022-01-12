package com.example.barcelonetransport.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "images")
 data class Images (
    val dateImg: String,
    var titre: String,
    val imgFile :String,
    val idStation: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
): Serializable