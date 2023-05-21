package com.tamaracapstone.tamara_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.tamaracapstone.tamara_android.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
        nameFocusListener()

        binding.btnDaftar.setOnClickListener { submitForm() }
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
        var message = "Name: " + binding.nameEditTextLogin.text
        message += "\nEmail: " + binding.emailEditTextLogin.text
        message += "\nPassword: " + binding.passwordEditTextLogin.text
    }

    private fun nameFocusListener() {
        binding.nameEditTextLogin.setOnFocusChangeListener {_, focused ->
            if(!focused) {
                binding.nameTextLayout.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = binding.nameEditTextLogin.text.toString()
        if (nameText.length < 3) {
            return getString(R.string.panjang_nama)
        }
        return null
    }

    private fun emailFocusListener() {
        binding.emailEditTextLogin.setOnFocusChangeListener {_, focused ->
            if(!focused) {
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
        binding.passwordEditTextLogin.setOnFocusChangeListener {_, focused ->
            if(!focused) {
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