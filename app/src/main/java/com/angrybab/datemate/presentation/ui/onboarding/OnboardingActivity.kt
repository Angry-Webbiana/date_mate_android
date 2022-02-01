package com.angrybab.datemate.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angrybab.datemate.MainActivity
import com.angrybab.datemate.R
import com.angrybab.datemate.data.repository.SharedPrefRepository
import com.angrybab.datemate.databinding.ActivityOnboardingBinding
import com.angrybab.datemate.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity () : AppCompatActivity() {

    @Inject lateinit var sharedPrefRepository: SharedPrefRepository

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme(R.style.Theme_DateMate)
        super.onCreate(savedInstanceState)

        /*if (sharedPrefRepository.isLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*/

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnOnboardingStart.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}