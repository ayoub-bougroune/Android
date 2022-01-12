package com.example.barcelonetransport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.barcelonetransport.Data.StationService
import com.example.barcelonetransport.Data.Result

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val retrofit = Retrofit.Builder()
            .baseUrl("http://barcelonaapi.marcpous.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val api = retrofit.create(StationService::class.java)
        api.getBusDataForMap().enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: retrofit2.Response<Result>) {

                val streetForZoom = LatLng(response.body()!!.data.nearstations!![0].lat.toDouble(), response.body()!!.data.nearstations!![0].lon.toDouble())
                val tailleTableau = response.body()!!.data.nearstations!!.size -1

                for(i in 0..tailleTableau){
                    val street = LatLng(response.body()!!.data.nearstations!![i].lat.toDouble(), response.body()!!.data.nearstations!![i].lon.toDouble())
                    val zoomLevel = 15.0f
                    mMap.addMarker(MarkerOptions().position(street).title("Marker in ${response.body()!!.data.nearstations!![i].street_name}"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(streetForZoom, zoomLevel))
                }

            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){
            R.id.Home_id->{
                val intent = Intent(this@MapsActivity, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.siting_id->{
                val intent = Intent(this@MapsActivity, Setting::class.java)
                startActivity(intent)
            }
            R.id.map_id->{
                val intent = Intent(this@MapsActivity, MapsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
