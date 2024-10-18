package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        // Icono para volver atrás
        ImageView backIcon = findViewById(R.id.icon_back);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para volver a la actividad anterior
                finish(); // Esto cierra la actividad actual y vuelve a la anterior
            }
        });

        // Icono para ir al perfil
        ImageView profileIcon = findViewById(R.id.icon_profile);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para ir a la actividad de perfil
                Intent intent = new Intent(Catalogo.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Configuración del RecyclerView para el catálogo
        RecyclerView recyclerViewCatalogo = findViewById(R.id.recycler_view_catalogo);
        recyclerViewCatalogo.setLayoutManager(new LinearLayoutManager(this));

        // Lista de categorías de ejemplo
        List<Categoria> listaCategorias = new ArrayList<>();
        listaCategorias.add(new Categoria("Acción", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Aventura", R.drawable.ic_category_placeholder));
        listaCategorias.add(new Categoria("Fantasía", R.drawable.ic_category_placeholder));

        // Establecemos el adaptador para el RecyclerView
        recyclerViewCatalogo.setAdapter(new CategoriasAdapter(listaCategorias));
    }
}
