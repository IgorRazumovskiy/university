package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.dao.implementation.GroupDAOHibernate;
import ua.com.foxminded.dao.implementation.StudentDAOHibernate;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Student;

@WebServlet("/test")
public class GroupTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroupDAO groupDAO;
    private StudentDAO studentDAO;
    private String path;

    public void init() throws ServletException {
        super.init();
        groupDAO = new GroupDAOHibernate();
        studentDAO = new StudentDAOHibernate();
        path = getServletContext().getContextPath();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student1 = new Student("First");
        Student student2 = new Student("Second");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        Group group = new Group("Test", students);
        groupDAO.create(group);
        List<Group> groupList = groupDAO.findAll();
        request.setAttribute("groupList", groupList);
        request.getRequestDispatcher("/groups.jsp").forward(request, response);
    }

}
