package com.example.recyclerviewexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.adapter.SuperHeroAdapter
import com.example.recyclerviewexample.databinding.ActivityMainBinding

// Importo librerias necesarias
class MainActivity : AppCompatActivity() {

    // Declaro binding
    private lateinit var binding: ActivityMainBinding

    // Declaro la funcion onCreate y la inicializo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llamada al constructor de la clase padre

        // Inflar la vista utilizando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Establece la vista raíz de la actividad

        // Inicializo el RecyclerView
        initRecyclerView()
    }

    // Inicializo el RecyclerView
    private fun initRecyclerView(){

        // Configuro el LayoutManager del RecyclerView
        val manager = LinearLayoutManager(this)

        // Configuro el DividerItemDecoration del RecyclerView
        val decoration = DividerItemDecoration(this, manager.orientation)

        // Establezco el LinearLayoutManager en el RecyclerView
        binding.recyclerSuperHero.layoutManager = manager

        // Establezco el Adapter del RecyclerView
        binding.recyclerSuperHero.adapter =
            SuperHeroAdapter(SuperHeroProvider.superHeroList) { superhero ->

                // Manejo del clic en un elemento del RecyclerView
            onItemSelected(
                superhero
            )
        }

    }

    // Método para manejar el clic en un elemento del RecyclerView
    private fun onItemSelected(superHero: SuperHero){

        // Muestro un Toast con el nombre del superhéroe seleccionado
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}

