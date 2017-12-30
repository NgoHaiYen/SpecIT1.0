package database;

import model.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamDb {
    private Connection conn;

    public TeamDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all subteam
    public ArrayList<Team> getAllSubteams() {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM subteam;");
            while(rs.next()){
                Team team = new Team();
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
