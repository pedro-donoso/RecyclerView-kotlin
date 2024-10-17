package com.example.recyclerviewexample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewexample.SuperHero
import com.example.recyclerviewexample.databinding.ItemSuperheroBinding

// Clase para el ViewHolder del RecyclerView
class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Utilizo View Binding para acceder a las vistas en el layout
    private val binding = ItemSuperheroBinding.bind(view)

    // Método para enlazar los datos del superhéroe con las vistas
    fun render(superHeroModel: SuperHero, onClickListener: (SuperHero) -> Unit) {

        // Establezco los valores de las vistas con los datos del superhéroe
        binding.tvSuperHeroName.text = superHeroModel.superhero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher

        // Utilizo Glide para cargar la imagen del superhéroe
        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)

        // Establezco el listener del clic en la vista
        itemView.setOnClickListener { onClickListener(superHeroModel) }
    }
}