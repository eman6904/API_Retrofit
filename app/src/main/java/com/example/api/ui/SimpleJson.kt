package com.example.api.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.api.MainActivity
import com.example.api.R
import com.example.api.databinding.FragmentSimpleJsonBinding
import org.json.JSONArray
import org.json.JSONObject

class SimpleJson : Fragment(R.layout.fragment_simple_json) {
    private lateinit var binding: FragmentSimpleJsonBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSimpleJsonBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        val ob = JSONObject()
        val arr = JSONArray()

        val ob1 = JSONObject()
        val ob2 = JSONObject()

        ob1.put("name", "eman")
        ob1.put("id", 12334)
        ob1.put("age", 20)

        ob2.put("name", "amira")
        ob2.put("id", 67801)
        ob2.put("age", 24)

        arr.put(ob1)
        arr.put(ob2)

        ob.put("arr", arr)

        binding.text.text = ob.toString()
    }
}