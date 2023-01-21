<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.lang.*, java.text.*, ua.lyashko.module4.service.StatService " %>
<html>
  <body>
       <i> <%
            StatService statService = StatService.getInstance ( );
            List<String> stringList = statService.statInfo ( );
            out.println ( "Races count: " + statService.countRaces ( ) );
            for (String s : stringList) {
                 out.println ( "<br>" + s );
             }
       %></i>
  </body>
</html>