package database;

import model.Comment;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ResultSet rs = statement.executeQuery("select * from comment");
            while(rs.next()){
               Comment c = new Comment();
               c.setId(rs.getInt("comment_id"));
               c.setContent(rs.getString("content"));
               c.setEmployeeId(rs.getInt("employee_id"));
               c.setNote(rs.getString("note"));
               c.setType(rs.getInt("type"));
               c.setCreatedAt(rs.getDate("created_at"));
               c.setRequestId(rs.getInt("request_id"));
               c.setUpdatedAt(rs.getDate("updated_at"));
               comments.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return comments;
    }

    // add new comment
    public void addComment(Comment comment){
        // TODO:
    }

    // update comment
    public void updateComment(Comment comment){
        // TODO:
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
