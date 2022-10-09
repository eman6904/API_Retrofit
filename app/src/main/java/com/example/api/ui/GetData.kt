package com.example.api.ui

import JsonToKotlinMain
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.api.MainActivity
import com.example.api.R
import com.example.api.databinding.GetDataBinding
import com.example.api.model.ApiInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class GetData : Fragment(R.layout.get_data) {
    private lateinit var binding: GetDataBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GetDataBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<JsonToKotlinMain> = apiInterface.getPost()

        call.enqueue(object : Callback<JsonToKotlinMain> {
            override fun onResponse(
                call: Call<JsonToKotlinMain>,
                response: Response<JsonToKotlinMain>
            ) {
                binding.textView.text = response.body()?.title
            }

            override fun onFailure(call: Call<JsonToKotlinMain>, t: Throwable) {
                binding.textView.text = t.message
            }
        })
        binding.json.setOnClickListener {
            navController.navigate(R.id.action_retrofitUsing_to_simpleJson)
        }
    }
}