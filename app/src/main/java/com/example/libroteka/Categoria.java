package com.example.libroteka;

public class Categoria {

    private final String nombre;
    private final int imagen;

    public Categoria(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}

