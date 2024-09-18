package com.example.libroteka;

import android.os.Bundle;
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

        // Lista de categorías de ejemplo
        List<Categoria> listaCategorias = new ArrayList<>();
        listaCategorias.add(new Categoria("Acción", R.drawable.ic_category_placeholder)); // Asegúrate de tener los recursos correctos
        listaCategorias.add(new Categoria("Aventura", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Fantasía", R.drawable.ic_category_placeholder));

        // Lista de libros destacados de ejemplo
        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(new Libro("El Principito", R.drawable.ic_book_placeholder));
        listaLibros.add(new Libro("Don Quijote", R.drawable.ic_book_placeholder));
        listaLibros.add(new Libro("1984", R.drawable.ic_book_placeholder));

        // Inicializamos RecyclerView y BottomNavigationView como variables locales
        RecyclerView rvCategorias = findViewById(R.id.rvCategorias);
        RecyclerView rvDestacados = findViewById(R.id.rvDestacados);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Configuramos el RecyclerView horizontal para categorías -- Falta probar funcionamiento
        rvCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategorias.setAdapter(new CategoriasAdapter(listaCategorias));

        // Agregamos el SnapHelper para que el efecto sea de "snap" en cada ítem -- Falta probar funcionamiento
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvCategorias);

        // Configuramos el RecyclerView horizontal para libros destacados -- Falta probar funcionamiento
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
}
