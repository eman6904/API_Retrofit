package com.example.api.ui

import JsonToKotlinMain
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.api.MainActivity
import com.example.api.R
import com.example.api.databinding.PostDataUsingObjectBinding
import com.example.api.model.ApiInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PostDataUsingObject : Fragment(R.layout.post_data_using_object) {
    private lateinit var binding: PostDataUsingObjectBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PostDataUsingObjectBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        //send data to server by using object from data class
        val post = JsonToKotlinMain(12, "Eman Nasser", "This Is My First Post")
        post(post)
        binding.json.setOnClickListener {
            navController.navigate(R.id.action_retrofitUsing_to_simpleJson)
        }
    }

  private fun post(obj: JsonToKotlinMain) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<JsonToKotlinMain> = apiInterface.objPost(obj)
        call.enqueue(object : Callback<JsonToKotlinMain> {
            override fun onResponse(
                call: Call<JsonToKotlinMain>,
                response: Response<JsonToKotlinMain>
            ) {
                binding.textView.text = response.body()?.body
            }
            override fun onFailure(call: Call<JsonToKotlinMain>, t: Throwable) {
                binding.textView.text = t.message
            }
        })
    }
}