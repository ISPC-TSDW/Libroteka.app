package com.example.libroteka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.ViewHolder> {

    private final List<Libro> listaLibros;

    // Constructor para pasar la lista de libros
    public LibrosAdapter(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Libro libro = listaLibros.get(position);
        holder.tvTitulo.setText(libro.getTitulo());
        holder.ivImagen.setImageResource(libro.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitulo;
        public ImageView ivImagen;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloLibro);
            ivImagen = itemView.findViewById(R.id.imgLibro);
        }
    }
}


