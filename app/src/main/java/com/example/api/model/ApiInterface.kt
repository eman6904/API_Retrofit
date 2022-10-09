package com.example.api.model

import JsonToKotlinMain
import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiInterface {
    @GET("posts")
    //this function will return list of posts according to userid
    fun getPost(@Query("userId")userId:String):retrofit2.Call<List<JsonToKotlinMain>>
}