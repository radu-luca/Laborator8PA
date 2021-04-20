import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestSingleton {
    public static void main(String[] args)
    {
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(30);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long start2 = System.currentTimeMillis();
        ConnectDB connectDB = ConnectDB.getInstance();
        for (int i = 0; i < 30; i++) {
            Runnable task = new Runnable() {
                public void run() {
                    try {
//                        List<Movie> listMovies = new MoviesDAO().getAllMovies(connectDB.getConnection());
                        connectDB.getConnection().createStatement().executeQuery("Select * from actors");
                        connectDB.getConnection().createStatement().executeQuery("Select * from actors");
                        connectDB.getConnection().createStatement().executeQuery("Select * from actors");
                        connectDB.getConnection().createStatement().executeQuery("Select * from actors");
                        connectDB.getConnection().createStatement().executeQuery("Select * from actors");

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            };
            executor.execute(task);
        }

        executor.shutdown();
//        try {
//            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
//                executor.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            executor.shutdownNow();
//        }
        long end2 = System.currentTimeMillis();
        long elapsedTime2 = end2 - start2;
        System.out.println(elapsedTime2+" millis");
    }
}
