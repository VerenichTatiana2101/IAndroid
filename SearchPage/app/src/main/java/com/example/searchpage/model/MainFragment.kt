package com.example.searchpage.model

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.searchpage.R
import com.example.searchpage.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var requestResult: String

        binding.button.setOnClickListener {
            requestResult = binding.search.text.toString()
            viewModel.onSignClick(requestResult)
        }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        binding.resultText.text = getText(R.string.answer)
                        when (state) {
                            State.Loading -> {
                                binding.progress.isVisible = true
                                binding.searchLayout.error = null
                                binding.button.isEnabled = false
                                binding.resultText.text = getText(R.string.search) //поиск
                            }

                            State.Success -> {
                                binding.progress.isVisible = false
                                binding.searchLayout.error = null
                                binding.button.isEnabled = true
                                binding.resultText.text = getText(R.string.answer) //успешно
                            }

                            is State.Error -> {
                                binding.progress.isVisible = false
                                binding.searchLayout.error = state.requestError
                                binding.resultText.text =
                                    getText(R.string.error_text)//запрос не найден
                                binding.button.isEnabled = true
                            }

                        }
                    }

            }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.error
                    .collect { message ->
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                    }
            }
    }
}
