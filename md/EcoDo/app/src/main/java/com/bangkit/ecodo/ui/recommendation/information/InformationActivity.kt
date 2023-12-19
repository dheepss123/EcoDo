package com.bangkit.ecodo.ui.recommendation.information

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.bangkit.ecodo.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.iconEcodo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val linearLayoutAnimator =
            ObjectAnimator.ofFloat(binding.linearLayout1, View.ALPHA, 0f, 1f).apply {
                duration = 300
            }
        val linearLayoutAnimator2 =
            ObjectAnimator.ofFloat(binding.linearLayout2, View.ALPHA, 0f, 1f).apply {
                duration = 1000
            }
        val title =
            ObjectAnimator.ofFloat(binding.tvInformation, View.ALPHA, 0f, 1f).setDuration(300)
        val text1 = ObjectAnimator.ofFloat(binding.information1, View.ALPHA, 1f).setDuration(300)
        val text2 = ObjectAnimator.ofFloat(binding.information2, View.ALPHA, 1f).setDuration(300)
        val text3 = ObjectAnimator.ofFloat(binding.information3, View.ALPHA, 1f).setDuration(300)
        val text4 = ObjectAnimator.ofFloat(binding.information4, View.ALPHA, 1f).setDuration(300)
        val text5 = ObjectAnimator.ofFloat(binding.information5, View.ALPHA, 1f).setDuration(300)
        val textFeedback =
            ObjectAnimator.ofFloat(binding.tvFeedback, View.ALPHA, 1f).setDuration(300)
        val dislike =
            ObjectAnimator.ofFloat(binding.imageButtonDislike, View.ALPHA, 1f).setDuration(1000)
        val like = ObjectAnimator.ofFloat(binding.imageButtonLike, View.ALPHA, 1f).setDuration(1000)

        val together = AnimatorSet().apply {
            playTogether(dislike, like)
        }
        AnimatorSet().apply {
            playSequentially(title, text1, text2, text3, text4, text5, textFeedback, together)
            startDelay = 300
        }.start()

        linearLayoutAnimator.start()
        linearLayoutAnimator2.start()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}