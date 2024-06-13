package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.FragmentFirstBinding
import kotlin.random.Random

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // установка менеджера(или в xml или в коде)
        binding.recycleView.layoutManager =
            GridLayoutManager(
                // spanCount кол-во столбцов в сетке
                requireContext(), 3, GridLayoutManager.VERTICAL, false
            )
        // или линейное расположение
//            LinearLayoutManager(
//                requireContext(), LinearLayoutManager.VERTICAL, false
//            )

        val data: List<String> = (0..100).map { it.toString() }
        val myAdapter = MySimpleAdapter(data)
        binding.recycleView.adapter = myAdapter

        binding.add.setOnClickListener {
            val item = Random.nextInt(100, 200).toString()
            myAdapter.addItem(5, item)
        }

        binding.remove.setOnClickListener {
            myAdapter.removeItem(1)
        }

        binding.set.setOnClickListener {
            val newData = List(100){
                Random.nextInt(0, 100).toString()
            }
            myAdapter.setData(newData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}