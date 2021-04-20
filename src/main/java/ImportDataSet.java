import com.DAO.ActorsDAO;
import com.DAO.DirectorsDAO;
import com.DAO.MoviesDAO;
import com.Model.Actors;
import com.Model.Directors;
import com.Model.Movie;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ImportDataSet {
    public static final String file = "C:\\Users\\radim\\OneDrive\\Desktop\\movies.csv";

    public static void importMovies(int limit, Connection connection) throws IOException, CsvValidationException, SQLException {
        FileReader filereader = new FileReader(file);
        MoviesDAO moviesDAO = new MoviesDAO();

        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;
        int i = 0, countId = 69;

        nextRecord = csvReader.readNext();
        while ((nextRecord = csvReader.readNext()) != null ) {
            String[] strings = new String[20];
            String[] strings1 = new String[20];
            String title="", release_date="";
            int duration = 0;
            float score =0;
            int index = 0;
            for (String cell : nextRecord) {
                switch (index) {
                    case 1:
                        title = cell;
                        break;
                    case 4:
                        release_date = cell;
                        break;
                    case 6:
                        duration = Integer.valueOf(cell);
                        break;
                    case 9:
                        strings = cell.split(",");
                        break;
                    case 12 :
                        strings1 = cell.split(",");
                        break;
                    case 14 :
                        score = Float.valueOf(cell);
                        break;
                    default:
                        break;
                }
                index++;
            }
            moviesDAO.addMovie(new Movie(countId,ImportDataSet.escapeApho(title),release_date,duration,score),connection);
            addDirectors(strings,moviesDAO.findByName(ImportDataSet.escapeApho(title),connection).getId(),connection);
            addActors(strings1,moviesDAO.findByName(ImportDataSet.escapeApho(title),connection).getId(),connection);
            countId++;
            i++;
        }
//        connection.commit();
    }

    public static void addDirectors(String[] directors, int idMovie, Connection connection) {
        try {
            DirectorsDAO directorsDAO = new DirectorsDAO();
            for (String name : directors) {
                Directors director = new Directors(idMovie, ImportDataSet.escapeApho(name));
                directorsDAO.addDirector(director, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addActors(String[] actors, int idMovie, Connection connection) {
        try {
            ActorsDAO actorsDAO = new ActorsDAO();
            for (String name : actors) {
                Actors actors1 = new Actors(idMovie,ImportDataSet.escapeApho(name));
                actorsDAO.addActor(actors1,connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String escapeApho(String string)
    {
        String aux = new String();
        for(int i=0; i < string.length(); ++i)
            if(string.charAt(i) == '\'')
            {
                aux+="\'\'";
            }
        else
            aux+=string.charAt(i);
        return aux;
    }



}