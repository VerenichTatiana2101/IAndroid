package com.quizzymania.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.quizzymania.R
import com.quizzymania.databinding.FragmentQuestionBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Question : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.questions.startAnimation(fadeInAnimation)

        binding.button.setOnClickListener {
            val number = getAnswersByUser()
            val action =
                QuestionDirections.actionQuestionFragmentToResultFragment(number)
            findNavController().navigate(action)
        }
    }



    private fun getAnswersByUser(): Int {
        var count = 0

        if (binding.question1.checkedRadioButtonId == binding.answer4q1.id) count++
        if (binding.question2.checkedRadioButtonId == binding.answer1q2.id) count++
        if (binding.question3.checkedRadioButtonId == binding.answer4q3.id) count++

        return count
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}