package ua.com.foxminded.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.implementation.GroupDAOImpl;
import ua.com.foxminded.domain.Group;

@WebServlet("/view-group")
public class GroupViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = null;
        Group group = null;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            GroupDAOImpl groupDAO = new GroupDAOImpl();
            group = groupDAO.findOne(id);
        }
        request.setAttribute("group", group);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-group.jsp");
        dispatcher.forward(request, response);
    }

}
