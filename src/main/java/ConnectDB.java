import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB objConnect = null;
    private static Connection connection;

    private ConnectDB() {
    }

    public static ConnectDB getInstance() {
        try {
            if (objConnect == null) {
                objConnect = new ConnectDB();
                connection = DriverManager.getConnection("jdbc:postgresql:movies", "postgres", "jora2012");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return objConnect;
    }

    public static Connection getConnection() {
        return connection;
    }
}
