package ua.lyashko.module3.migrationUtil;

import ua.lyashko.module3.entity.Abonent;
import ua.lyashko.module3.entityUtil.AbonentUtil;

import java.io.FileWriter;
import java.io.IOException;

public class AbonentsWriter {
    public static void writeAbonent ( FileWriter writer ) {
        Abonent abonent = AbonentUtil.createAbonent ( );
        int deviceId = abonent.getDevices ( ).ordinal ( );
        String fName = abonent.getFirstName ( );
        String lName = abonent.getLastname ( );
        int tariffId = abonent.getTariff ( ).getTariffId ( );
        String str = "\nINSERT INTO abonents (device, first_name, last_name, tariff_tariff_id) " +
                "VALUES (" + deviceId + " , '" + fName + "' , '" + lName + "' , " + tariffId + ")";
        try {
            writer.write ( str );
        } catch ( IOException ex ) {
            ex.printStackTrace ( );
        }
    }

    public static void createTable ( FileWriter writer ) {
        String str = """
                                
                CREATE TABLE `abonents` (
                  `abonent_id` int(11) NOT NULL AUTO_INCREMENT,
                  `device` smallint(6) DEFAULT NULL,
                  `first_name` varchar(255) DEFAULT NULL,
                  `last_name` varchar(255) DEFAULT NULL,
                  `tariff_tariff_id` int(11) DEFAULT NULL,
                  PRIMARY KEY (`abonent_id`),
                  KEY `FKarryhmdvtyom3x6lluk80798n` (`tariff_tariff_id`),
                  CONSTRAINT `FKarryhmdvtyom3x6lluk80798n` FOREIGN KEY (`tariff_tariff_id`) REFERENCES `tariff` (`tariff_id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;""";
        try {
            writer.write ( str );
        } catch ( IOException ex ) {
            ex.printStackTrace ( );
        }
    }
}
