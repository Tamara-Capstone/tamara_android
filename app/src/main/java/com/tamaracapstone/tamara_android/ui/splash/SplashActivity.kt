package com.tamaracapstone.tamara_android.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.tamaracapstone.tamara_android.Locator
import com.tamaracapstone.tamara_android.R
import com.tamaracapstone.tamara_android.databinding.ActivitySplashBinding
import com.tamaracapstone.tamara_android.ui.dashboard.DashboardActivity
import com.tamaracapstone.tamara_android.ui.login.LoginActivity
import com.tamaracapstone.tamara_android.utils.ResultState
import com.tamaracapstone.tamara_android.utils.launchAndCollectIn

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SplashViewModel>(factoryProducer = { Locator.splashViewModelFactory })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hideSystemUI()
        viewModel.splashState.launchAndCollectIn(this) {
            if (it.resultIsLoggedIn is ResultState.Success) {
                if (it.resultIsLoggedIn.data == true) {
                    startActivity(
                        Intent(this@SplashActivity, DashboardActivity::class.java).addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                        )
                    )
                    finish()
                } else {
                    startActivity(
                        Intent(this@SplashActivity, LoginActivity::class.java).addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                        )
                    )
                    finish()
                }
            }
        }

    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}