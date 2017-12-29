package database;

import model.Employee;
import model.ITteam;
import model.Priority;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItteamDb {
    private Connection conn;

    public ItteamDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all itteam
    public ArrayList<ITteam> getAllItteams() {
        ArrayList<ITteam> teams = new ArrayList<ITteam>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM itteam;");
            while(rs.next()){
                ITteam team = new ITteam();
                team.setId(rs.getInt("itteam_id"));
                team.setName(rs.getString("name"));
                teams.add(team);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    // get all itteams name
    public ArrayList<String> getAllItteamsName() {
        ArrayList<String> teamNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM itteam;");
            while(rs.next()){
                String s = rs.getString("name");

                teamNames.add(s);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamNames;
    }


    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
