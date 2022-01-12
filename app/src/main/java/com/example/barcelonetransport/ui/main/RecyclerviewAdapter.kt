package com.example.barcelonetransport.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barcelonetransport.Data.Nearstation
import com.example.barcelonetransport.Data.Result
import com.example.barcelonetransport.MainActivity
import com.example.barcelonetransport.R

class RecyclerviewAdapter(
    val context: Context,
    val nearstations: List<Nearstation>
) :
    RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>()
   {
       override fun onCreateViewHolder(
           parent: ViewGroup,
           viewType: Int
       ): RecyclerviewAdapter.ViewHolder {
           val inflater = LayoutInflater.from(parent.context)
           val view = inflater.inflate(R.layout.item_station, parent, false)
           return ViewHolder(view)
       }

       override fun getItemCount() : Int {
           return nearstations.size
       }

       override fun onBindViewHolder(holder: RecyclerviewAdapter.ViewHolder, position: Int) {

           val Nearstation = nearstations[position]
            val main = context as MainActivity
           holder.itemView.setOnClickListener { main.onStationSelected(Nearstation) }
           with(holder) {
               NameStreetText?.let {
                   it.text = Nearstation.street_name
               }
           }

           with(holder) {
               BusNumber?.let {
                   it.text = Nearstation.buses
               }
           }
       }

       inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

           val NameStreetText = itemView.findViewById<TextView>(R.id.STRNM_id)

           val BusNumber = itemView.findViewById<TextView>(R.id.buses_number)

       }

   }