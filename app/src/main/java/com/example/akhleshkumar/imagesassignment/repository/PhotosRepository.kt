package com.example.akhleshkumar.imagesassignment.repository

import androidx.lifecycle.MutableLiveData
import com.example.akhleshkumar.imagesassignment.models.PhotosModel
import com.example.akhleshkumar.imagesassignment.retroit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRepository {

    var imageList: MutableLiveData<ArrayList<PhotosModel>> = MutableLiveData()

    val api_key = "zKZ6jZ1_qJ6OTQ62clJyYD-enJEVyr7hRqTAyC2gyCM"
    fun getImages(pageNo:Int):MutableLiveData<ArrayList<PhotosModel>>{
        val call = RetrofitClient.ApiCall.getImages(key = api_key, pages =pageNo , perPage = 30, width = 300, height = 300)
        call.enqueue(object : Callback<ArrayList<PhotosModel>>{
            override fun onResponse(
                call: Call<ArrayList<PhotosModel>>,
                response: Response<ArrayList<PhotosModel>>
            ) {
                if (response.isSuccessful){
                    imageList.value = response.body()
                }

            }

            override fun onFailure(call: Call<ArrayList<PhotosModel>>, t: Throwable) {
            }
        })
        return imageList
    }
}