package exercise.servlet;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static exercise.Data.getNextId;
import static exercise.Data.getUsers;

public class UsersServlet extends HttpServlet {

    public static final String INVALID_USER = "Имя и фамилия должны быть заполнены";
    private List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getFirstName(HttpServletRequest request) {
        return request.getParameter("firstName");
    }

    private String getLastName(HttpServletRequest request) {
        return request.getParameter("lastName");
    }

    private String getEmail(HttpServletRequest request) {
        return request.getParameter("email");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    private Map<String, String> getUserById(String id) {
        Map<String, String> user = users
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);

        return user;
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showUsers(request, response);
                break;
            case "new":
                newUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "show":
                showUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "new":
                createUser(request, response);
                break;
            case "edit":
                updateUser(request, response);
                break;
            case "delete":
                destroyUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        Map<String, String> user = new HashMap<>();
        request.setAttribute("user", user);
        request.setAttribute("error", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/new.jsp");
        dispatcher.forward(request, response);
        // END
    }

    private void createUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        Map<String,String> user = new HashMap<>();
        user.put("id", getNextId());
        user.put("firstName", getFirstName(request));
        user.put("lastName", getLastName(request));
        user.put("email", getEmail(request));

        if (isInvalid(user)) {
            request.setAttribute("user", user);
            request.setAttribute("error", INVALID_USER);
            response.setStatus(422);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        users.add(user);
        response.sendRedirect("/users");
        // END
    }

    private void editUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        request.setAttribute("user", user);
        request.setAttribute("error", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
        // END
    }

    private void updateUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        Map<String,String> updatedUser = new HashMap<>();
        updatedUser.put("id", getId(request));
        updatedUser.put("firstName", getFirstName(request));
        updatedUser.put("lastName", getLastName(request));
        updatedUser.put("email", getEmail(request));

        if (isInvalid(updatedUser)) {
            request.setAttribute("user", updatedUser);
            request.setAttribute("error", INVALID_USER);
            response.setStatus(422);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        users.remove(user);
        users.add(updatedUser);
        response.sendRedirect("/users");
        // END
    }

    private boolean isInvalid(Map<String, String> user) {
        return user.get("firstName")
                .isEmpty() || user.get("lastName")
                .isEmpty();
    }

    private void deleteUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void destroyUser(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        users.remove(user);
        response.sendRedirect("/users");
    }
}
