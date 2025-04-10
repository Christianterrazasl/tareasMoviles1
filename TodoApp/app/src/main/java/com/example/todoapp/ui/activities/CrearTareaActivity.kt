package com.example.todoapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityCrearTareaBinding

class CrearTareaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCrearTareaBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCrearTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
       binding.btnAgregar.setOnClickListener {
           val intent = Intent(this, TareasActivity::class.java)
           intent.putExtra("title", binding.editTextText.text.toString())
           intent.putExtra("description", binding.editTextText2.text.toString())
           intent.putExtra("state", binding.checkBox2.isChecked)
           intent.putExtra("color", "#FFFFFF".toColorInt())

           startActivity(intent)
       }
    }

}