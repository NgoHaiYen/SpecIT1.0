package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static Connection conn;
//    private static final String SERVER = "localhost";
//    private static final String PORT = "3306";
//    private static final String DATABASENAME = "calllogit";

    public static Connection getConnection(){
        try {
            String filepath = "database.properties";
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = loader.getResourceAsStream(filepath);
            Properties p = new Properties();
            p.load(is);

            String user = p.getProperty("user");
            String password = p.getProperty("password");
            String url = p.getProperty("url");
//            String url = "jdbc:mysql://"+SERVER+":"+PORT+"/"+DATABASENAME+"?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String [] args) {
        System.out.println(getConnection());  //check
    }
}
