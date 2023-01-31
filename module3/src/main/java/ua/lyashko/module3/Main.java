package ua.lyashko.module3;

import ua.lyashko.module3.migrationUtil.CreateMigrationFile;
import ua.lyashko.module3.systemUtil.SystemRunner;

public class Main {
    public static void main ( String[] args ) {
        CreateMigrationFile.createMigrationFile ( );
        SystemRunner.systemInteractions ( );
    }
}
