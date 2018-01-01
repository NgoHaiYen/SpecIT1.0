package database;

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
            ResultSet rs = statement.executeQuery("select * from relater WHERE request_id =" +requestId + ";");
            while(rs.next()){
               Relater r = new Relater();

               r.setEmployeeId(rs.getInt("employee_id"));
               r.setRequestId(rs.getInt("request_id"));
               r.setCreatedAt(rs.getString("created_at"));
               relaters.add(r);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return relaters;
    }

    // insert a list of relater to a request
    public void addRelaters(ArrayList<Integer> relatersId, int requestId){
        for (Integer aRelatersId : relatersId) {
            addRelater(aRelatersId, requestId);
        }
    }

    // insert a relater to a request
    private void addRelater(int relaterId, int requestId) {
        // todo send mail to the relater

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "INSERT INTO relater(request_id, employee_id, created_at) VALUE (?,?,CURRENT_TIMESTAMP);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,requestId);
            statement.setInt(2,relaterId);
            statement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // delete all relaters to a request
    private void deleteAllRelater(int requestId){
        // todo send mail to the relater who is deleted

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "DELETE FROM relater WHERE request_id = ? ;";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,requestId);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update relater of a request
    // delete all relation to a req_id
    // insert new ones to db
    public void updateRelater(ArrayList<Integer> relatersId, int requestId){
        deleteAllRelater(requestId);

        addRelaters(relatersId,requestId);
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
