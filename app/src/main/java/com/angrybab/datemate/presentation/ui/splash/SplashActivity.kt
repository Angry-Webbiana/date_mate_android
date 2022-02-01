package com.angrybab.datemate.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.angrybab.datemate.MainActivity
import com.angrybab.datemate.R
import com.angrybab.datemate.data.repository.SharedPrefRepository
import com.angrybab.datemate.databinding.ActivitySplashBinding
import com.angrybab.datemate.presentation.ui.onboarding.OnboardingActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        Glide.with(this).asGif().load(R.raw.animated_logo).into(binding.ivSplashLogo)

        val handler = Handler()
        handler.postDelayed(Runnable {
            if (sharedPrefRepository.isLogin()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }, 3000)
    }
}