package com.Model;

public class Movies_genres {
    private int id_movies;
    private int id_genres;

    public Movies_genres(int id_movies, int id_genres) {
        this.id_movies = id_movies;
        this.id_genres = id_genres;
    }

    public int getId_movies() {
        return id_movies;
    }

    public void setId_movies(int id_movies) {
        this.id_movies = id_movies;
    }

    public int getId_genres() {
        return id_genres;
    }

    public void setId_genres(int id_genres) {
        this.id_genres = id_genres;
    }
}
