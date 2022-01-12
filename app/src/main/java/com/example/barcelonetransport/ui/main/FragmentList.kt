@file:Suppress("DEPRECATION")

package com.example.barcelonetransport.ui.main

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.barcelonetransport.Data.Nearstation
import com.example.barcelonetransport.MainActivity
import com.example.barcelonetransport.R

class FragmentList : Fragment() {
    companion object {
        fun newInstance() = FragmentList()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.list_page,container,false)
        recyclerView = view.findViewById(R.id.recyclerview_id)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val activity = activity as Context
        viewModel.busData.observe(this, Observer
        {
            val adapter = RecyclerviewAdapter(activity, it as List<Nearstation>)
            recyclerView.adapter = adapter
        })

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    interface OnStationSelected {
        fun onStationSelected(nearstation: Nearstation)
    }

 }