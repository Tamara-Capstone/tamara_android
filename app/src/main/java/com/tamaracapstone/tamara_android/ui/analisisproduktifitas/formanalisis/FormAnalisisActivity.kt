package com.tamaracapstone.tamara_android.ui.analisisproduktifitas.formanalisis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.tamaracapstone.tamara_android.R
import com.tamaracapstone.tamara_android.databinding.ActivityFormAnalisisBinding
import com.tamaracapstone.tamara_android.ui.analisisproduktifitas.resultanalisis.ResultAnalisisActivity

class FormAnalisisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormAnalisisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAnalisisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpanData.setOnClickListener {
            val intent = Intent(this, ResultAnalisisActivity::class.java)
            startActivity(intent)
        }

        val spinner: Spinner = findViewById(R.id.spinnner_plant)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.plants_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }
}