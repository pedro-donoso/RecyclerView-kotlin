package com.example.recyclerviewexample.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.SuperHero

class SuperHeroAdapter(val superHeroList: List<SuperHero>) : RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {

    }

    override fun getItemCount(): Int = superHeroList.size


    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {

    }
}