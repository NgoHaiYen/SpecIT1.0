package controller;

import database.RequestDb;
import model.Request;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListController")
public class ListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        addDataForNavi(request);

        int id = (int) session.getAttribute("id");
        RequestDb requestDb = new RequestDb();
        ArrayList<Request> requests = requestDb.getAllRequest(id, Constant.ALL);

        String t = request.getParameter("t");
        String k = request.getParameter("k");
        if (k == null || t == null){
            session.setAttribute("requests", requests);
            request.setAttribute("listname", "Danh sách việc tôi yêu cầu");
            request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
            return;
        }

        int status = 0;

        switch (k){
            case "n":
                status = Constant.NEW;
                break;
            case "i":
                status = Constant.IN_PROGRESS;
                break;
            case "r":
                status = Constant.RESOLVED;
                break;
            case "f":
                status = Constant.FEEDBACK;
                break;
            case "c":
                status = Constant.CLOSED;
                break;
            case "o":
                status = Constant.OUT_OF_DATE;
                break;
            default:
                status = Constant.ALL;
                break;
        }

        switch (t){
            case "mya":
                request.setAttribute("listname", "Danh sách việc tôi được giao");
                requests = requestDb.getAllAssignRequest(id, status);
                break;
            case "r":
                request.setAttribute("listname", "Danh sách công việc liên quan");
                requests = new ArrayList<>();
                break;
            case "t":
                request.setAttribute("listname", "Danh sách công việc của team");
                requests = requestDb.getAllTeamRequest(id, status);
                break;
            case "i":
                request.setAttribute("listname", "Danh sách công việc của bộ phận IT");
                requests = requestDb.getAllTeamRequest(id, status);
                break;
            default:
                request.setAttribute("listname", "Danh sách việc tôi yêu cầu");
                break;
        }

        session.setAttribute("requests", requests);
        request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
    }

    private void addDataForNavi(HttpServletRequest request){
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        RequestDb rdb = new RequestDb();
        session.setAttribute("myra", rdb.getNumberOfRequest(id, Constant.ALL));
        session.setAttribute("myrn", rdb.getNumberOfRequest(id, Constant.NEW));
        session.setAttribute("myri", rdb.getNumberOfRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("myrr", rdb.getNumberOfRequest(id, Constant.RESOLVED));
        session.setAttribute("myrf", rdb.getNumberOfRequest(id, Constant.FEEDBACK));
        session.setAttribute("myrc", rdb.getNumberOfRequest(id, Constant.CLOSED));
        session.setAttribute("myro", rdb.getNumberOfRequest(id, Constant.OUT_OF_DATE));

        session.setAttribute("myaa", rdb.getNumberOfAssignRequest(id, Constant.ALL));
        session.setAttribute("myan", rdb.getNumberOfAssignRequest(id, Constant.NEW));
        session.setAttribute("myai", rdb.getNumberOfAssignRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("myar", rdb.getNumberOfAssignRequest(id, Constant.RESOLVED));
        session.setAttribute("myac", rdb.getNumberOfAssignRequest(id, Constant.CLOSED));
        session.setAttribute("myaf", rdb.getNumberOfAssignRequest(id, Constant.FEEDBACK));
        session.setAttribute("myao", rdb.getNumberOfAssignRequest(id, Constant.OUT_OF_DATE));

        session.setAttribute("rla", rdb.getNumberOfRequestRelate(id, Constant.ALL));
        session.setAttribute("rln", rdb.getNumberOfRequestRelate(id, Constant.NEW));
        session.setAttribute("rli", rdb.getNumberOfRequestRelate(id, Constant.IN_PROGRESS));
        session.setAttribute("rlr", rdb.getNumberOfRequestRelate(id, Constant.RESOLVED));
        session.setAttribute("rlc", rdb.getNumberOfRequestRelate(id, Constant.CLOSED));
        session.setAttribute("rlf", rdb.getNumberOfRequestRelate(id, Constant.FEEDBACK));
        session.setAttribute("rlo", rdb.getNumberOfRequestRelate(id, Constant.OUT_OF_DATE));

        session.setAttribute("ta", rdb.getNumberOfTeamRequest(id, Constant.ALL));
        session.setAttribute("tn", rdb.getNumberOfTeamRequest(id, Constant.NEW));
        session.setAttribute("ti", rdb.getNumberOfTeamRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("tr", rdb.getNumberOfTeamRequest(id, Constant.RESOLVED));
        session.setAttribute("tr", rdb.getNumberOfTeamRequest(id, Constant.CLOSED));
        session.setAttribute("tr", rdb.getNumberOfTeamRequest(id, Constant.FEEDBACK));
        session.setAttribute("to", rdb.getNumberOfTeamRequest(id, Constant.OUT_OF_DATE));

        session.setAttribute("ita", rdb.getNumberOfBranchRequest(id, Constant.ALL));
        session.setAttribute("itn", rdb.getNumberOfBranchRequest(id, Constant.NEW));
        session.setAttribute("iti", rdb.getNumberOfBranchRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("itr", rdb.getNumberOfBranchRequest(id, Constant.RESOLVED));
        session.setAttribute("itr", rdb.getNumberOfBranchRequest(id, Constant.CLOSED));
        session.setAttribute("itr", rdb.getNumberOfBranchRequest(id, Constant.FEEDBACK));
        session.setAttribute("ito", rdb.getNumberOfBranchRequest(id, Constant.OUT_OF_DATE));
    }

}