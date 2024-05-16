package com.example.akhleshkumar.imagesassignment.api_methodes

import com.example.akhleshkumar.imagesassignment.models.PhotosModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhotosApiMethods {
    @GET("photos")

    fun getImages(@Query("client_id") key:String, @Query("page")pages:Int, @Query("per_page") perPage:Int, @Query("w")width:Int, @Query("h")height :Int): Call<ArrayList<PhotosModel>>
}