package com.example.akhleshkumar.imagesassignment.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.akhleshkumar.imagesassignment.R
import com.example.akhleshkumar.imagesassignment.adapter.PhotosAdapter
import com.example.akhleshkumar.imagesassignment.databinding.ActivityMainBinding
import com.example.akhleshkumar.imagesassignment.viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var photosViewModel: PhotosViewModel
    lateinit var photosAdapter: PhotosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        photosViewModel = ViewModelProvider(this)[PhotosViewModel::class.java]
        binding.rvImages.layoutManager = GridLayoutManager(this,3,RecyclerView.VERTICAL,false)

        photosAdapter = PhotosAdapter()
        binding.rvImages.adapter = photosAdapter

        var page = 1

        getPageImage(page)
        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager =  recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastItem =  layoutManager.findLastVisibleItemPosition()
                if (totalItemCount<= lastItem+5){
                    page+=1
                    photosAdapter.photosList.clear()
                    if (page<=30) {
                        getPageImage(page)
                    }
                }
            }
        })
    }
    private fun getPageImage(pageNo:Int){
        photosAdapter.photosList.clear()
        photosViewModel.images(pageNo).observe(this){
            if (!it.isEmpty()){
                photosAdapter.setList(it)

                binding.rvImages.adapter = photosAdapter
                photosAdapter.notifyDataSetChanged()
            }

        }

    }
}