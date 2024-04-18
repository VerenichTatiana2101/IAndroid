package com.quizzymania.quiz

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.quizzymania.R
import com.quizzymania.databinding.QuizResultBinding


class QuizResult : Fragment() {
    private val args: QuizResultArgs by navArgs()
    private var _binding: QuizResultBinding? = null
    private val binding get() = _binding!!
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var textAnimator: ValueAnimator

    @SuppressLint("Recycle")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizResultBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Создаем ObjectAnimator для масштабирования кнопки
        val scaleXAnimator = ObjectAnimator.ofFloat(binding.button, "scaleX", 1f, 1.6f)
        val scaleYAnimator = ObjectAnimator.ofFloat(binding.button, "scaleY", 1f, 1.6f)
        scaleXAnimator.duration = 700 // Продолжительность анимации (в миллисекундах)
        scaleYAnimator.duration = 700
        // Запускаем анимацию
        scaleXAnimator.start()
        scaleYAnimator.start()

        // метод градиента текста
        gradientText(binding.resultQuiz)

        super.onViewCreated(view, savedInstanceState)
        if (args.number == 3) {
            binding.resultQuiz.text = getString(R.string.result_win)
            // в случае выигрыша анимация меняется
            binding.anime.setAnimation(R.raw.second_anim)
            playMelody(R.raw.right_answer)
        } else {
            binding.resultQuiz.text = getString(R.string.result_full)
            playMelody(R.raw.wrong_answer)
        }
        binding.result.text = "${args.number}/3"
        binding.button.setOnClickListener {
            // останавливаю анимацию чтобы не вылетало приложение
            textAnimator.cancel()
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
        // останавливаю анимацию чтобы не вылетало приложение
        textAnimator.cancel()
        mediaPlayer?.release()
        _binding = null
    }
    
    private fun gradientText(text: TextView) {
        textAnimator = ValueAnimator.ofObject(
            GradientArgbEvaluator,
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA),
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.BLUE),
            intArrayOf(Color.MAGENTA, Color.BLUE, Color.BLACK),
            intArrayOf(Color.BLUE, Color.BLACK, Color.RED),
            intArrayOf(Color.BLACK, Color.RED, Color.GREEN),
            intArrayOf(Color.RED, Color.GREEN, Color.BLUE),
            intArrayOf(Color.GREEN, Color.BLUE, Color.CYAN),
            intArrayOf(Color.BLUE, Color.CYAN, Color.YELLOW),
            intArrayOf(Color.CYAN, Color.YELLOW, Color.MAGENTA),
        ).apply {
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            duration = 3000
            addUpdateListener {
                val shader = LinearGradient(
                    0f, 0f,
                    text.paint.measureText(text.text.toString()),
                    text.textSize, it.animatedValue
                            as IntArray, null, Shader.TileMode.CLAMP
                )
                text.paint.shader = shader
                text.invalidate()
            }
            start()
        }
    }

    object GradientArgbEvaluator : TypeEvaluator<IntArray> {
        private val argbEvaluator = ArgbEvaluator()
        override fun evaluate(fraction: Float, startValue: IntArray, endValue: IntArray): IntArray {
            return startValue.mapIndexed { index, item ->
                argbEvaluator.evaluate(fraction, item, endValue[index]) as Int
            }.toIntArray()
        }
    }
}