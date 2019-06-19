package ua.com.foxminded.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.dao.implementation.StudentDAOHibernate;
import ua.com.foxminded.dao.implementation.StudentDAOImpl;
import ua.com.foxminded.domain.Student;

@WebServlet("/student")
public class StudentReadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAOHibernate();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = null;
        Student student = null;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            student = studentDAO.findOne(id);
        }
        request.setAttribute("student", student);
        request.getRequestDispatcher("/student.jsp").forward(request, response);
    }

}
