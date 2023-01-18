package ua.lyashko.module3.migrationUtil;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;

public class CreateMigrationFile {
    @SneakyThrows
    public static void createMigrationFile () {
        File file = new File ( "module3/src/main/resources/V1__migration.sql" );
        FileWriter fileWriter = new FileWriter ( file );
        TariffWriter.createTable ( fileWriter );
        for (int i = 0; i < 5; i++) {
            TariffWriter.writeTariff ( fileWriter );
        }
        AbonentsWriter.createTable ( fileWriter );
        for (int i = 0; i <= 2000; i++) {
            AbonentsWriter.writeAbonent ( fileWriter );
        }
        ActivityWriter.createTable ( fileWriter );
        fileWriter.close ( );
    }
}
