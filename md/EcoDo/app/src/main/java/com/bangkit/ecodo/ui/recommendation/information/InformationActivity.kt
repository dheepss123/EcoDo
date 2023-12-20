package com.bangkit.ecodo.ui.recommendation.information

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.databinding.ActivityInformationBinding
import com.bangkit.ecodo.ui.adapter.ArticleAdapter
import com.bangkit.ecodo.ui.recommendation.video.VideoRecomendationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding
    private lateinit var articleAdapter: ArticleAdapter
    private val viewModel: InformationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getArticle().observe(this) { articles ->
            showLoading(false)
            articleAdapter = ArticleAdapter(articles)
            binding.rvList.layoutManager = LinearLayoutManager(this)
            binding.rvList.adapter = articleAdapter
        }

        showLoading(true)

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
        val text1 = ObjectAnimator.ofFloat(binding.rvList, View.ALPHA, 1f).setDuration(300)
        val textFeedback =
            ObjectAnimator.ofFloat(binding.tvFeedback, View.ALPHA, 1f).setDuration(300)
        val dislike =
            ObjectAnimator.ofFloat(binding.imageButtonDislike, View.ALPHA, 1f).setDuration(1000)
        val like = ObjectAnimator.ofFloat(binding.imageButtonLike, View.ALPHA, 1f).setDuration(1000)

        val together = AnimatorSet().apply {
            playTogether(dislike, like)
        }
        AnimatorSet().apply {
            playSequentially(title, text1, textFeedback, together)
            startDelay = 300
        }.start()

        linearLayoutAnimator.start()
        linearLayoutAnimator2.start()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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