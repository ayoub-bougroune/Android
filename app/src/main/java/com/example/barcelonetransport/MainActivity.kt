package com.example.barcelonetransport

//import com.example.barcelonetransport.ui.main.FragmentAddImages

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.barcelonetransport.Data.Nearstation
import com.example.barcelonetransport.ui.main.FragmentAddImages
import com.example.barcelonetransport.ui.main.FragmentImages
import com.example.barcelonetransport.ui.main.FragmentList
import com.example.barcelonetransport.ui.main.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), FragmentList.OnStationSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPageAdapter =
            ViewPageAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.viewpage_id)
        viewPager.adapter = viewPageAdapter
        val tabs: TabLayout = findViewById(R.id.tablayaut_id)
        tabs.setupWithViewPager(viewPager)
        tabs.setOnTabSelectedListener(
            object : ViewPagerOnTabSelectedListener(viewPager) {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    super.onTabSelected(tab)
                    var numTab = tab.position
                    if(numTab == 1){
                        val intent = Intent(this@MainActivity, MapsActivity::class.java)
                        startActivity(intent)
                    }
                }
            })

    }
    public  fun test(){

    }
    override fun onStationSelected(nearstation: Nearstation) {
        setContentView(R.layout.activity_main_details)
        val imagesFragment =
            FragmentImages.newInstance(nearstation)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, imagesFragment, "Station")
            .addToBackStack(null)
            .commit()
    }

    fun onClickButtonAddPhoto(nearstation: Nearstation){
        setContentView(R.layout.activity_main_details)
        val addImagesFragment =
            FragmentAddImages.newInstance(nearstation)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, addImagesFragment, "Station")
            .addToBackStack(null)
            .commit()
    }


   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){
            R.id.Home_id->{
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.siting_id->{
                val intent = Intent(this@MainActivity, Setting::class.java)
                startActivity(intent)
            }
            R.id.map_id->{
                val intent = Intent(this@MainActivity, MapsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


