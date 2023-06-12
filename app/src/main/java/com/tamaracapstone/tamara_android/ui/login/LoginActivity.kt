package com.tamaracapstone.tamara_android.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import com.tamaracapstone.tamara_android.Locator
import com.tamaracapstone.tamara_android.R
import com.tamaracapstone.tamara_android.databinding.ActivityLoginBinding
import com.tamaracapstone.tamara_android.ui.register.RegisterActivity
import com.tamaracapstone.tamara_android.ui.dashboard.DashboardActivity
import com.tamaracapstone.tamara_android.utils.ResultState
import com.tamaracapstone.tamara_android.utils.launchAndCollectIn

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel>(factoryProducer = { Locator.loginViewModelFactory })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusListener()



        viewModel.loginState.launchAndCollectIn(this) { state ->
            when (state.resultVerifyUser) {
                is ResultState.Success<String> -> {
//                    binding.btnLogin.setLoading(false)
                    startActivity(
                        Intent(
                            this@LoginActivity, DashboardActivity::class.java
                        )
                    )
                    finish()
                }
//                is ResultState.Loading -> binding.btnLogin.setLoading(true)
                is ResultState.Error -> {
//                    binding.btnLogin.setLoading(false)
                    Toast.makeText(
                        this@LoginActivity, state.resultVerifyUser.message, Toast.LENGTH_SHORT
                    ).show()
                }

                else -> Unit
            }

        }

        binding.btnLogin.setOnClickListener { submitForm() }

//        binding.btnLogin.setOnClickListener {
//            viewModel.doLogin(
//                email = binding.emailEditTextLogin.text.toString(),
//                password = binding.passwordEditTextLogin.text.toString()
//            )
//        }

        binding.textDontHaveAcc.setOnClickListener {
            startActivity(
                Intent(
                    this, RegisterActivity::class.java
                )
            )
        }

    }


    private fun submitForm() {
        val validEmail = binding.emailTextLayout.helperText == null
        val validPassword = binding.passwordTextLayout.helperText == null

        if (validEmail && validPassword)
            viewModel.doLogin(
                email = binding.emailEditTextLogin.text.toString(),
                password = binding.passwordEditTextLogin.text.toString()
            )
//            resetForm() //nnti ganti ke pindah activity home
        else
        invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if (binding.emailTextLayout.helperText == null)
            message += "\n\nEmail: " + binding.emailTextLayout.helperText
        if (binding.passwordTextLayout.helperText == null)
            message += "\n\nPassword: " + binding.passwordTextLayout.helperText

        Toast.makeText(
            this@LoginActivity, getString(R.string.invalid_form), Toast.LENGTH_SHORT
        ).show()
    }

    private fun resetForm() {
        var message = "Email: " + binding.emailEditTextLogin.text
        message += "\nPassword: " + binding.passwordEditTextLogin.text
    }

    private fun emailFocusListener() {
        binding.emailEditTextLogin.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailTextLayout.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditTextLogin.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.email_tidak_valid)
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.passwordEditTextLogin.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordTextLayout.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.emailEditTextLogin.text.toString()
        if (passwordText.length < 8) {
            return getString(R.string.panjang_password)
        }
        return null
    }
}