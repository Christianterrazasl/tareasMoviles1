package com.example.todoapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TareasItemBinding
import com.example.todoapp.models.Tarea
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColorInt

class TareaAdapter(var tareas : ArrayList<Tarea>) : RecyclerView.Adapter<TareaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = TareasItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tareas[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    class ViewHolder(private val binding: TareasItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (item: Tarea){
            binding.lblTitle.text = item.title
            binding.textView2.text = item.description
            binding.checkBox.isChecked = item.state
            binding.itemContainer.setBackgroundColor(item.color)

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                item.state = isChecked
            }
            binding.colorPickerBtn.setOnClickListener {
                mostrarDialogoColor(binding.root.context, item)
            }


        }

        private fun mostrarDialogoColor(context: Context, tarea: Tarea){
            val colores = arrayOf(
                "#FFFFFF", // Blanco
                "#F44336", // Rojo
                "#FFEB3B", // Amarillo
                "#03A9F4",// Celeste
                "#4CAF50", // Verde
                "#2196F3", // Azul
                "#C0C0C0", //plomo
                "#00FF00", //Lima
                "#FF00FF", //Morado
                "#FFC0CB" //Rosa



            )

            AlertDialog.Builder(context)
                .setTitle("Selecciona un color")
                .setItems(colores){ _, which ->
                    val nuevoColor = colores[which].toColorInt()
                    tarea.color = nuevoColor
                    binding.itemContainer.setBackgroundColor(nuevoColor)
                }
                .show()
        }
    }


}

