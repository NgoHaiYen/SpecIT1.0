package controller;

import database.CommentDb;
import database.RelaterDb;
import database.RequestDb;
import model.Comment;
import model.Employee;
import model.Relater;
import model.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RequestDetailsController")
public class RequestDetailsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("requestshow"));
        RequestDb rdb = new RequestDb();
        Request r = rdb.getRequestById(id);

        RelaterDb relaterDb = new RelaterDb();
        ArrayList<Relater> relaters = relaterDb.getAllRelater(id);

        CommentDb cdb = new CommentDb();
        ArrayList<Comment> comments = cdb.getAllComment(id);

        request.setAttribute("request", r);
        request.setAttribute("relaters", relaters);
        request.setAttribute("comments", comments);

        request.getRequestDispatcher("jsp/request_details.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/list");
        }
    }
}