@file:Suppress("DEPRECATION")

package com.example.barcelonetransport.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.barcelonetransport.Data.Images
import com.example.barcelonetransport.Data.Nearstation
import com.example.barcelonetransport.MainActivity
import com.example.barcelonetransport.R
import kotlinx.android.synthetic.main.list_images.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.ArrayList

class FragmentImages : Fragment(){

    companion object {
        private const val STATION = "model"
        fun newInstance(nearstation: Nearstation): FragmentImages {
            val args = Bundle()
            args.putSerializable(STATION, nearstation)
            val fragment = FragmentImages()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: ImagesViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        val model = arguments!!.getSerializable(STATION) as Nearstation
        val view = inflater.inflate(R.layout.list_images,container,false)

        val button_add =  view.findViewById<Button>(R.id.add_photo)
        val main = context as MainActivity
        button_add.setOnClickListener { main.onClickButtonAddPhoto(model) }
        viewModel.getImagesByIdStation(model.id).observe(this, Observer {
            val list = it as ArrayList<Images>
            val lv = view.findViewById(R.id.list) as ListView
            val activity = activity as Context
            val customeAdapter = CustomAdapterImages(activity, list!!)
            lv!!.adapter = customeAdapter
        })


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


 }