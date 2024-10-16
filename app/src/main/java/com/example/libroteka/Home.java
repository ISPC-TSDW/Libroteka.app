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

    private RecyclerView rvDestacados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //
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
        listaCategorias.add(new Categoria("Aventura", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Fantasía", R.drawable.ic_category_placeholder));

        // Lista de libros destacados de ejemplo
        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(new Libro("El Principito", R.drawable.ic_book_placeholder));
        listaLibros.add(new Libro("Don Quijote", R.drawable.ic_book_placeholder));
        listaLibros.add(new Libro("1984", R.drawable.ic_book_placeholder));

        // Inicializamos RecyclerView y BottomNavigationView
        RecyclerView rvCategorias = findViewById(R.id.rvCategorias);
        rvDestacados = findViewById(R.id.rvDestacados);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Configuramos el RecyclerView horizontal para categorías con un listener de clic
        rvCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(listaCategorias, new CategoriasAdapter.OnCategoriaClickListener() {
            @Override
            public void onCategoriaClick(Categoria categoria) {
                // Acción cuando se selecciona una categoría (mostrar libros relacionados)
                mostrarLibrosPorCategoria(categoria);
            }
        });
        rvCategorias.setAdapter(categoriasAdapter);

        // Agregamos el SnapHelper para el efecto de "snap"
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvCategorias);

        // Configuramos el RecyclerView horizontal para libros destacados
        rvDestacados.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDestacados.setAdapter(new LibrosAdapter(listaLibros));

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

    // Método para navegar a ProfileActivity
    private void goToProfile() {
        Intent intent = new Intent(Home.this, ProfileActivity.class);
        startActivity(intent);
    }

    // Método para manejar el clic en la categoría y mostrar los libros relacionados
    private void mostrarLibrosPorCategoria(Categoria categoria) {
        List<Libro> librosFiltrados = new ArrayList<>();

        // Lógica para filtrar libros según la categoría seleccionada
        switch (categoria.getNombre()) {
            case "Acción":
                librosFiltrados.add(new Libro("Libro de Acción 1", R.drawable.ic_book_placeholder));
                librosFiltrados.add(new Libro("Libro de Acción 2", R.drawable.ic_book_placeholder));
                break;
            case "Aventura":
                librosFiltrados.add(new Libro("Libro de Aventura 1", R.drawable.ic_book_placeholder));
                librosFiltrados.add(new Libro("Libro de Aventura 2", R.drawable.ic_book_placeholder));
                break;
            case "Fantasía":
                librosFiltrados.add(new Libro("Libro de Fantasía 1", R.drawable.ic_book_placeholder));
                librosFiltrados.add(new Libro("Libro de Fantasía 2", R.drawable.ic_book_placeholder));
                break;
            default:
                librosFiltrados.add(new Libro("Libro General 1", R.drawable.ic_book_placeholder));
                librosFiltrados.add(new Libro("Libro General 2", R.drawable.ic_book_placeholder));
                break;
        }

        // Actualizamos el RecyclerView de los libros destacados
        LibrosAdapter librosAdapter = new LibrosAdapter(librosFiltrados);
        rvDestacados.setAdapter(librosAdapter);
    }
}

