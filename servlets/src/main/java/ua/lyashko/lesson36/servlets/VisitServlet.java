package ua.lyashko.lesson36.servlets;

import com.google.gson.Gson;
import ua.lyashko.lesson36.model.Visit;
import ua.lyashko.lesson36.service.StatService;
import ua.lyashko.lesson36.service.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "VisitServlet",
        urlPatterns = {"/visits"}
)
public class VisitServlet extends HttpServlet {
    private VisitService visitService;
    private StatService statService;
    private final Gson gson = new Gson ( );

    @Override
    public void init () throws ServletException {
        visitService = VisitService.getInstance ( );
        statService = StatService.getInstance ( );
        System.out.println ( getServletName ( ) + "initialized" );
    }

    @Override
    protected void doGet ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        statService.increaseCounter ( );
        PrintWriter writer = resp.getWriter ( );
        writer.print ( gson.toJson ( visitService.getAll ( ) ) );
    }

    @Override
    protected void doPost ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        statService.increaseCounter ( );
        Visit visit = gson.fromJson ( req.getReader ( ) , Visit.class );
        if (visit == null || visit.getName ( ) == null || visit.getDate ( ) == null || visit.getCity ( ) == null) {
            resp.sendError ( 400 );
        } else {
            visitService.removeFirst ();
            visitService.add ( visit );
            resp.setStatus ( 201 );
        }
    }

    @Override
    public void destroy () {
        System.out.println ( getServletName ( ) + "destroyed" );
    }
}
