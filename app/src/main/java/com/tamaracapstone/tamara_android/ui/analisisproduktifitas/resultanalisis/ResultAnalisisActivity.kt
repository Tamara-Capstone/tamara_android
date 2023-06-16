package com.tamaracapstone.tamara_android.ui.analisisproduktifitas.resultanalisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamaracapstone.tamara_android.R
import com.tamaracapstone.tamara_android.databinding.ActivityFormAnalisisBinding
import com.tamaracapstone.tamara_android.databinding.ActivityNoAnalisisiBinding
import com.tamaracapstone.tamara_android.databinding.ActivityResultAnalisisBinding
import com.tamaracapstone.tamara_android.ui.analisisproduktifitas.formanalisis.FormAnalisisActivity

class ResultAnalisisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultAnalisisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultAnalisisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPerbaruiData.setOnClickListener {
            val intent = Intent(this, FormAnalisisActivity::class.java)
            startActivity(intent)
        }
    }
}