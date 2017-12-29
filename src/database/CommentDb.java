package database;

import model.Comment;

import java.sql.Connection;
import java.sql.SQLException;
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
        // TODO
        return null;
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
