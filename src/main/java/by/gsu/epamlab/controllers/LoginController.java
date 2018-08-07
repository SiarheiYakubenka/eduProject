package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.BaseController;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends BaseController {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        forward(Constants.JUMP_LOGIN, request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // to retrieve the form data
        String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
        String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);
        // to check out the form data
        if (login == null || password == null) {
            forwardError(Constants.JUMP_LOGIN, Constants.ERROR_NULL, request, response);
            return;
        }
        // to rectify and to check out the form data once more
        login = login.trim();
        password = password.trim();
        if (Constants.KEY_EMPTY.equals(login)
                || Constants.KEY_EMPTY.equals(password)) {
            forwardError(Constants.JUMP_LOGIN, Constants.ERROR_EMPTY, request, response);
            return;
        }

        // to create an instance of the business logic class
        IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);

        // to call the method and to get the result
        User user = userDAO.getUser(login, password);
        if (user == null) {
            forwardError(Constants.JUMP_LOGIN, Constants.ERROR_SOURCE, request, response);
            return;
        }
        if (user.getRole() != Role.FAKER) {
            HttpSession session = request.getSession();
            session.setAttribute(Constants.KEY_USER, user);
            // to forward to a view layer
            forward(Constants.NAME_PROJECT_ROOT, request, response);
        } else {
            // alternative workflow
            forwardError(Constants.JUMP_LOGIN, Constants.ERROR_PASSWORD, request, response);
        }
    }
}
