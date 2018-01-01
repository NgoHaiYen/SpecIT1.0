package database;

import model.IsRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsReadDb {
    private Connection conn;

    public IsReadDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isRead(int userId, int requestId){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select request_id from isread where reader_id = ? and request_id = ?";

            PreparedStatement statement = conn.prepareStatement(s + ";");
            statement.setInt(1, userId);
            statement.setInt(2, requestId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setRead(int userId, int requestId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement statement = conn.prepareStatement("insert into isread values (?, ?)");
            statement.setInt(1, requestId);
            statement.setInt(2, userId);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void renew(int requestId){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement statement = conn.prepareStatement("DELETE from isread where request_id = ?");
            statement.setInt(1, requestId);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
