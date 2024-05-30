package com.example.wordcounter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wordcounter.db.App
import com.example.wordcounter.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (requireContext().applicationContext as App).db.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputText = binding.wordField.text

        binding.addBtm.setOnClickListener {
            val word = inputText.toString().trim()
            if(validData(word)){
                viewModel.onAddBtm(word)
                binding.wordField.text?.clear()
            } else {
                Snackbar.make(it, "Ошибка ввода", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.deleteBtm.setOnClickListener {
            viewModel.onDeleteBtn()
        }

        binding.updateBtm.setOnClickListener {
            lifecycleScope.launch {
                viewModel.allWords.collect { words ->
                    binding.textView.text = words.joinToString(
                        separator = "\r\n"
                    )
                }
            }
        }

        lifecycleScope.launch {
            viewModel.limitedWords
                .collect { words ->
                    binding.textView.text = words.joinToString(
                        separator = "\r\n"
                    )
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validData(word: String): Boolean {
        return word.isNotEmpty() && word.matches(Regex("[a-zA-Z-а-яА-Я]+"))
    }
}