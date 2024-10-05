package com.example.libroteka;

public class Libro {
    private final String titulo;
    private final int imagenResId;

    public Libro(String titulo, int imagenResId) {
        this.titulo = titulo;
        this.imagenResId = imagenResId;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}


