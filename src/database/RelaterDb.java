package database;

import model.Priority;

import java.sql.Connection;
import java.sql.SQLException;
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
    public ArrayList<Priority> getAllRelater(int requestId) {
        // TODO
        return null;
    }

    // insert a list of relater to a request
    public void addRelaters(ArrayList<Integer> relatersId, int requestId){
        // TODO
        // use addRelater
    }

    // insert a relater to a request
    public void addRelater(int relaterId, int requestId) {
        // TODO
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
