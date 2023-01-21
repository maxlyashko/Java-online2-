package ua.lyashko.module4.servlets;

import ua.lyashko.module4.service.HippodromeService;
import ua.lyashko.module4.service.RaceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(
        name = "raceStartServlet",
        urlPatterns = {"/race/start"}
)
public class raceStartServlet extends HttpServlet {
    private HippodromeService hippodromeService;
    private RaceService raceService;
    int horseQuantity;
    int selectedHorse;

    @Override
    public void init () throws ServletException {
        hippodromeService = HippodromeService.getInstance ( );
        raceService = RaceService.getInstance ( );
        System.out.println ( getServletName ( ) + "initialized" );
    }

    @Override
    public void doGet ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        resp.setContentType ( "text/html" );
        RequestDispatcher ds = req.getRequestDispatcher ( "raceStart.html" );
        ds.include ( req , resp );
    }

    @Override
    public void doPost ( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
        resp.setContentType ( "text/html" );
        if (req.getParameter ( "quantity" ) == null || req.getParameter ( "number" ) == null) {
            RequestDispatcher ds = req.getRequestDispatcher ( "raceStart.html" );
            ds.include ( req , resp );
        } else {
            horseQuantity = Integer.parseInt ( ( req.getParameter ( "quantity" ) ) );
            selectedHorse = Integer.parseInt ( ( req.getParameter ( "number" ) ) );
            raceService.saveRace ( selectedHorse , horseQuantity );
            ExecutorService executorService = Executors.newFixedThreadPool ( horseQuantity );
            for (int i = 0; i < horseQuantity; i++) {
                executorService.submit ( new HippodromeService ( ) );
            }
            hippodromeService.saveHorses ( horseQuantity );
            executorService.shutdown ( );
            hippodromeService.flush ( );
            resp.sendRedirect ( "http://localhost:8080/race/id?id=" + raceService.getCounter ( ) );
        }
    }

    @Override
    public void destroy () {
        System.out.println ( getServletName ( ) + "destroyed" );
    }
}