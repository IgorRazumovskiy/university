package ua.com.foxminded.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.implementation.GroupDAOImpl;
import ua.com.foxminded.domain.Group;


@WebServlet("/group/delete")
public class GroupDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroupDAO groupDAO;
    private String path;

    public void init() throws ServletException {
        super.init();
        groupDAO = new GroupDAOImpl();
        path = getServletContext().getContextPath();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Group group = groupDAO.delete(id);
        response.sendRedirect(path + "/groups");
    }

}
