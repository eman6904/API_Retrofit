package com.example.api.model

import JsonToKotlinMain
import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

public interface ApiInterface {
    @POST("posts")
    fun mapPost(@Body map:HashMap<Any,Any>):retrofit2.Call<JsonToKotlinMain>
    @POST("posts")
    fun objPost(@Body post:JsonToKotlinMain):retrofit2.Call<JsonToKotlinMain>
}