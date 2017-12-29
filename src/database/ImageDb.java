package database;

import model.Employee;
import model.Image;

import java.sql.*;
import java.util.ArrayList;

public class ImageDb {
    private Connection conn;

    public ImageDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all images of a request
    public ArrayList<Image> getAllImage(int requestId) {
        ArrayList<Image> images = new ArrayList<Image>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select url from image where request_id = ?";

            PreparedStatement statement = conn.prepareStatement(s + ";");
            statement.setInt(1, requestId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Image i = new Image();
                i.setRequestId(requestId);
                i.setUrl(rs.getString("url"));
                images.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return images;
    }

    // add new image
    public void addImage(Image image){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlS ="INSERT INTO image(request_id, url_image) VALUE (?,?);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,image.getRequestId());
            statement.setString(2,image.getUrl());
            statement.executeQuery(sqlS);


        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update image
    public void update(Image image){
        // TODO
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
