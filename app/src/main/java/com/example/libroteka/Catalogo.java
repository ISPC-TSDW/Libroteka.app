package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private RecyclerView recyclerViewLibros;
    private LibrosAdapter librosAdapter;
    private List<Libro> listaLibrosOriginales; // Para almacenar la lista original de libros
    private List<Libro> listaLibrosFiltrados;  // Para almacenar la lista filtrada de libros
    private EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);


        // Icono del perfil y listener para navegar a ProfileActivity
        ImageView profileImageView = findViewById(R.id.icon_profile);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile(); // Navegamos a ProfileActivity
            }
        });

        // Inicializamos el RecyclerView para los libros
        recyclerViewLibros = findViewById(R.id.recycler_view_libros);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columnas para libros
        recyclerViewLibros.setLayoutManager(gridLayoutManager);

        // Inicializamos el campo de búsqueda
        searchInput = findViewById(R.id.search_input);

        // Cargamos los libros originales (sin filtrar)
        cargarLibrosOriginales();

        // Cargamos los libros por defecto
        cargarLibrosFiltrados(null);

        // Configuramos la navegación inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_categories) {
                return true;
            } else if (id == R.id.navigation_home) {
                goToHome();
                return true;
            } else if (id == R.id.navigation_favorites) {
                goToFavourites();
                return true;
            }
            return false;
        });

        // Icono de filtro
        findViewById(R.id.icon_filter).setOnClickListener(v -> mostrarFiltroCategorias());

        // Icono de volver atrás
        findViewById(R.id.icon_back).setOnClickListener(v -> onBackPressed());

        // Implementación de la funcionalidad de búsqueda
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchInput.getText().toString();
            buscarLibros(query);  // Filtra los libros según el texto ingresado
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reinicia los libros cargados sin filtrar cuando vuelves a la actividad
        cargarLibrosFiltrados(null);
    }

    // Método para cargar libros sin filtrar (originales)
    private void cargarLibrosOriginales() {
        listaLibrosOriginales = new ArrayList<>();
        listaLibrosOriginales.add(new Libro("Romeo y Julieta", R.drawable.ic_book_romeoyjulieta, "Drama"));
        listaLibrosOriginales.add(new Libro("Hamlet", R.drawable.ic_book_hamlet, "Drama"));
        listaLibrosOriginales.add(new Libro("Cumbres borrascosas", R.drawable.ic_book_cumbresborrascosas, "Drama"));
        listaLibrosOriginales.add(new Libro("El silencio de lo perdido", R.drawable.ic_book_elsilenciodeloperdido, "Suspenso"));
        listaLibrosOriginales.add(new Libro("El resplandor", R.drawable.ic_book_elresplandor, "Suspenso"));
        listaLibrosOriginales.add(new Libro("La hora azul", R.drawable.ic_book_lahoraazul, "Suspenso"));
        listaLibrosOriginales.add(new Libro("Harry Potter y la Piedra Filosofal", R.drawable.ic_book_harrypotterylapiedrafilosofal, "Fantasia"));
        listaLibrosOriginales.add(new Libro("El hobbit", R.drawable.ic_book_elhobbit, "Fantasia"));
        listaLibrosOriginales.add(new Libro("Juego de tronos", R.drawable.ic_book_juegodetronos, "Fantasia"));
        listaLibrosOriginales.add(new Libro("El principito", R.drawable.ic_book_elprincipito, "Juvenil"));
        listaLibrosOriginales.add(new Libro("Los juegos del hambre", R.drawable.ic_book_losjuegosdelhambre, "Juvenil"));
        listaLibrosOriginales.add(new Libro("Divergente", R.drawable.ic_book_divergente, "Juvenil"));
        listaLibrosOriginales.add(new Libro("1984", R.drawable.ic_book_1984, "Ciencia ficción"));
        listaLibrosOriginales.add(new Libro("Fahrenheit 451", R.drawable.ic_book_fahrenheit451, "Ciencia ficción"));
        listaLibrosOriginales.add(new Libro("Dune", R.drawable.ic_book_dune, "Ciencia ficción"));
        listaLibrosOriginales.add(new Libro("Orgullo y prejuicio", R.drawable.ic_book_orgulloyprejuicio, "Romance"));
        listaLibrosOriginales.add(new Libro("Bajo la misma estrella", R.drawable.ic_book_bajolamismaestrella, "Romance"));
        listaLibrosOriginales.add(new Libro("After", R.drawable.ic_book_after, "Romance"));
        listaLibrosOriginales.add(new Libro("IT", R.drawable.ic_book_it, "Terror"));
        listaLibrosOriginales.add(new Libro("Drácula", R.drawable.ic_book_dracula, "Terror"));
        listaLibrosOriginales.add(new Libro("La llamada de Cthulhu", R.drawable.ic_book_lallamadadecthulhu, "Terror"));
    }

    // Método para cargar libros filtrados por categoría
    private void cargarLibrosFiltrados(String categoria) {
        listaLibrosFiltrados = new ArrayList<>(listaLibrosOriginales); // Copiamos la lista original
        if (categoria != null) {
            List<Libro> librosFiltrados = new ArrayList<>();
            for (Libro libro : listaLibrosOriginales) {
                if (libro.getCategoria().equals(categoria)) {
                    librosFiltrados.add(libro);
                }
            }
            listaLibrosFiltrados = librosFiltrados; // Reemplazamos por la lista filtrada
        }

        // Actualizamos el adaptador de libros
        librosAdapter = new LibrosAdapter(listaLibrosFiltrados);
        recyclerViewLibros.setAdapter(librosAdapter);
    }

    // Método para buscar libros por título
    private void buscarLibros(String query) {
        List<Libro> librosFiltradosPorBusqueda = new ArrayList<>();
        for (Libro libro : listaLibrosOriginales) {
            if (libro.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                librosFiltradosPorBusqueda.add(libro);
            }
        }

        // Actualizamos el RecyclerView con los resultados de la búsqueda
        librosAdapter = new LibrosAdapter(librosFiltradosPorBusqueda);
        recyclerViewLibros.setAdapter(librosAdapter);
    }

    // Método para mostrar el diálogo de filtro de categorías
    private void mostrarFiltroCategorias() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle("Seleccionar Categoría");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_filtro_categorias,  null);
        builder.setView(dialogView);

        RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroupCategorias);

        builder.setPositiveButton("Filtrar", (dialog, which) -> {
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = dialogView.findViewById(selectedRadioButtonId);
            String categoriaSeleccionada = selectedRadioButton.getText().toString();

            // Filtramos la lista según la categoría seleccionada
            cargarLibrosFiltrados(categoriaSeleccionada);
        });

        builder.setNegativeButton("Cancelar", null);

        builder.create().show();
    }

    // Método para navegar a ProfileActivity
    private void goToProfile() {
        Intent intent = new Intent(Catalogo.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void goToFavourites() {
        Intent intent = new Intent(Catalogo.this, FavoritosActivity.class); // Asegúrate de tener Catalogo.java creado
        startActivity(intent);
    }

    // Método para navegar a la pantalla de favoritos (agregar cuando esté lista)
    private void goToHome() {
        Intent intent = new Intent(Catalogo.this, Home.class); // Asegúrate que tienes ProfileActivity creada
        startActivity(intent);
    }
}











