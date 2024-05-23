package com.example.retrofit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.bumptech.glide.Glide
import com.example.retrofit.databinding.FragmentMainBinding
import com.example.retrofit.ui.model.State
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = binding.user

        /* Загрузка данных при открытии фрагмента
        проверка для того, чтобы при повороте не вызывался метод
         */
        if(savedInstanceState == null) viewModel.onSignClick()

        // Обновление данных при нажатии на кнопку
        binding.button.setOnClickListener {
            viewModel.onSignClick()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is State.Loading -> {
                            textView.text = state.result
                            binding.progress.isVisible = true
                            binding.button.isEnabled = false
                            binding.img.isVisible = false
                        }

                        is State.Error -> {
                            textView.text = state.result
                            binding.progress.isVisible = false
                            binding.button.isEnabled = true
                            binding.img.isVisible = false
                        }

                        is State.Success -> {
                            textView.text = state.result
                            binding.progress.isVisible = false
                            binding.button.isEnabled = true
                            binding.img.isVisible = true
                            //binding.img.load(state.photo)
                            Glide.with(this@MainFragment)
                                .load(state.photo)
                                .into(binding.img);
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.error.collect { message ->
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}