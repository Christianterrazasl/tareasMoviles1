package com.example.todoapp.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityTareasBinding
import com.example.todoapp.models.Tarea
import com.example.todoapp.ui.adapters.TareaAdapter
import androidx.core.graphics.toColorInt

class TareasActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTareasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTareasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        var tareas = arrayListOf<Tarea>(
            Tarea("Pasear al perro", "Sacarlo a pasear porque si no se hace caca", false,
                "#F44336".toColorInt()),
            Tarea("Lavar la ropa", "Lavar porque si no huele mal", false, "#2196F3".toColorInt()),
            Tarea("Comprar leche", "Ir al super y comprar leche", true, "#03A9F4".toColorInt())
        )

        val adapter = TareaAdapter(tareas)
        binding.rvTareas.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@TareasActivity).apply {
                orientation = RecyclerView.VERTICAL
            }

        }
    }
}