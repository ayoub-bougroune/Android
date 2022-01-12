package com.example.barcelonetransport.ui.main

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.barcelonetransport.MainActivity
import com.example.barcelonetransport.MapsActivity
import com.example.barcelonetransport.R


@Suppress("DEPRECATION")
class ViewPageAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {


    private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
    )

    override fun getItem(position: Int): Fragment {
           when(position){
               0 -> {
                   return FragmentList.newInstance()
               }
               1 -> {


                   return FragmentMap()
               }
               else -> return  FragmentList.newInstance()

           }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Buses"

            1 -> return "MAP"

        }
        return null
    }


}