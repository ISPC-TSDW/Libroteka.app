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

        // Icono del perfil y listener para navegar a ProfileActivity
        ImageView profileImageView = findViewById(R.id.profileIcon);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

        // Lista de categorías
        List<Categoria> listaCategorias = new ArrayList<>();
        listaCategorias.add(new Categoria("Acción", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Aventura", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Fantasía", R.drawable.ic_category_placeholder));

        // Lista de libros destacados
        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(new Libro("El Principito", R.drawable.ic_book_placeholder, "Juvenil"));
        listaLibros.add(new Libro("Don Quijote", R.drawable.ic_book_placeholder, "Histórico"));
        listaLibros.add(new Libro("1984", R.drawable.ic_book_placeholder, "Terror"));

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
                goToCategories(); // Navegar a la pantalla de categorías
                return true;
            } else if (id == R.id.navigation_favorites) {
                goToFavorites(); // Navegar a la pantalla de favoritos
                return true;
            }
            return false;
        });
    }

    // Método para navegar a ProfileActivity
    private void goToProfile() {
        Intent intent = new Intent(Home.this, ProfileActivity.class); // Asegúrate que tienes ProfileActivity creada
        startActivity(intent);
    }

    // Método para manejar el clic en la categoría y mostrar los libros relacionados
    private void mostrarLibrosPorCategoria(Categoria categoria) {
        List<Libro> librosFiltrados = new ArrayList<>();

        // Filtrar libros según la categoría seleccionada
        switch (categoria.getNombre()) {
            case "Acción":
                librosFiltrados.add(new Libro("Libro de Acción 1", R.drawable.ic_book_placeholder, "Acción"));
                librosFiltrados.add(new Libro("Libro de Acción 2", R.drawable.ic_book_placeholder, "Acción"));
                break;
            case "Aventura":
                librosFiltrados.add(new Libro("Libro de Aventura 1", R.drawable.ic_book_placeholder, "Aventura"));
                librosFiltrados.add(new Libro("Libro de Aventura 2", R.drawable.ic_book_placeholder, "Aventura"));
                break;
            case "Fantasía":
                librosFiltrados.add(new Libro("Libro de Fantasía 1", R.drawable.ic_book_placeholder, "Fantasía"));
                librosFiltrados.add(new Libro("Libro de Fantasía 2", R.drawable.ic_book_placeholder, "Fantasía"));
                break;
        }

        // Actualizamos el RecyclerView de los libros destacados
        LibrosAdapter librosAdapter = new LibrosAdapter(librosFiltrados);
        rvDestacados.setAdapter(librosAdapter);
    }

    // Método para navegar a la pantalla de categorías
    private void goToCategories() {
        Intent intent = new Intent(Home.this, Catalogo.class); // Asegúrate de tener Catalogo.java creado
        startActivity(intent);
    }

    // Método para navegar a la pantalla de favoritos (agregar cuando esté lista)
    private void goToFavorites() {
        // Agregar cuando esté lista la pantalla de favoritos
    }
}




