package exercise.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            showUsers(request, response);
        } else {
            String[] pathParts = pathInfo.split("/");
            String id = ArrayUtils.get(pathParts, 1, "");
            showUser(request, response, id);
        }
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String content = Files.readString(Path.of("src/main/resources/users.json"));
        return mapper.readValue(content, new TypeReference<List<Map<String, String>>>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Пользователи</title>
                    </head>
                    <body>
                        <table border="1" width="50%" cellpadding="3">
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
                            </tr>
                """);
        users.forEach(user -> {
            String fullName = user.get("firstName") + " " + user.get("lastName");
            String id = user.get("id");
            body.append("<tr>\n")
                    .append("<td>")
                    .append(id)
                    .append("</td>\n")
                    .append("<td>")
                    .append("<a href=\"/users/" + id + "\">" + fullName + "</a>")
                    .append("</td>\n")
                    .append("</tr>\n");
        });
        body.append("""
                  </table>
                    </body>
                    </html>  
                """);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(body);
        // END
    }

    private void showUser(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        // BEGIN
        List<Map<String, String>> users = getUsers();
        Optional<Map<String, String>> userOpt = users.stream().filter(map -> map.get("id").equals(id)).findFirst();
        if (userOpt.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        var user = userOpt.get();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Пользователи</title>
                    </head>
                    <body>
                        <table border="1" width="50%" cellpadding="3">
                            <tr>
                                <th>ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                            </tr>
                            <tr>
                """);
        body.append("<td>")
                .append(user.get("id"))
                .append("</td>\n");
        body.append("<td>")
                .append(user.get("firstName"))
                .append("</td>\n");
        body.append("<td>")
                .append(user.get("lastName"))
                .append("</td>\n");
        body.append("<td>")
                .append(user.get("email"))
                .append("</td>\n");
        body.append("""
                  </tr>
                  </table>
                    </body>
                    </html>  
                """);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(body);
        // END
    }
}
