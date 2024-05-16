package com.example.akhleshkumar.imagesassignment.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.akhleshkumar.imagesassignment.R
import com.example.akhleshkumar.imagesassignment.databinding.CustomRvImagesBinding
import com.example.akhleshkumar.imagesassignment.models.PhotosModel

import java.net.URL


class PhotosAdapter() : Adapter<PhotosAdapter.PhotosViewHolder>() {
    var photosList : ArrayList<PhotosModel> = ArrayList()

    private val cache: LruCache<String, Bitmap>
    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt() // Use 1/8th of available memory for this memory cache.
        val cacheSize = maxMemory / 8

        cache = LruCache(cacheSize)
    }

    fun setList(list:ArrayList<PhotosModel>){
        photosList.clear()
        photosList.addAll(list)
        notifyDataSetChanged()
    }





    inner class PhotosViewHolder(val binding: CustomRvImagesBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = CustomRvImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotosViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return photosList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photoUrl = photosList[position].urls.raw
        val cachedBitmap = cache.get(photoUrl)
        if (cachedBitmap != null) {
            holder.binding.imageView.setImageBitmap(cachedBitmap)
        } else {
            holder.binding.imageView.setImageResource(R.drawable.place_holder) // Set a placeholder image
            loadBitmap(photoUrl, holder.binding.imageView)
        }
    }


    private val loadingThreads: HashMap<String, Thread> = HashMap()

    private fun loadBitmap(photoUrl: String, imageView: ImageView) {
        loadingThreads[photoUrl]?.interrupt() // Interrupt if already loading this URL

        val thread = Thread {
            try {
                if (Thread.interrupted()) return@Thread

                val inputStream = URL(photoUrl).openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                cache.put(photoUrl, bitmap)
                imageView.post {
                    if (!Thread.interrupted()) {
                        imageView.setImageBitmap(bitmap)
                    }
                }
            } catch (e: Exception) {
                if (e is InterruptedException) return@Thread
                e.printStackTrace()
            } finally {
                synchronized(loadingThreads) {
                    loadingThreads.remove(photoUrl)
                }
            }
        }
        thread.start()

        synchronized(loadingThreads) {
            loadingThreads[photoUrl] = thread
        }
    }

}
