package com.example.libroteka;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libroteka.data.BookResponse;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private final List<BookResponse> listaLibros;

    // Constructor para pasar la lista de libros
    public BookAdapter(List<BookResponse> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destacado, parent, false);
        return new ViewHolder(view);  // Return the ViewHolder with the inflated view
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookResponse libro = listaLibros.get(position);
        holder.tvTitulo.setText(libro.getTitle());
        holder.ivImagen.setImageResource(R.drawable.ic_book_dracula);  // Static image, you can customize it
        if (libro.getPrice() != null) {
            holder.tvPrice.setText("$" + libro.getPrice().toString());
        } else {
            holder.tvPrice.setText("N/A");  // Set a default value
        }
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitulo;
        public ImageView ivImagen;
        public TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize your views here
            tvTitulo = itemView.findViewById(R.id.tvTituloLibro);
            ivImagen = itemView.findViewById(R.id.imgLibro);
            tvPrice = itemView.findViewById(R.id.tvPrecioLibro);
        }
    }
}