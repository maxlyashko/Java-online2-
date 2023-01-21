package ua.lyashko.module4.servlets;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "raceIdServlet",
        urlPatterns = {"/race/id"}
)
public class raceIdServlet extends HttpServlet {


    @Override
    public void init () throws ServletException {
        System.out.println ( getServletName ( ) + "initialized" );
    }

    @SneakyThrows
    @Override
    protected void doGet ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        resp.setContentType ( "text/html" );
        String id = req.getParameter ( "id" );
        resp.sendRedirect ( "http://localhost:8080/race/raceId.jsp?id=" + id );
    }

    @Override
    public void destroy () {
        System.out.println ( getServletName ( ) + "destroyed" );
    }
}
