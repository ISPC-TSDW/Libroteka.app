package com.example.libroteka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriaViewHolder> {

    private final List<Categoria> categorias;
    private final OnCategoriaClickListener listener;

    // Interfaz para manejar los clics en las categorías
    public interface OnCategoriaClickListener {
        void onCategoriaClick(Categoria categoria);
    }

    // Constructor que recibe la lista de categorías y el listener de clic
    public CategoriasAdapter(List<Categoria> categorias, OnCategoriaClickListener listener) {
        this.categorias = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.imgCategoria.setImageResource(categoria.getImagen());
        holder.txtNombreCategoria.setText(categoria.getNombre());

        // Manejador de clic en la categoría
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCategoriaClick(categoria);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCategoria;
        TextView txtNombreCategoria;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            imgCategoria = itemView.findViewById(R.id.imgCategoria);
            txtNombreCategoria = itemView.findViewById(R.id.tvNombreCategoria);
        }
    }
}



