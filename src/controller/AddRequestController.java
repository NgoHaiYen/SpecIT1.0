package controller;

import database.EmployeeDb;
import database.ItteamDb;
import database.PriorityDb;
import model.Employee;
import model.Itteam;
import model.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddRequestController")
public class AddRequestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // priorities
        PriorityDb pdb = new PriorityDb();
        ArrayList<Priority> priorities = pdb.getAllPriorities();
        session.setAttribute("priorities", priorities);

        // itteams
        ItteamDb idb = new ItteamDb();
        ArrayList<Itteam> itteams = idb.getAllItteams();
        session.setAttribute("itteams", itteams);

        // employees
        EmployeeDb edb = new EmployeeDb();
        ArrayList<Employee> employees = edb.getAllEmployee();
        session.setAttribute("employees", employees);

        request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
    }
}
