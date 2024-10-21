package com.example.libroteka;

public class Libro {
    private final String titulo;
    private final int imagenResId;
    private final String categoria; // Nuevo campo para categoría

    // Constructor que ahora incluye el parámetro de categoría
    public Libro(String titulo, int imagenResId, String categoria) {
        this.titulo = titulo;
        this.imagenResId = imagenResId;
        this.categoria = categoria;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public String getCategoria() {
        return categoria;
    }
}



