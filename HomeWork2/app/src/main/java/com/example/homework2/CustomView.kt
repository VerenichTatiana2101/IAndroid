package com.example.homework2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.homework2.databinding.PostContentLayoutBinding

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {

    private val binding = PostContentLayoutBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setAddToBag(text: String) {
        binding.addToBag.text = text
    }

    fun setPrice(text: String) {
        binding.price.text = text
    }

}

