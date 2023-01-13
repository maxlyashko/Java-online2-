package ua.lyashko.lesson36.servlets;

import com.google.gson.Gson;
import ua.lyashko.lesson36.service.StatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(
        name = "StatServlet",
        urlPatterns = {"/statistics"}
)
public class StatServlet extends HttpServlet {
    private StatService statService;
    private final Gson gson = new Gson ( );

    @Override
    public void init () throws ServletException {
        statService = StatService.getInstance ( );
        System.out.println ( getServletName ( ) + "initialized" );
    }

    @Override
    protected void doGet ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        statService.increaseCounter ( );
        PrintWriter writer = resp.getWriter ( );
        writer.print ( "Quantity of requests: " + gson.toJson ( statService.getCounter ( ) ) );
        writer.print ( "<br> Current date and time: " + LocalDateTime.now ( ) );
    }

    @Override
    public void destroy () {
        System.out.println ( getServletName ( ) + "destroyed" );
    }
}
