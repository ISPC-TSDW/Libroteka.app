package com.example.libroteka;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private RecyclerView recyclerViewCatalogo;
    private CategoriasAdapter categoriasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo); // El XML que compartiste

        // Inicializa el RecyclerView
        recyclerViewCatalogo = findViewById(R.id.recycler_view_catalogo);

        // Configura el RecyclerView con un GridLayoutManager para mostrar una cuadrícula
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columnas
        recyclerViewCatalogo.setLayoutManager(gridLayoutManager);

        // Cargar categorías de ejemplo
        List<Categoria> listaCategorias = cargarCategorias();

        // Configura el adaptador
        categoriasAdapter = new CategoriasAdapter(listaCategorias);
        recyclerViewCatalogo.setAdapter(categoriasAdapter);
    }

    // Método para cargar una lista de categorías de ejemplo
    private List<Categoria> cargarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("Romeo y Julieta", R.drawable.romeo_y_julieta, 239.80));
        categorias.add(new Categoria("Corazón Delator", R.drawable.corazon_delator, 239.80));
        categorias.add(new Categoria("Harry Potter", R.drawable.harry_potter, 239.80));
        categorias.add(new Categoria("El Mago de Oz", R.drawable.mago_de_oz, 239.80));
        return categorias;
    }
}

