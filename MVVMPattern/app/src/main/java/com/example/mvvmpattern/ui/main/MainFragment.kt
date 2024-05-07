package com.example.mvvmpattern.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.mvvmpattern.databinding.FragmentMainBinding

import com.example.mvvmpattern.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val login = binding.login.toString()
            val password = binding.password.toString()
            viewModel.onSignInClick(login, password)
        }

        // отвечает за жизненный цикл
        viewLifecycleOwner.lifecycleScope //scope для запуска корутин
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when (state) {
                            State.Loading -> {
                                binding.progress.isVisible = true
                            }

                            State.Success -> {
                                binding.progress.isVisible = false
                            }
                        }
                    }
            }
    }
}