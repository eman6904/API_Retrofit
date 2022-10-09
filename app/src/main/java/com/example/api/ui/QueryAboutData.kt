package com.example.api.ui

import JsonToKotlinMain
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.api.MainActivity
import com.example.api.R
import com.example.api.databinding.QueryAboutDataBinding
import com.example.api.model.ApiInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class QueryAboutData : Fragment(R.layout.query_about_data) {
    private lateinit var binding: QueryAboutDataBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QueryAboutDataBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
        //here function=>getPost will return list of posts that their userid=1
        val call: Call<List<JsonToKotlinMain>> = apiInterface.getPost("1")
        call.enqueue(object : Callback<List<JsonToKotlinMain>> {
            override fun onResponse(
                call: Call<List<JsonToKotlinMain>>,
                response: Response<List<JsonToKotlinMain>>
            ) {
                val postNumber = 0
                binding.textView.text = response.body()?.get(postNumber)?.title.toString()
                //will print title of first post from all posts that their userid=1 that function return
            }

            override fun onFailure(call: Call<List<JsonToKotlinMain>>, t: Throwable) {
                binding.textView.text = t.message
            }
        })
        binding.json.setOnClickListener {
            navController.navigate(R.id.action_retrofitUsing_to_simpleJson)
        }
    }
}