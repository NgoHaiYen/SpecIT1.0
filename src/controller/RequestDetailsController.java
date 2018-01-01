package controller;

import database.*;
import model.*;

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

            // branched
            ArrayList<Branch> branches = bdb.getAllBranch();
            request.setAttribute("branches", branches);

            // employees
            ArrayList<Employee> employees = edb.getAllEmployeeNameAndId();
            request.setAttribute("employees", employees);

            rdb.closeConnection();
            cdb.closeConnection();
            pdb.closeConnection();
            bdb.closeConnection();
            edb.closeConnection();
            relaterDb.closeConnection();
            request.getRequestDispatcher("jsp/request_details.jsp").forward(request, response);
        }

        String type = request.getParameter("typename");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        if (type != null){
            int requestid = Integer.parseInt(request.getParameter("requestid"));
            int id = 0;
            if (!type.equalsIgnoreCase("deadline") && !type.equalsIgnoreCase("relater")){
                id = Integer.parseInt(request.getParameter("changeValue"));
            }
            String comment = request.getParameter("comment");
            Comment c = new Comment();
            switch (type){
                case "priority":
                    String before = rdb.getRequestById(requestid).getPriorityName();

                    // add comment
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
                    break;
                case "deadline":
                    String date = request.getParameter("changeValue");
                    rdb.updateRequestDeadline(requestid, date);

                    // add comment
                    c.setRequestId(requestid);
                    c.setContent("Thay đổi deadline cho yêu cầu " + rdb.getName(requestid) +
                          ": chuyển đến ngày " + date);
                    c.setNote("Lý do: " + comment);
                    c.setEmployeeId(userId);
                    c.setType(3);
                    cdb.addComment(c);
                    break;
                case "relater":
                    String[] relater = request.getParameterValues("changeValue");
                    ArrayList<Integer> relatersId = new ArrayList<>();
                    for (String rl:relater){
                        relatersId.add(Integer.parseInt(rl));
                    }
                    RelaterDb relaterDb = new RelaterDb();
                    relaterDb.updateRelater(relatersId, id);

                    break;
                case "assigned":
                    rdb.updateRequestAssign(requestid, id);
                    break;
                case "status":
                    rdb.updateRequestStatus(requestid, id);
                    break;
                default:
                    break;
            }

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

            rdb.closeConnection();
            cdb.closeConnection();
            pdb.closeConnection();
            bdb.closeConnection();
            edb.closeConnection();
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