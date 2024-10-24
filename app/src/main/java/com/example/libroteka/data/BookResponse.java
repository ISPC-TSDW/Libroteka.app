package com.example.libroteka.data;

import android.content.Context;

public class BookResponse {
    private Integer id_Book;
    private String title;
    private Author id_Author;
    private Genre id_Genre;
    private Editorial id_Editorial;
    private String description;
    private Float price;
    private Float avg_rating;

    public BookResponse(Integer id_Book, String title, Author id_Author, Genre id_Genre,
                        Editorial id_Editorial, String description, Float price,
                        Float stock, Float avg_rating) {
        this.id_Book = id_Book;
        this.title = title;
        this.id_Author = id_Author;
        this.id_Genre = id_Genre;
        this.id_Editorial = id_Editorial;
        this.description = description;
        this.price = price;
        this.avg_rating = avg_rating;
    }

    // Getters
    public Integer getId_Book() {
        return id_Book;
    }

    public String getTitle() {
        return title;
    }

    public Author getId_Author() {
        return id_Author;
    }

    public String getAuthorName() {
        return id_Author != null ? id_Author.getName() : "Unknown Author";
    }

    public Genre getId_Genre() {
        return id_Genre;
    }

    public String getGenreName() {
        return id_Genre != null ? id_Genre.getName() : "Unknown Genre";
    }

    public Editorial getId_Editorial() {
        return id_Editorial;
    }

    public String getEditorialName() {
        return id_Editorial != null ? id_Editorial.getName() : "Unknown Editorial";
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public Float getAvg_rating() {
        return avg_rating;
    }

    // Generate the image resource name based on id_Book
    public String getImageName() {
        return "id_" + id_Book; // Example: id_1234
    }

    // You could add another method to get the resource ID (this is optional)
    public int getImageResourceId(Context context) {
        String imageName = getImageName();
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}