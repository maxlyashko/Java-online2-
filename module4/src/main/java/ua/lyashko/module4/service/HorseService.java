package ua.lyashko.module4.service;

import ua.lyashko.module4.model.Horse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HorseService {
    private static HorseService instance;


    public synchronized static HorseService getInstance () {
        if (instance == null) {
            instance = new HorseService ( );
        }
        return instance;
    }

    public void saveHorses ( List<Horse> horses ) {
        try {
            for (int i = 0; i < horses.size ( ); i++) {
                Connection con = DataBaseService.initializeDatabase ( );
                PreparedStatement st = con.prepareStatement ( "insert into race_horse (race_id, horse_id, place) values ( ?, ?, ? )" );
                st.setInt ( 1 , RaceService.getInstance ( ).getCounter ( ) );
                st.setInt ( 2 , horses.get ( i ).getHorseNumber ( ) );
                st.setInt ( 3 , i + 1 );
                st.executeUpdate ( );
                st.close ( );
                con.close ( );
            }
            System.out.println ( "inserted" );
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
    }

    public List<Horse> getHorsesFromDataBase ( int id ) {
        List<Horse> horses = new ArrayList<> ( );
        Horse horse;
        try {
            Connection con = DataBaseService.initializeDatabase ( );
            Statement statement = con.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String temp = "SELECT horse_id , place  from race_horse WHERE race_id = " + id;
            ResultSet resultSet = statement.executeQuery ( temp );
            while ( resultSet.next ( ) ) {
                horse = new Horse ( );
                horse.setHorseNumber ( Integer.parseInt ( resultSet.getString ( "horse_id" ) ) );
                horse.setPlace ( Byte.parseByte ( resultSet.getString ( "place" ) ) );
                horses.add ( horse );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return horses;
    }


}
