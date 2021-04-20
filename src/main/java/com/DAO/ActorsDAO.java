package com.DAO;

import com.Model.Actors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActorsDAO {
    public Actors findByName(String name, Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "Select * from directors where name = " + name;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Actors actor = new Actors(rs.getInt("id_movies"), rs.getString("name"));
        return actor;
    }


    public boolean addActor(Actors actor,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "INSERT INTO actors(id_movies,name)" +
                " VALUES( '"+actor.getIdFilm()+"', '"+actor.getName() + "')";
        return stm.execute(sql);
    }
    public boolean deleteActor(Actors actor,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "DELETE FROM actors where name='"+actor.getName() + "'";
        return stm.execute(sql);
    }
}
