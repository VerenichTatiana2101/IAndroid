package com.example.example2


import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example2.FlowerObject.flowerList


class FirstFragment : Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rc = view.findViewById<RecyclerView>(R.id.rcv)
        rc.layoutManager = LinearLayoutManager(requireContext())
        rc.adapter = MyRecyclerView(itemOnClick = { imageView, textView, id ->
            (activity as MainActivity).showSecondFragment(imageView, textView, id)
        })
    }

    data class It(val text: String, @DrawableRes val img: Int)

    inner class MyRecyclerView(private val itemOnClick: (ImageView, TextView, Int) -> Unit) :
        RecyclerView.Adapter<MyRecyclerView.MyRecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
            return MyRecyclerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false),
                itemOnClick
            )
        }

        override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
            holder.bing(flowerList[position])
        }

        override fun getItemCount(): Int {
            return flowerList.size
        }

        inner class MyRecyclerViewHolder(
            private val view: View,
            private val itemOnClick: (ImageView, TextView, Int) -> Unit
        ) : RecyclerView.ViewHolder(view) {
            fun bing(item: It) {

                val textView = view.findViewById<TextView>(R.id.title)
                val imageView = view.findViewById<ImageView>(R.id.imageView)

                textView.text = item.text
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(view.context, item.img)
                )

                view.setOnClickListener {
                    // назначение общих элементов для анимации
                    imageView.transitionName = getString(R.string.image_transition_name)
                    textView.transitionName = getString(R.string.text_transition_name)
                    // передаём эти view для activity
                    itemOnClick.invoke(imageView, textView, adapterPosition)
                }
            }
        }
    }
}