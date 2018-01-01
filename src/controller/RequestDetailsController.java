package controller;

import com.google.gson.Gson;
import database.*;
import model.*;

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
        if (request.getParameter("requestshow") != null){
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

            // priorities
            PriorityDb pdb = new PriorityDb();
            ArrayList<Priority> priorities = pdb.getAllPriorities();
            request.setAttribute("priorities", priorities);

            // priorities
            BranchDb bdb = new BranchDb();
            ArrayList<Branch> branches = bdb.getAllBranch();
            request.setAttribute("branches", branches);

            // employees
            EmployeeDb edb = new EmployeeDb();
            ArrayList<Employee> employees = edb.getAllEmployeeNameAndId();
            request.setAttribute("employees", employees);

            request.getRequestDispatcher("jsp/request_details.jsp").forward(request, response);
        }

        String type = request.getParameter("ajax");
        if (type != null){
//            String json = new Gson().toJson("");
//
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(type);
        }
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