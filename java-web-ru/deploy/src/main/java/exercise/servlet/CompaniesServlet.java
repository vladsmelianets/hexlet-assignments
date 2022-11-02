package exercise.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        String searchPattern = request.getParameter("search");
        List<String> result = getCompanies();
        if (searchPattern == null || searchPattern.isEmpty()) {
            result.forEach(out::println);
        } else {
            result = result.stream().filter(company -> company.contains(searchPattern)).toList();
            if (result.isEmpty()) {
                out.println("Companies not found");
            } else {
                result.forEach(out::println);
            }
        }
        // END
    }
}
