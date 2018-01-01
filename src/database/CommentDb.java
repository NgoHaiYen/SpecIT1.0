package database;

import model.Comment;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class CommentDb {
    private Connection conn;

    public CommentDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all Comments of a request
    public ArrayList<Comment> getAllComment(int requestId) {
        ArrayList<Comment> comments =  new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from comment where request_id = " + requestId);
            while(rs.next()){
               Comment c = new Comment();
               c.setId(rs.getInt("comment_id"));
               c.setRequestId(rs.getInt("request_id"));
               c.setEmployeeId(rs.getInt("employee_id"));
               c.setContent(rs.getString("content"));
               c.setType(rs.getInt("type"));
               c.setNote(rs.getString("note"));
               c.setCreatedAt(rs.getString("create_at"));
               c.setRequestId(rs.getInt("request_id"));
               c.setUpdatedAt(rs.getString("updated_at"));
               comments.add(c);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return comments;
    }

    // add new comment
    public void addComment(Comment comment){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "INSERT INTO comment(request_id, employee_id, content, note, type, create_at) " +
                    "VALUE ( ? , ? , ? , ? , ? , CURRENT_TIMESTAMP);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,comment.getRequestId());
            statement.setInt(2,comment.getEmployeeId());
            statement.setString(3,comment.getContent());
            statement.setString(4,comment.getNote());
            statement.setInt(5,comment.getType());
            statement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update comment
    public void updateComment(Comment comment){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "UPDATE comment set content = ?, type = ?, note = ?, updated_at = CURRENT_TIMESTAMP " +
                    "WHERE  comment_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sqlS);

            statement.setString(1,comment.getContent());
            statement.setInt(2,comment.getType());
            statement.setString(3,comment.getNote());
            statement.setInt(4,comment.getId());
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
