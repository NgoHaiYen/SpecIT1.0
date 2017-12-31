package controller;

import database.*;
import model.Employee;
import model.Priority;
import model.Request;
import model.Team;
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
                r.setDeadline(Constant.formatDate(request.getParameter("date")));
                r.setTeamId(Integer.parseInt(request.getParameter("subteams")));
                String[] relater = request.getParameterValues("relater");
                RequestDb rdb = new RequestDb();
                rdb.addNewRequest(r, relater);

                // todo mail
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

        // priorities
        PriorityDb pdb = new PriorityDb();
        ArrayList<Priority> priorities = pdb.getAllPriorities();
        session.setAttribute("priorities", priorities);

        // itteams
        TeamDb sdb = new TeamDb();
        ArrayList<Team> subteams = sdb.getAllSubteams();
        session.setAttribute("subteams", subteams);

        // employees
        EmployeeDb edb = new EmployeeDb();
        ArrayList<Employee> employees = edb.getAllEmployeeNameAndId();
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

        try {
            Part filePart = request.getPart("upload");
            if(Paths.get(filePart.getSubmittedFileName()).toString().equals("")) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

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

            // todo add image to db
        }
    }

    private boolean checkImage(String fileName) {
        String mimeType = getServletContext().getMimeType(fileName);
        if (mimeType.startsWith("image/")) {
            return true;
        }
        return false;
    }

}
