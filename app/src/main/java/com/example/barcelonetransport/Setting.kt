package com.example.barcelonetransport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Setting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){
            R.id.Home_id->{
                val intent = Intent(this@Setting, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.siting_id->{
                val intent = Intent(this@Setting, Setting::class.java)
                startActivity(intent)
            }
            R.id.map_id->{
                val intent = Intent(this@Setting, MapsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
