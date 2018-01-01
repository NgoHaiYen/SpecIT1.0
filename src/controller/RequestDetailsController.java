package controller;

import com.google.gson.Gson;
import database.*;
import model.*;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RequestDetailsController")
public class RequestDetailsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDb rdb = new RequestDb();
        if (request.getParameter("requestshow") != null){
            int id = Integer.parseInt(request.getParameter("requestshow"));
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

        String type = request.getParameter("typename");
        System.out.println(type);
        int id = 0;
        if (type != null){
            int requestid = Integer.parseInt(request.getParameter("requestid"));
            switch(type){
                case "priority":
                    id = Integer.parseInt(request.getParameter("priorities"));
                    rdb.updateRequestPriority(requestid, id);
                    break;
                case "branch":
                    id = Integer.parseInt(request.getParameter("branches"));
                    rdb.updateRequestBranch(requestid, id);
                    break;
                case "deadline":
                    String date = request.getParameter("deadline");
                    rdb.updateRequestDeadline(requestid, Constant.formatDateToSql(date));
                    break;
                case "relater":
                    id = Integer.parseInt(request.getParameter("relater"));

                    break;
                case "assigned":
                    id = Integer.parseInt(request.getParameter("assign"));
                    rdb.updateRequestAssign(requestid, id);
                    break;
                case "status":
                    id = Integer.parseInt(request.getParameter("status"));
                    rdb.updateRequestStatus(requestid, id);
                    break;
                default:
                    break;
            }
//            String json = new Gson().toJson("");

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setHeader("Cache-control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Max-Age", "86400");
            out.println(id);

            out.close();
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