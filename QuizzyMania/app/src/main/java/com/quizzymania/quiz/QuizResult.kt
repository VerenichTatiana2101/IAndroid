package com.quizzymania.quiz

import android.annotation.SuppressLint
import android.media.MediaPlayer
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
    private var mediaPlayer: MediaPlayer? = null

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
        if (args.number == 3) {
            binding.resultQuiz.text = getString(R.string.result_win)
            playMelody(R.raw.right_answer)
        } else {
            binding.resultQuiz.text = getString(R.string.result_full)
            playMelody(R.raw.wrong_answer)
        }

        binding.result.text = "${args.number}/3"

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_quizResult_to_QuizFragment)
        }
    }

    private fun playMelody(resourceId: Int) {
        //mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(requireContext(), resourceId)
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        _binding = null
    }
}