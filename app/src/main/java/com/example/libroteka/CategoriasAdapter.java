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

    // Constructor para pasar la lista de categorías
    public CategoriasAdapter(List<Categoria> categorias) {
        this.categorias = categorias;
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


