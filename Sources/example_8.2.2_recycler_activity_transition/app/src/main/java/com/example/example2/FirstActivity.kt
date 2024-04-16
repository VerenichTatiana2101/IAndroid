package com.example.example2

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example2.FlowerObject.flowerList


class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val rc = findViewById<RecyclerView>(R.id.rcv)
        rc.layoutManager = LinearLayoutManager(this)
        rc.adapter = MyRecyclerView()
    }

    data class It(val text: String, @DrawableRes val img: Int)


    inner class MyRecyclerView : RecyclerView.Adapter<MyRecyclerView.MyRecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
            return MyRecyclerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
            // список цветов
            holder.bing(flowerList[position])
        }

        override fun getItemCount(): Int {
            return flowerList.size
        }

        inner class MyRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            fun bing(item: It) {

                val textView = view.findViewById<TextView>(R.id.title)
                val imageView = view.findViewById<ImageView>(R.id.imageView)

                textView.text = item.text
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(view.context, item.img)
                )

                view.setOnClickListener {
                    val intent = Intent(this@FirstActivity, SecondActivity::class.java).apply {
                        // передаём id чтобы передать его в новом активити
                            putExtra("ID", adapterPosition)
                        }

                    // создём options
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                        this@FirstActivity,
                        // пары общих элементов
                        /*тексторовое view из файла шаблонной разметки, transitions name*/
                        Pair.create(textView, getString(R.string.text_transition_name)),
                        /*image view из файла шаблонной разметки, transitions name*/
                        Pair.create(imageView, getString(R.string.image_transition_name))
                    )
                    // передаём его при старте activity
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }
}