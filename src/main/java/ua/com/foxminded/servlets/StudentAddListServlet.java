package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.dao.implementation.StudentDAOImpl;
import ua.com.foxminded.domain.Student;

@WebServlet("/students")
public class StudentAddListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student(request.getParameter("name"));
        studentDAO.create(student);
        response.sendRedirect("students");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> studentList = studentDAO.findAll();
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
    
}
