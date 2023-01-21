package ua.lyashko.module4;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import ua.lyashko.module4.service.DataBaseService;

import java.io.File;

public class Runner {
    public static void main ( String[] args ) throws Exception {
        DataBaseService dataBaseService = new DataBaseService ( );
        dataBaseService.createRaceDataBase ( );
        dataBaseService.createRaceHorseDataBase ( );

        String webappDirLocation = "module4/src/main/webapp/";
        Tomcat tomcat = new Tomcat ( );

        String webPort = System.getenv ( "PORT" );
        if (webPort == null || webPort.isEmpty ( )) {
            webPort = "8080";
        }

        tomcat.setPort ( Integer.parseInt ( webPort ) );

        StandardContext ctx = (StandardContext) tomcat.addWebapp ( "/" , new File ( webappDirLocation ).getAbsolutePath ( ) );
        System.out.println ( "configuring app with basedir: " + new File ( "./" + webappDirLocation ).getAbsolutePath ( ) );

        File additionWebInfClasses = new File ( "module4/target/classes" );
        WebResourceRoot resources = new StandardRoot ( ctx );
        resources.addPreResources ( new DirResourceSet ( resources , "/WEB-INF/classes" ,
                additionWebInfClasses.getAbsolutePath ( ) , "/" ) );
        ctx.setResources ( resources );

        tomcat.start ( );
        tomcat.getServer ( ).await ( );
    }
}
