package ua.lyashko.module3.migrationUtil;

import java.io.FileWriter;
import java.io.IOException;

public class ActivityWriter {
    public static void createTable ( FileWriter writer ) {
        String str = """
                                
                CREATE TABLE `activity` (
                  `DTYPE` varchar(31) NOT NULL,
                  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
                  `name` varchar(255) DEFAULT NULL,
                  `message` varchar(255) DEFAULT NULL,
                  `number` varchar(255) DEFAULT NULL,
                  `spentMB` int(11) DEFAULT NULL,
                  `abonent_id` int(11) DEFAULT NULL,
                  PRIMARY KEY (`activity_id`),
                  KEY `FK1ts02qw4mgmlf4u9a4jxn68yt` (`abonent_id`),
                  CONSTRAINT `FK1ts02qw4mgmlf4u9a4jxn68yt` FOREIGN KEY (`abonent_id`) REFERENCES `abonents` (`abonent_id`)
                ) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;""";
        try {
            writer.write ( str );
        } catch ( IOException ex ) {
            ex.printStackTrace ( );
        }
    }
}
