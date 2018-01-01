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
        CommentDb cdb = new CommentDb();
        PriorityDb pdb = new PriorityDb();
        BranchDb bdb = new BranchDb();
        EmployeeDb edb = new EmployeeDb();

        if (request.getParameter("requestshow") != null){
            int id = Integer.parseInt(request.getParameter("requestshow"));
            Request r = rdb.getRequestById(id);

            RelaterDb relaterDb = new RelaterDb();
            ArrayList<Relater> relaters = relaterDb.getAllRelater(id);

            ArrayList<Comment> comments = cdb.getAllComment(id);

            request.setAttribute("request", r);
            request.setAttribute("relaters", relaters);
            request.setAttribute("comments", comments);

            // priorities
            ArrayList<Priority> priorities = pdb.getAllPriorities();
            request.setAttribute("priorities", priorities);

            // priorities
            ArrayList<Branch> branches = bdb.getAllBranch();
            request.setAttribute("branches", branches);

            // employees
            ArrayList<Employee> employees = edb.getAllEmployeeNameAndId();
            request.setAttribute("employees", employees);

            request.getRequestDispatcher("jsp/request_details.jsp").forward(request, response);
        }

        String type = request.getParameter("typename");
        System.out.println(type);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        if (type != null){
            int requestid = Integer.parseInt(request.getParameter("requestid"));
            int id = 0;
            if (!type.equalsIgnoreCase("deadline")){
                id = Integer.parseInt(request.getParameter("changeValue"));
            }
            String comment = request.getParameter("comment");
            switch (type){
                case "priority":
                    String before = rdb.getRequestById(requestid).getPriorityName();

                    // add comment
                    Comment c = new Comment();
                    c.setRequestId(requestid);
                    c.setContent("Thay đổi mức độ ưu tiên: " + before + " -> " + pdb.getPriorityNameById(id));
                    c.setNote("Lý do: " + comment);
                    c.setEmployeeId(userId);
                    c.setType(2);
                    cdb.addComment(c);

                    rdb.updateRequestPriority(requestid, id);
                    break;
                case "branch":
                    rdb.updateRequestBranch(requestid, id);
                    // todo mail
                    break;
                case "deadline":
                    // todo comment type=3
                    // todo mail
                    String date = request.getParameter("changeValue");
                    rdb.updateRequestDeadline(requestid, Constant.formatDateToSql(date));
                    break;
                case "relater":
                    // todo mail
                    id = Integer.parseInt(request.getParameter("relater"));

                    break;
                case "assigned":
                    // todo mail
                    rdb.updateRequestAssign(requestid, id);
                    break;
                case "status":
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