package com.example.searchpage.model

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.searchpage.R
import com.example.searchpage.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var requestResult: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            requestResult = binding.search.text.toString()
            viewModel.onSignClick(requestResult)
        }

        binding.search.doOnTextChanged { text, _, _, _ ->
            if (text == null || text.length < 3) initializationState()
            else binding.button.isEnabled = true
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is State.Initialization -> initializationState()
                        is State.Loading -> loadingState()
                        is State.Success -> successState(state.result)
                        is State.Error -> errorState(state.requestError)
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

    /*Метод устанавливает состояние для поиска*/
    private fun loadingState() {
        binding.progress.isVisible = true
        binding.searchLayout.error = null
        binding.button.isEnabled = false
        binding.resultText.text = getText(R.string.search) //поиск
    }

    /*Метод устанавливает состояние для успешного завершения поиска*/
    private fun successState(result: String) {
        binding.progress.isVisible = false
        binding.searchLayout.error = null
        binding.button.isEnabled = true
        successResult(result)
    }

    /*Метод устанавливает состояние для ошибок поиска*/
    private fun errorState(error: String) {
        binding.progress.isVisible = false
        binding.button.isEnabled = true
        binding.searchLayout.error = error
        binding.resultText.text = error
    }

    private fun initializationState() {
        binding.resultText.text = getText(R.string.result)
        binding.button.isEnabled = false //изначально блокируем кнопку
    }

    @SuppressLint("SetTextI18n")
    private fun successResult(result: String) {
        binding.resultText.text =
            "${getText(R.string.answer)} `$result`${getText(R.string.addAnswer)}" //успешно
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

