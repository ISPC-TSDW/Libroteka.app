package com.example.libroteka;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    private final Context context; // Change this to final

    // Constructor for passing context and list of books
    public BookAdapter(Context context, List<BookResponse> listaLibros) {
        this.context = context; // Initialize the context
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_destacado, parent, false); // Use context here
        return new ViewHolder(view);  // Return the ViewHolder with the inflated view
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookResponse book = listaLibros.get(position);

        // Dynamically get the resource ID for the image
        int imageResId = context.getResources().getIdentifier(
                "id_" + book.getId_Book(), "drawable", context.getPackageName());

        // Set the book title
        holder.tvTitulo.setText(book.getTitle());

        // If the resource ID is valid, set the image resource
        if (imageResId != 0) {  // Check if the resource ID is found
            holder.ivImagen.setImageResource(imageResId);
        } else {
            // Use a default image if the resource ID is not found
            holder.ivImagen.setImageResource(R.drawable.ic_book_dracula);
        }

        // Set the price or a default value
        if (book.getPrice() != null) {
            holder.tvPrice.setText("$" + book.getPrice().toString());
        } else {
            holder.tvPrice.setText("N/A");  // Set a default value
        }

        // Set up the click listener to pass data to ProductoActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductoActivity.class);
            intent.putExtra("book_id", book.getId_Book());
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthorName());
            intent.putExtra("genre", book.getGenreName());
            intent.putExtra("editorial", book.getEditorialName());
            intent.putExtra("description", book.getDescription());
            intent.putExtra("price", book.getPrice());
            intent.putExtra("avg_rating", book.getAvg_rating());
            intent.putExtra("image_res_id", imageResId);  // Pass the image resource ID

            context.startActivity(intent);
        });
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