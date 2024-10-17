package com.example.recyclerviewexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.SuperHero

// Clase para el adaptador del RecyclerView
class SuperHeroAdapter(
    private val superHeroList: List<SuperHero>, // Lista de superhéroes
    private val onClickListener: (SuperHero) -> Unit // Función de clic en un superhéroe
) : RecyclerView.Adapter<SuperHeroViewHolder>() { // Hereda de RecyclerView.Adapter

    // Método para crear un ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) // Inflar el layout del ViewHolder

        // Inflar el layout del ViewHolder y devuelve un nuevo SuperHeroViewHolder
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    // Método para enlazar los datos con el ViewHolder
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position] // Obtener el superhéroe en la posición actual

        // Enlazar los datos del superhéroe con el ViewHolder
        holder.render(item, onClickListener)
    }

    // Método para obtener la cantidad de elementos en la lista
    override fun getItemCount(): Int = superHeroList.size // Devuelve el tamaño de la lista
}