package com.example.example2

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.example2.FlowerObject.flowerList

class SecondFragment : Fragment(R.layout.fragment_second) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // переход анимации с первого фрагмента на второй
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            /*TransitionInflater отвечает за анимации из xml
            requireContext() - текущий контекст
            R.transition.grid_transition ресурс анимации*/
            .inflateTransition(R.transition.grid_transition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.title)
        val image = view.findViewById<ImageView>(R.id.imageView)

        val flowerId = arguments?.getInt(ID, 0) ?: 0
        title.text = flowerList[flowerId].text

        image.setImageDrawable(
            ContextCompat.getDrawable(requireContext(), flowerList[flowerId].img)
        )
    }

    companion object {
        private const val ID = "ID"

        fun newInstance(id: Int): SecondFragment {
            val args = Bundle()
            args.putInt(ID, id)
            val fragment = SecondFragment()
            fragment.arguments = args
            return fragment
        }
    }
}