package com.example.akhleshkumar.imagesassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akhleshkumar.imagesassignment.models.PhotosModel
import com.example.akhleshkumar.imagesassignment.repository.PhotosRepository

class PhotosViewModel :ViewModel() {
    val repository = PhotosRepository()

    fun images(page_number:Int):MutableLiveData<ArrayList<PhotosModel>>{
        return repository.getImages(pageNo = page_number)
    }
}