package com.example.api.model

import JsonToKotlinMain
import android.telecom.Call
import retrofit2.http.GET

public interface ApiInterface {
    @GET("posts/1")
    fun getPost(): retrofit2.Call<JsonToKotlinMain>
}