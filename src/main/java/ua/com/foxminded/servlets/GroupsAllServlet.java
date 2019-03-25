package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.implementation.GroupDAOImpl;
import ua.com.foxminded.domain.Group;

@WebServlet("/groups")
public class GroupsAllServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupDAOImpl groupDAO = new GroupDAOImpl();
        List<Group> groupList = groupDAO.findAll();
        request.setAttribute("groupList", groupList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/groups.jsp");
        dispatcher.forward(request, response);
    }

}
