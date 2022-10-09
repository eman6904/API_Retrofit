package com.example.api.ui

import JsonToKotlinMain
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.api.MainActivity
import com.example.api.R
import com.example.api.databinding.PostDataUsingMapBinding
import com.example.api.model.ApiInterface
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDataUsingMap : Fragment(R.layout.post_data_using_map) {
    private lateinit var binding: PostDataUsingMapBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PostDataUsingMapBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        //send data to server by using map
        val map = HashMap<Any, Any>()
        map["title"] = "Android Studio"
        map["body"] = "Android Studio Welcome You"
        post(map)
    }

    private fun post(map: HashMap<Any, Any>) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<JsonToKotlinMain> = apiInterface.mapPost(map)

        call.enqueue(object : Callback<JsonToKotlinMain> {
            override fun onResponse(
                call: Call<JsonToKotlinMain>,
                response: Response<JsonToKotlinMain>
            ) {
                binding.text.text = response.body()?.body
            }
            override fun onFailure(call: Call<JsonToKotlinMain>, t: Throwable) {
                binding.text.text = t.message
            }
        })
    }
}