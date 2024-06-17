package com.skillbox.a017_recyclerview.pagedmovielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.recyclerviewnet.databinding.MovieItemBinding
import com.example.recyclerviewnet.models.Movie
import com.example.recyclerviewnet.movielist.MovieViewHolder

class MoviePagedListAdapter(
    // передадим элемент для дальнейшей обработки через метод
    private val onClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MovieViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            title.text = item?.nameRu ?: ""
            genres.text = item?.genres?.joinToString(", ") { it.genre }
            description.text = "Премьера ${item?.premiereRu}"
            countries.text = item?.countries?.joinToString(", ") { it.country }
            item?.let {
                Glide
                    .with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
        // обработка нажатия на элемент списка
        holder.binding.root.setOnClickListener {
            // передаём элемент данных на который нажали
            item?.let {
                onClick(item) // получили в первой строке метода
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.kinopoiskId == newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}
