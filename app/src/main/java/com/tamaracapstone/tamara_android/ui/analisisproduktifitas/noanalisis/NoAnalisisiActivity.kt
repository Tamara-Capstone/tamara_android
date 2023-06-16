package com.tamaracapstone.tamara_android.ui.analisisproduktifitas.noanalisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamaracapstone.tamara_android.databinding.ActivityNoAnalisisiBinding
import com.tamaracapstone.tamara_android.ui.analisisproduktifitas.formanalisis.FormAnalisisActivity

class NoAnalisisiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoAnalisisiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoAnalisisiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIsiData.setOnClickListener {
            val intent = Intent(this, FormAnalisisActivity::class.java)
            startActivity(intent)
        }
    }
}