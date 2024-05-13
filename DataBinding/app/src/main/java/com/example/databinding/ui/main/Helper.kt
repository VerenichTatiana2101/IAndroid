package com.example.databinding.ui.main

import android.content.Context
import android.view.View
import android.widget.Toast

object Helper {
    @JvmStatic
    fun onClick(c: Context) {
        Toast.makeText(c, "onClick", Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun onTextChanged(v: View, string: String) {
        Toast.makeText(v.context, "onTextChanged $string", Toast.LENGTH_SHORT).show()

    }

    @JvmStatic
    fun onChecked(v: View, checked: Boolean){
        Toast.makeText(v.context, "onChecked $checked", Toast.LENGTH_SHORT).show()
    }
}