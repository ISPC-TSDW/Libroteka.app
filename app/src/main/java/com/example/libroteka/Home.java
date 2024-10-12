package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up the profile icon and click listener for navigation to ProfileActivity
        ImageView profileImageView = findViewById(R.id.profileIcon);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

        // Lista de categorías de ejemplo
        List<Categoria> listaCategorias = new ArrayList<>();
        listaCategorias.add(new Categoria("Acción", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Suspenso", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Fantasía", R.drawable.ic_category_placeholder));

        // Lista de libros destacados de ejemplo
        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(new Libro("El Instituto", R.drawable.el_instituto));
        listaLibros.add(new Libro("El laberinto del Fauno", R.drawable.el_laberinto_del_fauno));
        listaLibros.add(new Libro("El Visitante", R.drawable.el_visitante));
        listaLibros.add(new Libro("Terapia", R.drawable.terapia));
        listaLibros.add(new Libro("A la caza de Jack el destripador", R.drawable.a_la_caza_de_jack_el_destripador));


        // Inicializamos RecyclerView y BottomNavigationView
        RecyclerView rvCategorias = findViewById(R.id.rvCategorias);
        RecyclerView rvDestacados = findViewById(R.id.rvDestacados);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Configuramos el RecyclerView horizontal para categorías
        rvCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategorias.setAdapter(new CategoriasAdapter(listaCategorias));

        // Agregamos el SnapHelper para el efecto de "snap"
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvCategorias);

        // Configuramos el RecyclerView horizontal para libros destacados
        rvDestacados.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDestacados.setAdapter(new DestacadosAdapter(listaLibros));

        // Menú de navegación inferior
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                return true;
            } else if (id == R.id.navigation_categories) {
                return true;
            } else if (id == R.id.navigation_favorites) {
                return true;
            }
            return false;
        });
    }

    // Method to navigate to ProfileActivity
    private void goToProfile() {
        Intent intent = new Intent(Home.this, ProfileActivity.class);
        startActivity(intent);
    }
}