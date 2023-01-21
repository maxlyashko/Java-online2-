package ua.lyashko.module4.service;

import ua.lyashko.module4.model.Race;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class RaceService {
    private static RaceService instance;
    private static int counter;
    private static final Object lock = new Object ( );

    public synchronized static RaceService getInstance () {
        if (instance == null) {
            instance = new RaceService ( );
        }
        return instance;
    }

    public void increaseCounter () {
        synchronized (lock) {
            counter++;
        }
    }

    public int getCounter () {
        return counter;
    }

    public void saveRace ( int horseNumber , int horseQuantity ) {
        counter = initCounter ( );
        increaseCounter ( );
        try {
            Connection con = DataBaseService.initializeDatabase ( );
            PreparedStatement st = con.prepareStatement ( "insert into race (id, date, number, quantity) values ( ?, ?, ?, ? )" );
            st.setInt ( 1 , getCounter ( ) );
            st.setDate ( 2 , Date.valueOf ( LocalDate.now ( ) ) );
            st.setInt ( 3 , horseNumber );
            st.setInt ( 4 , horseQuantity );
            st.executeUpdate ( );
            st.close ( );
            con.close ( );
            System.out.println ( "inserted" );
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
    }

    public int initCounter () {
        counter = 0;
        try {
            Connection con = DataBaseService.initializeDatabase ( );
            Statement statement = con.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String temp = "SELECT MAX(id) FROM race ";
            ResultSet resultSet = statement.executeQuery ( temp );
            resultSet.first ( );
            String tempValue = String.valueOf ( resultSet.getInt ( "MAX(id)" ) );
            if (tempValue == null) {
                counter = 0;
            } else {
                counter = Integer.parseInt ( tempValue );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return counter;
    }

    public Race getById ( int id ) {
        Race race = new Race ( );
        try {
            Connection con = DataBaseService.initializeDatabase ( );
            Statement statement = con.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String temp = "SELECT * FROM race WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery ( temp );
            resultSet.first ( );
            race.setId ( Integer.parseInt ( resultSet.getString ( "id" ) ) );
            race.setDate ( Date.valueOf ( resultSet.getString ( "date" ) ) );
            race.setQuantity ( Integer.parseInt ( resultSet.getString ( "quantity" ) ) );
            race.setHorseList ( List.of ( ) );
            race.setNumber ( Integer.parseInt ( resultSet.getString ( "number" ) ) );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return race;
    }
}
