package com.example.memory

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

open class MyFragment: Fragment(){

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args =
            arguments?.getParcelable<Person>("Person", Person::class.java)

        args?.placeWork
    }
}