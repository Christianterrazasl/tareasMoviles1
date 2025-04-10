package com.example.proyecto1mobile.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto1mobile.R
import com.example.proyecto1mobile.databinding.ActivityMainBinding
import com.example.proyecto1mobile.ui.viewmodels.MainViewModel
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupEventListeners(){
        binding.btnSend.setOnClickListener {
            val username:String = binding.inputUsername.text.toString()
            val password:String = binding.inputPassword.text.toString()
            viewModel.sendForm(username, password)

        }

    }

    private fun setupViewModelObservers(){
        viewModel.changeActivity.observe(this) {
            if(it){
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("username", "Admin")
                startActivity(intent)
                finish()
            }
        }

        viewModel.showError.observe(this) {
            if (it){
                Toast.makeText(this, "Error, contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

