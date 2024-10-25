package com.example.libroteka.data;

public class FavoriteRequest {
        private String id_user;
        private Integer id_book;
        private Integer id;

        public FavoriteRequest(String id_user, Integer id_book) {
            this.id_user = id_user;
            this.id_book = id_book;
            this.id = id;
        }

    public Integer getId_book() {
            return id_book;
    }
    public Integer getId() {return id;}

}
