<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.lang.*, java.text.*, ua.lyashko.module4.model.Horse, ua.lyashko.module4.model.Race, ua.lyashko.module4.service.HorseService, ua.lyashko.module4.service.RaceService, java.io.PrintWriter " %>
<html>
  <body>
       <i> <%
            HorseService horseService = HorseService.getInstance();
            RaceService raceService = RaceService.getInstance();
            String id = request.getParameter ( "id" );
            Race race = raceService.getById ( Integer.parseInt ( id ) );
            List<Horse> horseList = horseService.getHorsesFromDataBase ( Integer.parseInt ( id ) );
            out.println( "Race date: " + race.getDate ( ) + "<br>" );
            out.println( "Horses quantity: " + race.getQuantity ( ) + "<br>" );
            out.println( "Selected horse: " + race.getNumber ( ) + "<br>" );
            out.println( "Race result: ");
            for (Horse horse : horseList) {
             if (horse.getHorseNumber ( ) == race.getNumber ( )) {
                 out.println ( "<h4>" + horse + "</h4>" );
             } else {
                 out.println ( "<br>" + horse );
             }
         }
       %></i>
  </body>
</html>