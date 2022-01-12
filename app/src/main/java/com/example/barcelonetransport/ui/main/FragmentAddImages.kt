@file:Suppress("DEPRECATION")

package com.example.barcelonetransport.ui.main

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Insert
import com.example.barcelonetransport.Data.Images
import com.example.barcelonetransport.Data.Nearstation
import com.example.barcelonetransport.MainActivity
import com.example.barcelonetransport.R
import kotlinx.android.synthetic.main.take_pic.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.jar.Manifest

class FragmentAddImages : Fragment(){

    companion object {
        private const val STATION = "model"
        fun newInstance(nearstation: Nearstation): FragmentAddImages {
            val args = Bundle()
            args.putSerializable(STATION, nearstation)
            val fragment = FragmentAddImages()
            fragment.arguments = args
            return fragment
        }
    }
    private lateinit var viewModel: ImagesViewModel
    private val PERMISSION_CODE: Int = 1000
    private val IMAGE_CAPTURE_CODE: Int = 1001
    var image_uri : Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        val view = inflater.inflate(R.layout.take_pic,container,false)

        val takePhoto = view.findViewById(R.id.take_photo) as Button

        takePhoto.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ActivityCompat.checkSelfPermission(activity as Context,
                        android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                    || ActivityCompat.checkSelfPermission(activity as Context,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    //Permission denied
                    val permisssions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permisssions, PERMISSION_CODE)

                }else{
                    //Permission already granted
                    open_camera()
                }
            }else{
                open_camera()
            }
        }


        return view
    }

    private fun open_camera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "new")
        values.put(MediaStore.Images.Media.DESCRIPTION, "new")
        image_uri = getActivity()?.getContentResolver()?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onRequestPermissionsResult(requestCode :Int,
                                            permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission ok
                    open_camera()
                } else {
                    //permission ko
                    Toast.makeText(context, "permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            image_view.setImageURI(image_uri)
           //Insert images
            var dateCreation = ""
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                 dateCreation =  current.format(formatter)
            } else {
                var date = Date();
                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
                 dateCreation = formatter.format(date)
            }
            val model = arguments!!.getSerializable(STATION) as Nearstation
          CoroutineScope(Dispatchers.IO).launch {

           viewModel.insert(Images(dateCreation,titre.text.toString(),image_uri.toString(),model.id) )

            }

        }

    }


}