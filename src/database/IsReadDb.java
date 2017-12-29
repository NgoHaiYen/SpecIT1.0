package database;

import model.IsRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<IsRead> getReadRequest(int readerId) {
        ArrayList<IsRead> isReads = new ArrayList<IsRead>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from isread where reader_id = ?";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, readerId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                IsRead i = new IsRead(readerId, rs.getInt("request_id"));
                isReads.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isReads;
    }

    public ArrayList<Integer> getReadRequestId(int userId){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select request_id from isread where reader_id = ?";

            PreparedStatement statement = conn.prepareStatement(s + ";");
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            ArrayList<Integer> ids = new ArrayList<Integer>();
            while(rs.next()){
                ids.add(rs.getInt("request_id"));
            }
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setRead(IsRead isRead) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement statement = conn.prepareStatement("insert into isread values (?, ?, ?)");
            statement.setInt(1, isRead.getRequestId());
            statement.setInt(2, isRead.getReaderId());
            statement.setInt(3, isRead.getStatus());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(IsRead i) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement statement = conn.prepareStatement("update isread "
                    + "set status = ? "
                    + " where reader_id = ? and request_id = ?;");
            statement.setInt(1, i.getStatus());
            statement.setInt(2, i.getReaderId());
            statement.setInt(3, i.getRequestId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isRead(int readerId, int requestId) {
        ArrayList<Integer> reads = getReadRequestId(readerId);
        for (int r:reads) {
            if (r == requestId){
                return true;
            }
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
