package ua.lyashko.module4.service;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseService {
    private static final Connection connection = initializeDatabase ();

    @SneakyThrows
    public static Connection initializeDatabase () {
        String dbDriver = "org.mariadb.jdbc.Driver";
        String dbURL = "jdbc:mariadb://localhost:3306/module3";
        String dbUsername = "root";
        String dbPassword = "root";

        Class.forName ( dbDriver );
        Connection con = DriverManager.getConnection ( dbURL , dbUsername , dbPassword );
        return con;
    }

    public void createRaceDataBase () {
        try {
            Statement statement = connection.createStatement ( );
            String createDevices = """
                    CREATE TABLE IF NOT EXISTS `race` (
                      `id` int(11) NOT NULL,
                      `date` date DEFAULT NULL,
                      `number` int(11) DEFAULT NULL,
                      `quantity` int(11) DEFAULT NULL,
                      PRIMARY KEY (`id`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;""";
            statement.executeUpdate ( createDevices );
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    public void createRaceHorseDataBase () {
        try {
            Statement statement = connection.createStatement ( );
            String createDevices = """
                    CREATE TABLE IF NOT EXISTS `race_horse` (
                      `race_id` varchar(255) NOT NULL,
                      `horse_id` varchar(255) NOT NULL,
                      `place` varchar(255) NOT NULL,
                      KEY `FKnsv6m7fvy9pmg1b122f7o62x1` (`race_id`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;""";
            statement.executeUpdate ( createDevices );
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
}
