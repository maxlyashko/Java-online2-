package ua.lyashko.module4.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatService {
    private static StatService instance;


    public synchronized static StatService getInstance () {
        if (instance == null) {
            instance = new StatService ( );
        }
        return instance;
    }

    public List<String> statInfo () {
        List<String> statList = new ArrayList<> ( );
        try {
            Connection con = DataBaseService.initializeDatabase ( );
            Statement statement = con.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String temp = """
                    SELECT race_id, horse_id , place
                    FROM race_horse, race
                    WHERE race.id = race_horse.race_id AND race.`number` = horse_id
                    """;
            ResultSet resultSet = statement.executeQuery ( temp );
            while ( resultSet.next ( ) ) {
                String raceId = resultSet.getString ( "race_id" );
                String horseId = resultSet.getString ( "horse_id" );
                String place = resultSet.getString ( "place" );
                String str = "Race ID: " + raceId + " Horse ID: " + horseId + " Place: " + place;
                statList.add ( str );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return statList;
    }

    public int countRaces () {
        int racesCounter = 0;
        try {
            Connection con = DataBaseService.initializeDatabase ( );
            Statement statement = con.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String temp = "SELECT COUNT(id)  from race ";
            ResultSet resultSet = statement.executeQuery ( temp );
            resultSet.first ( );
            String tempValue = String.valueOf ( resultSet.getInt ( "COUNT(id)" ) );
            if (tempValue == null) {
                racesCounter = 0;
            } else {
                racesCounter = Integer.parseInt ( tempValue );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return racesCounter;
    }
}
