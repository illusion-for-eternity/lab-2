package ua.kpi.controller.command;

import ua.kpi.exception.ApplicationException;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.ErrorsMessages;
import ua.kpi.utils.PagesHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    public static final String REDIRECT = "redirect";

    private CommandHolder commandHolder;

    @Override
    public void init() {
        commandHolder = new CommandHolder();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandKey = getMethod(request) + CommandHolder.DELIMITER + getPath(request);
        Command command = commandHolder.getCommand(commandKey);
        checkIfErrorIsPresent(request);
        executeCommand(request, response, command);
    }

    private void executeCommand(HttpServletRequest request,
                                HttpServletResponse response,
                                Command command)
            throws IOException, ServletException {

        try {
            String path = command.execute(request, response);
            if (!isRedirected(path)) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
            request.setAttribute(AttributesHolder.ERROR_MESSAGE, e.getMessageKey());
            request.getRequestDispatcher(PagesHolder.PAGE_NOT_FOUND).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(AttributesHolder.ERROR_MESSAGE, ErrorsMessages.NOT_EXCEPTED_ERROR);
            request.getRequestDispatcher(PagesHolder.PAGE_NOT_FOUND).forward(request, response);
        }
    }

    private void checkIfErrorIsPresent(HttpServletRequest request) {
        request.setAttribute(AttributesHolder.ERROR_MESSAGE,
                request.getParameter(AttributesHolder.ERROR_MESSAGE));
    }

    private boolean isRedirected(String path) {
        return REDIRECT.equals(path);
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith("/app")) {
            path = path.substring("/app".length());
        }

        if (path.matches(".*/\\d+$")) {
            path = path.substring(0, path.lastIndexOf("/"));
        }

        System.out.println("PATH: " + path);

        return path;
    }

    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }
}
