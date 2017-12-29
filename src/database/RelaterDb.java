package database;

import model.Comment;
import model.Priority;
import model.Relater;

import java.sql.*;
import java.util.ArrayList;

public class RelaterDb {
    private Connection conn;

    public RelaterDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all relater of a request by request_id
    public ArrayList<Relater> getAllRelater(int requestId) {
        ArrayList<Relater> relaters = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from relater WHERE relater_id =" +requestId + ";");
            while(rs.next()){
               Relater r = new Relater();
               r.setEmployeeId(rs.getInt("employee_id"));
               r.setRequestId(rs.getInt("request_id"));
               r.setCreatedAt(rs.getDate("created_at"));
               relaters.add(r);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return relaters;


    }

    // insert a list of relater to a request
    public void addRelaters(ArrayList<Integer> relatersId, int requestId){
       //TODO

    }

    // insert a relater to a request
    public void addRelater(int relaterId, int requestId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "INSERT INTO relater(request_id, relater_id, created_at) VALUE (?,?,Null);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,requestId);
            statement.setInt(2,relaterId);
            statement.executeQuery(sqlS);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // delete a relater to a request
    private void deleteRelater(int relaterId, int requestId){
        // TODO
    }

    // update relater of a request
    public void updateRelater(ArrayList<Integer> relatersId, int requestId){
        // TODO
        // insert all new relaters
        // delete all relaters that are not contained in the given list
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
