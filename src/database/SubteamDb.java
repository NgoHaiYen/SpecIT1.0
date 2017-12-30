package database;

import model.Subteam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubteamDb {
    private Connection conn;

    public SubteamDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all subteam
    public ArrayList<Subteam> getAllSubteams() {
        ArrayList<Subteam> teams = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM subteam;");
            while(rs.next()){
                Subteam team = new Subteam();
                team.setId(rs.getInt("subteam_id"));
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
}
