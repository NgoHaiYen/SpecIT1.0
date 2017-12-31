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
                requests = requestDb.getAllSubteamRequest(id, status);
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
}