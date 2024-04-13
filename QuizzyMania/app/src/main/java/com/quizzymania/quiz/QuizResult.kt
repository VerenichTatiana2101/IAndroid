package com.quizzymania.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.quizzymania.R
import com.quizzymania.databinding.QuizResultBinding


class QuizResult : Fragment() {
    private val args: QuizResultArgs by navArgs()
    private var _binding: QuizResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizResultBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(args.number == 3) binding.resultQuiz.text = getString(R.string.result_win)
        else binding.resultQuiz.text = getString(R.string.result_full)

        binding.result.text = args.number.toString() + "/3"

        binding.button.setOnClickListener {
           findNavController().navigate(R.id.action_quizResult_to_QuizFragment)
     }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}