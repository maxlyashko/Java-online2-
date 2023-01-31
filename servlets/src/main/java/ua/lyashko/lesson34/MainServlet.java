package ua.lyashko.lesson34;

import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.*;

public class MainServlet extends HttpServlet {
    Map<String, String> users = fillMap ( );

    @Serial
    private static final long serialVersionUID = -8948379822734246956L;

    @SneakyThrows
    @Override
    public void init ( ServletConfig config ) {
        super.init ( config );
    }

    @Override
    protected void doGet ( HttpServletRequest req , HttpServletResponse resp ) throws IOException {
        PrintWriter responseBody = resp.getWriter ( );
        String currentUser = req.getRemoteAddr ( ) + " " + req.getHeader ( "User-Agent" );
        users.put ( req.getRemoteAddr ( ) , req.getHeader ( "User-Agent" ) );

        resp.setContentType ( "text/html" );
        responseBody.println ( "<html>" );
        responseBody.println ( "<head> <title> user IP: </title> </head>" );
        for (String key : users.keySet ( )) {
            if (key.equals ( req.getRemoteAddr ( ) )) {
                responseBody.println ( "<b>" + currentUser + "<br></b>" );
            } else {
                responseBody.println ( "<p>" + key + " " + users.get ( key ) + "<br></p>" );
            }
        }
        responseBody.println ( "<html>" );

    }

    private Map<String, String> fillMap () {
        Map<String, String> userIp = new HashMap<> ( );
        userIp.put ( "0:0:0:0:0:0:0:2" , "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15" );
        userIp.put ( "0:0:0:0:0:0:0:3" , "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15" );
        userIp.put ( "0:0:0:0:0:0:0:4" , "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15" );
        userIp.put ( "0:0:0:0:0:0:0:5" , "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15" );
        userIp.put ( "0:0:0:0:0:0:0:6" , "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.1 Safari/605.1.15" );
        return userIp;
    }

    @Override
    public void destroy () {
        System.out.println ( getServletName ( ) + " destroyed" );
    }
}
