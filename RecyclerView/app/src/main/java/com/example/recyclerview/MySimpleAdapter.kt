package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.MySimpleListItemBinding

class MySimpleAdapter(
    values: List<String>
) : RecyclerView.Adapter<MySimpleViewHolder>() {
    private var values = values.toMutableList()
    //вызывается когда нужно создать view для элемента
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySimpleViewHolder {
        val binding = MySimpleListItemBinding
            .inflate(LayoutInflater.from(parent.context))
        return MySimpleViewHolder(binding)
    }

    // вызывается когда нужно установить данные во view
    /*
    * получить нужный элемент из массива данных
    * и установить его во view
    * передаем то что нужно установить и куда
    */
    override fun onBindViewHolder(holder: MySimpleViewHolder, position: Int) {
        val item = values[position]
        holder.binding.testField.text = item


    }

    // количество данных для отображения
    override fun getItemCount(): Int = values.size

    /* метод изменения набора данных
    обновит данные в адаптере присвоив новый список
    и сообщит о необходимости перерисовать список
     */
    fun setData(values: List<String>){
        this.values = values.toMutableList()
        notifyDataSetChanged() // тяжелая операция, применять при необходимости изменения всего набора
    }

    // метод добавления в набор данных адаптера
    fun addItem(index: Int = 0, value: String){
        values.add(index, value)
        notifyItemInserted(index) // сообщает о необходимости изменения
    }

    // метод удаления из набора данных адаптера
    fun removeItem(index: Int = 0){
        values.removeAt(index)
        notifyItemRemoved(index) // сообщает о необходимости изменения
    }

}

class MySimpleViewHolder(val binding: MySimpleListItemBinding):
    RecyclerView.ViewHolder(binding.root)