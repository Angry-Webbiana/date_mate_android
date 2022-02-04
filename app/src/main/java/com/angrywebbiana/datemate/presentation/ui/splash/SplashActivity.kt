package com.angrywebbiana.datemate.presentation.ui.splash

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.angrywebbiana.datemate.MainActivity
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.databinding.ActivitySplashBinding
import com.angrywebbiana.datemate.presentation.ui.onboarding.OnboardingActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    private lateinit var binding: ActivitySplashBinding

    private val gifListener: RequestListener<GifDrawable> by lazy {
        object : RequestListener<GifDrawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<GifDrawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: GifDrawable,
                model: Any?,
                target: Target<GifDrawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource.setLoopCount(1)
                val callback = object: Animatable2Compat.AnimationCallback() {
                    override fun onAnimationEnd(drawable: Drawable?) {
                        if (sharedPrefRepository.isLogin()) {
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
                            finish()
                        }
                        super.onAnimationEnd(drawable)
                    }
                }
                resource.registerAnimationCallback(callback)
                return false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        Glide.with(this).asGif().load(R.drawable.animated_logo).listener(gifListener).into(binding.ivSplashLogo)
    }
}