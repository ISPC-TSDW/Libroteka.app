package com.example.libroteka.data;

public class FavoriteRequest {
        private String id_user;
        private Integer id_book;

        public FavoriteRequest(String id_user, Integer id_book) {
            this.id_user = id_user;
            this.id_book = id_book;
        }

    public Integer getId_book() {
            return id_book;
    }

}
