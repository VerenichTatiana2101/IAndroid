package com.example.mvvmpattern.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmpattern.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    // добалвение [MainViewModelFactory] в делегат [viewModels]
    private val viewModel: MainViewModel by viewModels{MainViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.viewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner

        binding.button.setOnClickListener {
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            viewModel.onSignClick(login, password)
        }

        // отвечает за жизненный цикл
        viewLifecycleOwner.lifecycleScope //scope для запуска корутин
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when (state) {
                            State.Loading -> {
                                binding.progress.isVisible = true
                                binding.loginLayout.error = null
                                binding.passwordLayout.error = null
                                binding.button.isEnabled = false
                            }

                            State.Success -> {
                                binding.progress.isVisible = false
                                binding.loginLayout.error = null
                                binding.passwordLayout.error = null
                                binding.button.isEnabled = true
                            }

                            is State.Error -> {
                                binding.progress.isVisible = false
                                binding.loginLayout.error = state.loginError
                                binding.passwordLayout.error = state.passwordError
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