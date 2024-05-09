package com.example.additional.ui.model

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.additional.databinding.FragmentFirstBinding

private const val TAG = "FirstFragment"

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { viewModel.onClick() }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.stateFlow
                    .collect {
                        Log.d(TAG, "onViewCreated: $it")
                        binding.stateFlow.text = it
                    }
            }
        viewModel.liveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: $it")
            binding.liveData.text = it
        }
    }
}
