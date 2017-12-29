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
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "INSERT INTO comment(comment_id, request_id, employee_id, content, type, note, create_at, updated_at) " +
                    "VALUE (?,?,?,?,?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,comment.getId());
            statement.setInt(2,comment.getRequestId());
            statement.setInt(3,comment.getEmployeeId());
            statement.setString(4,comment.getContent());
            statement.setInt(5,comment.getType());
            statement.setString(6,comment.getNote());
            statement.setDate(7,comment.getCreatedAt());
            statement.setDate(8,comment.getUpdatedAt());
            statement.executeQuery(sqlS);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update comment
    public void updateComment(Comment comment){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "UPDATE comment set request_id = ?,employee_id =? ,content = ? ,type = ?,note = ?,create_at = ?,updated_at = ? WHERE  comment_id = ? ;";
            PreparedStatement statement = conn.prepareStatement(sqlS);

            statement.setInt(1,comment.getRequestId());
            statement.setInt(2,comment.getEmployeeId());
            statement.setString(3,comment.getContent());
            statement.setInt(4,comment.getType());
            statement.setString(5,comment.getNote());
            statement.setDate(6,comment.getCreatedAt());
            statement.setDate(7,comment.getUpdatedAt());
            statement.setInt(8,comment.getId());
            statement.executeQuery(sqlS);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
