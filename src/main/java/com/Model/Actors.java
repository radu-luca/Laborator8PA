package com.Model;

public class Actors {
    int idFilm;
    String name;

    public Actors(int idFilm, String name) {
        this.idFilm = idFilm;
        this.name = name;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
