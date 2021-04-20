import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestPool {
    public static void main(String[] args)  {
       ThreadPoolExecutor executor =
                    (ThreadPoolExecutor) Executors.newFixedThreadPool(30);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();
            for (int i = 0; i < 30; i++) {
                Runnable task = new Runnable() {
                    public void run() {
                        try {
//                          List<Movie> listMovies = new MoviesDAO().getAllMovies(DBCPDataSource.getConnection());
                            Connection connection =  DBCPDataSource.getConnection();
                            connection.createStatement().executeQuery("Select * from actors");
                            connection.createStatement().executeQuery("Select * from actors");
                            connection.createStatement().executeQuery("Select * from actors");
                            connection.createStatement().executeQuery("Select * from actors");
                            connection.createStatement().executeQuery("Select * from actors");
                            connection.createStatement().executeQuery("Select * from actors");
                            connection.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                };
                executor.execute(task);
            }
       executor.shutdown();
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println(elapsedTime+" millis");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
//                executor.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            executor.shutdownNow();
//        }

    }
}
