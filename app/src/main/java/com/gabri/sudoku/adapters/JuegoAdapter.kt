package com.gabri.sudoku.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabri.sudoku.model.JuegoModel
import com.gabri.sudoku.R
class JuegoAdapter(private var items: List<JuegoModel>, private val onItmClick: ((JuegoModel) -> Unit)? = null): RecyclerView.Adapter<JuegoAdapter.JuegoViewHolder>() {
    class JuegoViewHolder(itm: View): RecyclerView.ViewHolder(itm){
        val nombre: TextView = itm.findViewById<TextView>(R.id.nombreLbl)
        val tiempo: TextView = itm.findViewById<TextView>(R.id.tiempoLbl)
        val dificultad: TextView = itm.findViewById<TextView>(R.id.difLbl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuegoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_activity, parent, false)
        return JuegoViewHolder(view)
    }

    override fun onBindViewHolder(holder: JuegoViewHolder, position: Int) {
        val puntuacion = items[position]
        holder.nombre.text = puntuacion.nombre
        holder.tiempo.text = puntuacion.tiempo
        holder.dificultad.text = puntuacion.dificultad
    }

    override fun getItemCount(): Int = items.size

    fun updatePuntuaciones(newItems: List<JuegoModel>) {
        items = newItems
        notifyDataSetChanged()
    }

}