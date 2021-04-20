package com.DAO;

import com.Model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {
    public List<Movie> getAllMovies(Connection connection) throws SQLException {
        List<Movie> listMovies = new ArrayList<>();
        Statement stm = connection.createStatement();
        String sql = "Select * from movies";
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next())
        {
            Movie movie = new Movie(rs.getInt("id"), rs.getString("title"),
                    rs.getString("release_date"), rs.getInt("duration"), rs.getFloat("score"));
            listMovies.add(movie);
        }

        return listMovies;
    }

    public Movie findById(String id, Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "Select * from movies where id = " + id;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Movie movie = new Movie(rs.getInt("id"), rs.getString("title"),
                rs.getString("release_date"), rs.getInt("duration"), rs.getFloat("score"));
        return movie;
    }

    public Movie findByName(String name,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "Select * from movies where title = '" + name+ "'";
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Movie movie = new Movie(rs.getInt("id"), rs.getString("title"),
                rs.getString("release_date"), rs.getInt("duration"), rs.getFloat("score"));
        return movie;
    }

    public boolean addMovie(Movie movie,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "INSERT INTO movies(title,release_date,duration,score)" +
                " VALUES( '"+movie.getTitle()+"', '"+movie.getRelease_date()+ "', "+ String.valueOf(movie.getDuration())+
                ", " + String.valueOf(movie.getScore())+")";
        return stm.execute(sql);
    }
    public boolean deleteMovie(Movie movie,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "DELETE FROM movies where id="+String.valueOf(movie.getId());
        return stm.execute(sql);
    }
}
