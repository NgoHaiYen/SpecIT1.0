package controller;

import database.*;
import model.*;
import utils.Constant;
import utils.Mail;

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
            if (!type.equalsIgnoreCase("deadline") && !type.equalsIgnoreCase("relater")){
                id = Integer.parseInt(request.getParameter("changeValue"));
            }
            String comment = request.getParameter("comment");
            Mail mail = new Mail();
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
                    String body = "Yêu cầu " + rdb.getName(requestid) + " của bộ phận của bạn đã được chuyển tới bộ phận IT khác";
                    String previousBranchLeaderMail = rdb.getLeaderBranchMail(requestid);
                    mail.sendMail(previousBranchLeaderMail, body, "Thay đổi bộ phận IT");

                    // todo send mail to the assigned employees

                    rdb.updateRequestBranch(requestid, id);

                    body = "Yêu cầu mới đã được chuyển tới bộ phận của bạn";
                    mail.sendMail(bdb.getLeaderEmail(id), body, "Thay đổi bộ phận IT");
                    break;
                case "deadline":
                    // todo comment type=3

                    // todo mail send mail to the assign employee
                    String date = request.getParameter("changeValue");
                    rdb.updateRequestDeadline(requestid, Constant.formatDateToSqlFromView(date));

                    // add comment
                    c.setRequestId(requestid);
                    c.setContent("Thay đổi deadline cho yêu cầu " + rdb.getName(requestid) +
                          ": chuyển đến ngày " + date);
                    c.setNote("Lý do: " + comment);
                    c.setEmployeeId(userId);
                    c.setType(2);
                    cdb.addComment(c);
                    break;
                case "relater":
                    // todo mail
                    String[] relater = request.getParameterValues("relater");
                    ArrayList<Integer> relatersId = new ArrayList<>();
                    for (String rl:relater){
                        relatersId.add(Integer.parseInt(rl));
                    }
                    RelaterDb relaterDb = new RelaterDb();
                    relaterDb.updateRelater(relatersId, id);

                    break;
                case "assigned":
                    // todo mail send mail to the old and new employee
                    rdb.updateRequestAssign(requestid, id);
                    break;
                case "status":
                    // todo mail send mail to the assigned employee
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