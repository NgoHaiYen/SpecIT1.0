package database;

import model.Priority;

import java.sql.*;
import java.util.ArrayList;

public class PriorityDb {
    private Connection conn;

    public PriorityDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all priority
    public ArrayList<Priority> getAllPriorities() {
        ArrayList<Priority> priorities = new ArrayList<Priority>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM priorities;");
            while(rs.next()){
                Priority p = new Priority();
                p.setId(rs.getInt("priority_id"));
                p.setName(rs.getString("name"));
                priorities.add(p);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priorities;
    }

    // get all priority name
    public ArrayList<String> getAllPrioritiesName() {
        ArrayList<String> prioritiesNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM priorities;");
            while(rs.next()){
                String s = rs.getString("name");

                prioritiesNames.add(s);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prioritiesNames;
    }

    // find priority_id
    public Integer getPriorityIdByName(String name){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select priority from priorities where name = ?";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getInt("priority_id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // find priority_name
    public String getPriorityNameById(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select name from priorities where priority_id = ?";
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

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
