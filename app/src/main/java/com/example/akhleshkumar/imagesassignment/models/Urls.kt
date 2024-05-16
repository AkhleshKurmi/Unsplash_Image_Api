package com.example.akhleshkumar.imagesassignment.models

import kotlinx.serialization.Serializable

@Serializable
data class Urls(
val raw: String,
val full: String,
val regular: String,
val small: String,
val thumb: String,
val small_s3: String
)
