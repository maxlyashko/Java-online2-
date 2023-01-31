package ua.lyashko.lesson24;
import ua.lyashko.lesson24.entity.Device;


public class Main {
    public static void main ( String[] args ) {
        TableUtil.generateFactories ( );
        TableUtil.generateDevices ( );
        Device device = TableUtil.getDevice ( 5 );
        TableUtil.getFactory ( device );
        TableUtil.changeData ( device );
        TableUtil.dropDevice ( 3 );
        System.out.println ( TableUtil.getAllDevicesByFactory ( 3 ) );
        System.out.println ( TableUtil.getQuantityAndSum ( ) );
    }
}