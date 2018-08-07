package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.controllers.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class BaseController  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // to forward to a view layer
    protected void forward(String url, HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    // to forward back with an error message
    protected void forwardError(String url, String message, HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        // to put a message into the request scope
        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
        forward(url, request, response);
    }
}