package com.example.roomdb.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.roomdb.App
import com.example.roomdb.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val userDao = (requireContext().applicationContext as App).db.userDao()
                return UserViewModel(userDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            addBtm.setOnClickListener {
                viewModel.onAddBtm()
            }

            updateBtm.setOnClickListener {
                viewModel.onUpdateBtn()
            }

            deleteBtm.setOnClickListener {
                viewModel.onDeleteBtn()
            }

            lifecycleScope.launchWhenStarted {
                viewModel.allUsers
                    .collect { users ->
                        textView.text = users.joinToString(
                            separator = "\r\n"
                        )
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}