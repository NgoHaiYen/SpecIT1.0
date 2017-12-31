package database;

import model.Priority;
import model.Status;

import java.sql.*;
import java.util.ArrayList;

public class StatusDb {
    private Connection conn;

    public StatusDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all status
    public ArrayList<Status> getAllStatus() {
        ArrayList<Status> statuses = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM status;");
            while(rs.next()){
                Status s = new Status();
                s.setId(rs.getInt("status_id"));
                s.setName(rs.getString("name"));
                statuses.add(s);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    // get status name by id
    public String findById(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select name from status where status_id = ?";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("name");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
