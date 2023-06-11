package com.tamaracapstone.tamara_android.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import com.tamaracapstone.tamara_android.Locator
import com.tamaracapstone.tamara_android.R
import com.tamaracapstone.tamara_android.databinding.ActivityRegisterBinding
import com.tamaracapstone.tamara_android.ui.login.LoginActivity
import com.tamaracapstone.tamara_android.utils.ResultState
import com.tamaracapstone.tamara_android.utils.launchAndCollectIn

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<RegisterViewModel>(factoryProducer = { Locator.registerViewModelFactory })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusListener()
        nameFocusListener()

        binding.btnDaftar.setOnClickListener { submitForm() }

        viewModel.registerViewState.launchAndCollectIn(this) {
            when (it.resultRegisterUser) {
                is ResultState.Success<String> -> {
//                    binding.btnDaftar.setLoading(false)
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.daftar_berhasil),
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(
                            this@RegisterActivity, LoginActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    finish()
                }
//                is ResultState.Loading -> binding.btnDaftar.setLoading(true)
                is ResultState.Error -> {
//                    binding.btnDaftar.setLoading(false)
                    Toast.makeText(
                        this@RegisterActivity, it.resultRegisterUser.message, Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
        binding.btnDaftar.setOnClickListener {
            viewModel.registerUser(
                name = binding.nameEditTextRegister.text.toString(),
                email = binding.emailEditTextRegister.text.toString(),
                password = binding.passwordEditTextRegister.text.toString()
            )
        }
        binding.tvPunyaAkun.setOnClickListener {
            startActivity(
                Intent(
                    this, LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }
    }

    private fun submitForm() {
        val validName = binding.nameTextLayout.helperText == null
        val validEmail = binding.emailTextLayout.helperText == null
        val validPassword = binding.passwordTextLayout.helperText == null

        if (validEmail && validPassword && validName)
            resetForm() //nnti ganti ke pindah activity home
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.nameTextLayout.helperText == null)
            message += "\n\nEmail: " + binding.nameTextLayout.helperText
        if(binding.emailTextLayout.helperText == null)
            message += "\n\nEmail: " + binding.emailTextLayout.helperText
        if(binding.passwordTextLayout.helperText == null)
            message += "\n\nPassword: " + binding.passwordTextLayout.helperText

        Toast.makeText(
            this@RegisterActivity, getString(R.string.invalid_form), Toast.LENGTH_SHORT
        ).show()
    }

    private fun resetForm() {
        var message = "Name: " + binding.nameEditTextRegister.text
        message += "\nEmail: " + binding.emailEditTextRegister.text
        message += "\nPassword: " + binding.passwordEditTextRegister.text
    }

    private fun nameFocusListener() {
        binding.nameEditTextRegister.setOnFocusChangeListener {_, focused ->
            if(!focused) {
                binding.nameTextLayout.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = binding.nameEditTextRegister.text.toString()
        if (nameText.length < 3) {
            return getString(R.string.panjang_nama)
        }
        return null
    }

    private fun emailFocusListener() {
        binding.emailEditTextRegister.setOnFocusChangeListener {_, focused ->
            if(!focused) {
                binding.emailTextLayout.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditTextRegister.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.email_tidak_valid)
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.passwordEditTextRegister.setOnFocusChangeListener {_, focused ->
            if(!focused) {
                binding.passwordTextLayout.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.emailEditTextRegister.text.toString()
        if (passwordText.length < 8) {
            return getString(R.string.panjang_password)
        }
        return null
    }
}