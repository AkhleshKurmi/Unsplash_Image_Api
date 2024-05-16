package com.example.akhleshkumar.imagesassignment.retroit

import com.example.akhleshkumar.imagesassignment.api_methodes.PhotosApiMethods
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofitClient : Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
    }

    val ApiCall : PhotosApiMethods by lazy {
        retrofitClient.build().create(PhotosApiMethods::class.java)
    }
}