package com.DAO;

import com.Model.Directors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirectorsDAO {
    public Directors findByName(String name, Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "Select * from directors where name = " + name;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Directors director = new Directors(rs.getInt("id_movies"), rs.getString("name"));
        return director;
    }


    public boolean addDirector(Directors director,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "INSERT INTO directors(id_movies,name)" +
                " VALUES( '"+director.getIdFilm()+"', '"+director.getName()+ "')";
        return stm.execute(sql);
    }
    public boolean deleteDirector(Directors director,Connection connection) throws SQLException {
        Statement stm = connection.createStatement();
        String sql = "DELETE FROM directors where name='"+director.getName() + "'";
        return stm.execute(sql);
    }
}
