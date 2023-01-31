package ua.lyashko.module3.migrationUtil;


import ua.lyashko.module3.entity.Tariff;
import ua.lyashko.module3.entityUtil.TariffUtil;

import java.io.FileWriter;
import java.io.IOException;

public class TariffWriter {
    public static void writeTariff ( FileWriter writer ) {
        Tariff tariff = TariffUtil.createTariff ( );
        String name = tariff.getName ( );
        int price = tariff.getPrice ( );
        String str = "\nINSERT INTO tariff (name, price)" +
                "VALUES ('" + name + "' , " + price + ") ";
        try {
            writer.write ( str );
        } catch ( IOException ex ) {
            ex.printStackTrace ( );
        }
    }

    public static void createTable ( FileWriter writer ) {
        String createTable = """
                  
                  CREATE TABLE `tariff` (
                  `tariff_id` int(11) NOT NULL AUTO_INCREMENT ,
                  `name` varchar(255) DEFAULT NULL,
                  `price` int(11) DEFAULT NULL,
                  PRIMARY KEY (`tariff_id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;""";
        try {
            writer.write ( createTable );
        } catch ( IOException ex ) {
            ex.printStackTrace ( );
        }
    }
}
