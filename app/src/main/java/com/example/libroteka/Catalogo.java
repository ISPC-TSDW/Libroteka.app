package com.example.libroteka;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private RecyclerView recyclerViewCatalogo;
    private RecyclerView recyclerViewLibros;
    private CategoriasAdapter categoriasAdapter;
    private LibrosAdapter librosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        // Inicializamos el RecyclerView para las categorías
        recyclerViewCatalogo = findViewById(R.id.recycler_view_catalogo);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewCatalogo.setLayoutManager(gridLayoutManager);

        // Inicializamos el RecyclerView para los libros
        recyclerViewLibros = findViewById(R.id.recycler_view_libros);
        GridLayoutManager librosGridLayoutManager = new GridLayoutManager(this, 2); // 2 columnas para libros
        recyclerViewLibros.setLayoutManager(librosGridLayoutManager);

        // Cargamos las categorías de ejemplo
        List<Categoria> listaCategorias = cargarCategorias();

        // Configuramos el adaptador para las categorías
        categoriasAdapter = new CategoriasAdapter(listaCategorias, new CategoriasAdapter.OnCategoriaClickListener() {
            @Override
            public void onCategoriaClick(Categoria categoria) {
                // Cargamos los libros según la categoría seleccionada
                cargarLibrosPorCategoria(categoria);
            }
        });
        recyclerViewCatalogo.setAdapter(categoriasAdapter);

        // Cargamos unos libros iniciales (por defecto)
        cargarLibrosPorCategoria(null); // Luego debemos cambair el "null" por la categoria defecto
    }

    // Método para cargar una lista de categorías de ejemplo
    private List<Categoria> cargarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("Romeo y Julieta", R.drawable.ic_category_placeholder));
        categorias.add(new Categoria("Corazón Delator", R.drawable.ic_category_placeholder));
        categorias.add(new Categoria("Harry Potter", R.drawable.ic_category_placeholder));
        categorias.add(new Categoria("El Mago de Oz", R.drawable.ic_category_placeholder));
        return categorias;
    }

    // Método para cargar libros por categoría
    private void cargarLibrosPorCategoria(Categoria categoria) {
        List<Libro> libros = new ArrayList<>();

        if (categoria == null || categoria.getNombre().equals("Romeo y Julieta")) {
            libros.add(new Libro("Romeo y Julieta", R.drawable.ic_book_placeholder));
            libros.add(new Libro("Hamlet", R.drawable.ic_book_placeholder));
        } else if (categoria.getNombre().equals("Corazón Delator")) {
            libros.add(new Libro("Corazón Delator", R.drawable.ic_book_placeholder));
            libros.add(new Libro("El Gato Negro", R.drawable.ic_book_placeholder));
        } else if (categoria.getNombre().equals("Harry Potter")) {
            libros.add(new Libro("Harry Potter y la Piedra Filosofal", R.drawable.ic_book_placeholder));
            libros.add(new Libro("Harry Potter y la Cámara Secreta", R.drawable.ic_book_placeholder));
        } else if (categoria.getNombre().equals("El Mago de Oz")) {
            libros.add(new Libro("El Mago de Oz", R.drawable.ic_book_placeholder));
            libros.add(new Libro("El Viaje a Oz", R.drawable.ic_book_placeholder));
        }

        // Configuramos el adaptador para los libros
        librosAdapter = new LibrosAdapter(libros);
        recyclerViewLibros.setAdapter(librosAdapter);
    }
}



