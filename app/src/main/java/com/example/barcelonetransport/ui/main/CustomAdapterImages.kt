package com.example.barcelonetransport.ui.main

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.barcelonetransport.Data.Images
import com.example.barcelonetransport.MainActivity
import com.example.barcelonetransport.R
import kotlinx.android.synthetic.main.take_pic.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * Created by Parsania Hardik on 03-Jan-17.
 */
@Suppress("DEPRECATION")
class CustomAdapterImages(val context: Context, private val imageModelArrayList: ArrayList<Images>) : BaseAdapter() {
    private lateinit var viewModel: ImagesViewModel
    init {
        viewModel = ViewModelProviders.of(context as FragmentActivity).get(ImagesViewModel::class.java)
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return imageModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_image, null, true)

            holder.titre = convertView!!.findViewById(R.id.titre) as TextView
            holder.date = convertView!!.findViewById(R.id.date) as TextView
            holder.photo = convertView.findViewById(R.id.photo) as ImageView
            holder.delete = convertView.findViewById(R.id.delete) as Button

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }
        val main = context as MainActivity
        holder.delete?.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteImage(imageModelArrayList[position].id)

            }
        }
        holder.titre!!.setText(imageModelArrayList[position].titre)
        holder.date!!.setText(imageModelArrayList[position].dateImg)
        holder.photo!!.setImageURI(Uri.parse(imageModelArrayList[position].imgFile))

        return convertView
    }

    private inner class ViewHolder {

        var titre: TextView? = null
        var delete: Button? = null
        var date: TextView? = null
        internal var photo: ImageView? = null

    }

}