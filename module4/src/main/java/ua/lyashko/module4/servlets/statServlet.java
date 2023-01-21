package ua.lyashko.module4.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "statServlet",
        urlPatterns = {"/stats"}
)
public class statServlet extends HttpServlet {

    @Override
    public void init () throws ServletException {
        System.out.println ( getServletName ( ) + "initialized" );
    }

    @Override
    protected void doGet ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        resp.setContentType ( "text/html" );
        resp.sendRedirect ( "http://localhost:8080/race/raceStat.jsp" );
    }

    @Override
    public void destroy () {
        System.out.println ( getServletName ( ) + "destroyed" );
    }
}
