package com.example.practico3.data

data class Lugar(
    val id:Int?,
    val name:String,
    val city:String,
    val description:String?,
    val image_url:String?,
    val created_at:String?,
    val updated_at:String?,
    val directions:String?,
    val time_to_spend:String?,
    val price:String?,
    val trip_id:Int
)
