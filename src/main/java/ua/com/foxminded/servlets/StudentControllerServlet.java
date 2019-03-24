package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.implementation.StudentDAOImpl;
import ua.com.foxminded.domain.Student;

@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        List<Student> studentList = studentDAO.findAll();
        request.setAttribute("studentList", studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/students.jsp");
        dispatcher.forward(request, response);
    }

}
