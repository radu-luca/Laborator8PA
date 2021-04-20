package com.DAO;

import com.Model.Genres;
import com.Model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenresDAO {
    public List<Genres> getAllGenres(Connection connection) throws SQLException {
        List<Genres> listGenres = new ArrayList<>();
        Statement stm = connection.createStatement();
        String sql = "Select * from genres";
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next())
        {
            Genres genres = new Genres(rs.getInt("id"),rs.getString("name"));
            listGenres.add(genres);
        }
        return listGenres;
    }

    public Genres findById(String id,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "Select * from genres where id = " + id;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Genres genres = new Genres(rs.getInt("id"),rs.getString("name"));
        return genres;
    }

    public List<Movie> findMoviesByGenres(String name, Connection connection) throws SQLException {
        List<Movie> listMovies = new ArrayList<>();
        Statement stm = connection.createStatement();
        String sql = "Select id from genres where name ='" + name+ "'";
        ResultSet rs = stm.executeQuery(sql);
        MoviesDAO moviesDAO = new MoviesDAO();
        while(rs.next())
        {
            listMovies.add(moviesDAO.findById(rs.getString("id"),connection));
        }
        return listMovies;
    }
}
