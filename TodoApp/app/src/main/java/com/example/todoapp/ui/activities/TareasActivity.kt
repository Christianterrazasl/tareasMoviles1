package com.example.todoapp.ui.activities

import android.content.Intent
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
    var tareas = arrayListOf<Tarea>(
        Tarea("Pasear al perro", "Sacarlo al perro a pasear", false,
            "#F44336".toColorInt()),
        Tarea("Lavar la ropa", "Lavar la ropa de la semana", false, "#2196F3".toColorInt()),
        Tarea("Comprar soda", "Ir al super y comprar leche", true, "#03A9F4".toColorInt())
    )

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
        setOnClickListeners()
    }

    private fun setupRecyclerView() {

        val adapter = TareaAdapter(tareas)
        binding.rvTareas.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@TareasActivity).apply {
                orientation = RecyclerView.VERTICAL
            }

        }


    }

    private fun setOnClickListeners(){
        binding.btnAgregarTarea.setOnClickListener {
            val intent = Intent(this, CrearTareaActivity::class.java)
            startActivity(intent)
        }
    }


}
