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
import java.util.HashSet;

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

//            RelaterDb relaterDb = new RelaterDb();
//            HashSet<Relater> relaters = relaterDb.getAllRelater(id);
//
////            HashSet<Comment> comments = cdb.getAllComment(id);
//
//            request.setAttribute("request", r);
//            request.setAttribute("relaters", relaters);
//            request.setAttribute("comments", comments);

            // priorities
//            HashSet<Priority> priorities = pdb.getAllPriorities();
//            request.setAttribute("priorities", priorities);

            // branched
//            HashSet<Branch> branches = bdb.getAllBranch();
//            request.setAttribute("branches", branches);

            // branched
//            TeamDb teamDb = new TeamDb();
//            HashSet<Team> subteams = teamDb.getAllTeams();
//            request.setAttribute("subteams", subteams);

            // employees
//            HashSet<Employee> employees = edb.getAllEmployee();
//            request.setAttribute("employees", employees);

            request.getRequestDispatcher("jsp/request_details.jsp").forward(request, response);
        }

        String type = request.getParameter("typename");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        if (type != null){
            int requestid = Integer.parseInt(request.getParameter("requestid"));
            int id = 0;
            if (!type.equalsIgnoreCase("deadline") && !type.equalsIgnoreCase("relater") && !type.equalsIgnoreCase("comment")){
                id = Integer.parseInt(request.getParameter("changeValue"));
            }
            String comment = request.getParameter("comment");
            Comment c = new Comment();
            switch (type){
                case "priority":
//                    String before = rdb.getRequestById(requestid).getPriorityName();

                    // add comment
                    c.setRequestId(requestid);
//                    c.setContent("Thay đổi mức độ ưu tiên: " + before + " -> " + pdb.getPriorityNameById(id));
                    c.setNote("Lý do: " + comment);
                    c.setEmployeeId(userId);
                    c.setType(2);
                    cdb.addComment(c);

//                    rdb.updateRequestPriority(requestid, id);
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
                    String relater = request.getParameter("changeValue");
                    System.out.println(relater);
//                    HashSet<Integer> relatersId = new HashSet<>();
//                    for (String rl : relater){
//                        relatersId.add(Integer.parseInt(rl));
//                    }
//                    RelaterDb relaterDb = new RelaterDb();
//                    relaterDb.updateRelater(relatersId, id);

                    break;
                case "assign":
                    rdb.updateRequestAssign(requestid, id);
                    break;
                case "status":
                    rdb.updateRequestStatus(requestid, id);
                    break;
                case "comment":
                    c.setRequestId(requestid);
                    c.setContent(request.getParameter("nd"));
                    c.setEmployeeId(userId);
                    c.setType(1);
                    cdb.addComment(c);

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
            out.println("");

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