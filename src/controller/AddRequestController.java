package controller;

import database.*;
import model.Employee;
import model.Priority;
import model.Request;
import model.Subteam;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "AddRequestController")
@MultipartConfig
public class AddRequestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String button = request.getParameter("addrequest");
        if ("add".equals(button)) {
            boolean check = checkValidate(request);
            if(check) {
                uploadImageToServer(request.getPart("upload"), request);

                Request r = new Request();
                r.setSubject(request.getParameter("tencv"));
                r.setContent(request.getParameter("nd"));
                r.setCreatedBy((Integer) session.getAttribute("id"));
                r.setPriority(Integer.parseInt(request.getParameter("priorities")));
                r.setDeadline(request.getParameter("date"));
                r.setTeamId(Integer.parseInt(request.getParameter("subteams")));
                String[] relater = request.getParameterValues("relater");
                RequestDb rdb = new RequestDb();
                rdb.addNewRequest(r, relater);

                response.sendRedirect(request.getContextPath() + "/list");
            } else {
                request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        addDataForNavi(request);

        // priorities
        PriorityDb pdb = new PriorityDb();
        ArrayList<Priority> priorities = pdb.getAllPriorities();
        session.setAttribute("priorities", priorities);

        // itteams
        SubteamDb sdb = new SubteamDb();
        ArrayList<Subteam> subteams = sdb.getAllSubteams();
        session.setAttribute("subteams", subteams);

        // employees
        EmployeeDb edb = new EmployeeDb();
        ArrayList<Employee> employees = edb.getAllEmployee();
        session.setAttribute("employees", employees);

        request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
    }

    private boolean checkValidate(HttpServletRequest request) {
        ArrayList<String> values = new ArrayList<String>();
        values.add("tencv");
        values.add("priorities");
        values.add("date");
        values.add("subteams");
        values.add("nd");
        values.add("upload");

        String[] relater = request.getParameterValues("relater");
        return (relater != null) && checkValidate(request, values);
    }

    private boolean checkValidate(HttpServletRequest request, ArrayList<String> values){
        for (String value:values) {
            if (!checkValidate(request, value)) return false;
        }
        return true;
    }

    private boolean checkValidate(HttpServletRequest request, String value){
        String check = request.getParameter(value);
        if ((check == null) || (check.isEmpty())){
            return false;
        }
        return true;
    }

    private void uploadImageToServer(Part filePart, HttpServletRequest request) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).toString();
        if (checkImage(fileName)) {
            byte[] buffer = new byte[4096];
            long time = System.currentTimeMillis();
            fileName = fileName.split("\\.")[0] + time + "." + fileName.split("\\.")[1];
            fileName = request.getServletContext().getRealPath("") + "image/" + fileName;
            InputStream content = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(fileName);
            int bytesRead;
            while ((bytesRead = content.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            content.close();
        }
    }

    private boolean checkImage(String fileName) {
        String mimeType = getServletContext().getMimeType(fileName);
        if (mimeType.startsWith("image/")) {
            return true;
        }
        return false;
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

        RelaterDb relaterDb = new RelaterDb();
        session.setAttribute("rla", relaterDb.getNumberOfRequestRelate(id, Constant.ALL));
        session.setAttribute("rln", relaterDb.getNumberOfRequestRelate(id, Constant.NEW));
        session.setAttribute("rli", relaterDb.getNumberOfRequestRelate(id, Constant.IN_PROGRESS));
        session.setAttribute("rlr", relaterDb.getNumberOfRequestRelate(id, Constant.RESOLVED));
        session.setAttribute("rlc", relaterDb.getNumberOfRequestRelate(id, Constant.CLOSED));
        session.setAttribute("rlf", relaterDb.getNumberOfRequestRelate(id, Constant.FEEDBACK));
        session.setAttribute("rlo", relaterDb.getNumberOfRequestRelate(id, Constant.OUT_OF_DATE));

        session.setAttribute("ta", rdb.getNumberOfSubteamRequest(id, Constant.ALL));
        session.setAttribute("tn", rdb.getNumberOfSubteamRequest(id, Constant.NEW));
        session.setAttribute("ti", rdb.getNumberOfSubteamRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("tr", rdb.getNumberOfSubteamRequest(id, Constant.RESOLVED));
        session.setAttribute("tr", rdb.getNumberOfSubteamRequest(id, Constant.CLOSED));
        session.setAttribute("tr", rdb.getNumberOfSubteamRequest(id, Constant.FEEDBACK));
        session.setAttribute("to", rdb.getNumberOfSubteamRequest(id, Constant.OUT_OF_DATE));

        session.setAttribute("ita", rdb.getNumberOfTeamRequest(id, Constant.ALL));
        session.setAttribute("itn", rdb.getNumberOfTeamRequest(id, Constant.NEW));
        session.setAttribute("iti", rdb.getNumberOfTeamRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("itr", rdb.getNumberOfTeamRequest(id, Constant.RESOLVED));
        session.setAttribute("itr", rdb.getNumberOfTeamRequest(id, Constant.CLOSED));
        session.setAttribute("itr", rdb.getNumberOfTeamRequest(id, Constant.FEEDBACK));
        session.setAttribute("ito", rdb.getNumberOfTeamRequest(id, Constant.OUT_OF_DATE));
    }
}
