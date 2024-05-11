package com.example.searchpage.model

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.core.view.isVisible
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

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var requestResult: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            requestResult = binding.search.text.toString()
            viewModel.onSignClick(requestResult)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Initialization -> binding.resultText.text = getText(R.string.result)
                        State.Loading -> loadingState()
                        State.Success -> successState()
                        is State.Error -> {
                            errorState(state.requestError)
                        }
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

    // Функция для установки текста результата
    private fun setResultText(result: String) {
        val resultText =
            getText(R.string.start_result).toString() +
                    " \n`" + result + getText(R.string.end_result)
        binding.resultText.text = resultText
    }

    /*Метод устанавливает состояние для поиска*/
    private fun loadingState(){
        binding.progress.isVisible = true
        binding.searchLayout.error = null
        binding.button.isEnabled = false
        binding.resultText.text = getText(R.string.search) //поиск
    }

    /*Метод устанавливает состояние для успешного завершения поиска*/
    private fun successState(){
        binding.progress.isVisible = false
        binding.searchLayout.error = null
        binding.button.isEnabled = true
        binding.resultText.text = getText(R.string.answer) //успешно
    }

    /*Метод устанавливает состояние для ошибок поиска*/
    private fun errorState(error: String){
        binding.progress.isVisible = false
        setResultText(requestResult)//запрос не найден
        binding.button.isEnabled = true
        binding.searchLayout.error = error
    }
}

