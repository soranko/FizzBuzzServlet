package fi.soranko.kalle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "FizzBuzzServlet", urlPatterns = {"/FizzBuzzServlet"})
public class FizzBuzzServlet extends HttpServlet {

    List<String> history = new ArrayList<>();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @author Soranko, Kalle
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<String> numbers;
        List<Integer> inputs = new ArrayList<>();
        boolean virhe = false;
        try {
            String number = request.getParameter("input");
            if (number.contains(",")) {
                numbers = Arrays.asList(number.split(","));
                for (String n : numbers) {
                    inputs.add(Integer.parseInt(n));
                }
            } else if (number != null) {
                inputs.add(Integer.parseInt(number));
            } else {
                inputs.add(0);
            }
        } catch (Exception e) {
            virhe = true;
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FizzBuzzServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>FizzBuzz</h1>");
            out.println("<p>Input one number or numbers separated with commas.</p>");
            out.println("<p>" + "Last result:" + "</p>");
            if (!virhe) {
                for (int i : inputs) {
                    String muunnos = String.format("%d -> %s", i, fizzBuzz(i));
                    out.println("<p>");
                    out.println(muunnos);
                    out.println("</p>");
                    history.add(muunnos);
                }
            } else {
                out.println("INCORRECT INPUT!");
            }
            out.println(this.fizzBuzzForm());
            out.println("<hr/>");
            out.println("History: ");
            for (String l : history) {
                out.println("<p>" + l + "</p>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Prints FizzBuzz form on HTML page.
     *
     * @return string that implements fizzBuzz form on HTML page
     * @author Soranko, Kalle
     */
    private String fizzBuzzForm() {
        StringBuffer sb = new StringBuffer();
        sb.append("<form method='POST' action='FizzBuzzServlet'>")
                .append("<p><input name='input' value='0'/></p>")
                .append("<p><input type='submit' value='FizzBuzz!'/></p>")
                .append("</form>");
        return sb.toString();
    }

    /**
     * Implements FizzBuzz trasformation  on a single integer.
     *
     * @param number
     * @return string according to fizzBuzz rules
     * @author Soranko, Kalle
     */
    protected String fizzBuzz(int number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= number; i++) {
            if (i % 15 == 0) {
                sb.append("FizzBuzz ");
            } else if (i % 3 == 0) {
                sb.append("Fizz ");
            } else if (i % 5 == 0) {
                sb.append("Buzz ");
            } else {
                sb.append(i + " ");
            }
        }
        return sb.toString().trim();
    }
}