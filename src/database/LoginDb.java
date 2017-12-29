package database;

import model.IsRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDb {
    private Connection conn;

    public LoginDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(String userName, String password) {
        ArrayList<IsRead> isReads = new ArrayList<IsRead>();
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from employees where username = ? and password = md5(?)";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
