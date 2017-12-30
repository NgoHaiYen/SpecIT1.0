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

        String t = request.getParameter("t");
        String k = request.getParameter("k");

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

        ArrayList<Request> requests;
        RequestDb requestDb = new RequestDb();

        switch (t){
            case "mya":
                requests = requestDb.getAllAssignRequest(id, status);
                break;
            case "r":
                requests = new ArrayList<>();
                break;
            case "t":
                requests = requestDb.getAllSubteamRequest(id, status);
                break;
            case "i":
                requests = requestDb.getAllTeamRequest(id, status);
                break;
            default:
                requests = requestDb.getAllRequest(id, status);
                break;
        }

        session.setAttribute("requests", requests);

        request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
    }
}